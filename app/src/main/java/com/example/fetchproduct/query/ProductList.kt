package com.example.fetchproduct.query

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchproduct.MainActivity.Companion.context
import com.example.fetchproduct.urls.Urls
import com.shopify.buy3.GraphCallResult
import com.shopify.buy3.Storefront
import com.shopify.graphql.support.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductList(var urls: Urls):ViewModel() {

    var cursor = "nocursor"
        set(cursor) {
            field = cursor
            Response()
        }
    var isDirection = false
    var keys: Storefront.ProductSortKeys? = null
    var number = 10
    var shopID = ""
    val message = MutableLiveData<String>()
    val filteredproducts = MutableLiveData<MutableList<Storefront.ProductEdge>>()

    val presentmentCurrency: String?= null

    private fun getAllProducts() {
        val currency_list = ArrayList<Storefront.CurrencyCode>()
        if (presentmentCurrency != "nopresentmentcurrency") {
            currency_list.add(Storefront.CurrencyCode.valueOf(presentmentCurrency!!))
        }
        try {
            doGraphQLQueryGraph(
                urls,
                Query.getAllProducts(cursor, keys, isDirection, number, currency_list),
                customResponse = object : CustomResponse {
                    override fun onSuccessQuery(result: GraphCallResult<Storefront.QueryRoot>) {
                        invoke(result)
                    }
                },
                context = context
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun Response() {
        if (!shopID.isEmpty()) {
            getAllProducts()
        }
    }

    private operator fun invoke(result: GraphCallResult<Storefront.QueryRoot>): Unit {
        if (result is GraphCallResult.Success<*>) {
            consumeResponse(GraphQLResponse.success(result as GraphCallResult.Success<*>))
        } else {
            consumeResponse(GraphQLResponse.error(result as GraphCallResult.Failure))
        }
        return Unit
    }

    private fun consumeResponse(reponse: GraphQLResponse) {
        when (reponse.status) {
            Status.SUCCESS -> {
                val result =
                    (reponse.data as GraphCallResult.Success<Storefront.QueryRoot>).response
                if (result.hasErrors) {
                    val errors = result.errors
                    val iterator = errors.iterator()
                    val errormessage = StringBuilder()
                    var error: Error? = null
                    while (iterator.hasNext()) {
                        error = iterator.next()
                        errormessage.append(error.message())
                    }
                    message.setValue(errormessage.toString())
                } else {
                    var edges: List<Storefront.ProductEdge>? = null
                    if (!shopID.isEmpty()) {
                        edges = result.data!!.products.edges
                    }
                }
            }
            Status.ERROR -> message.setValue(reponse.error!!.error.message)
            else -> {
            }
        }
    }

    fun ViewModel.doGraphQLQueryGraph(urls: Urls, query: Storefront.QueryRootQuery, customResponse: CustomResponse, context: Context) {
        GlobalScope.launch(Dispatchers.Main) {
            val call = urls.graphClient.queryGraph(query)
            call.enqueue { result: GraphCallResult<Storefront.QueryRoot> ->
                GlobalScope.launch(Dispatchers.Main) {
                    customResponse.onSuccessQuery(result)

                }
            }
        }
    }

}