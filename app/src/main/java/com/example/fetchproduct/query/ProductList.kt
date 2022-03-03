package com.example.fetchproduct.query

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchproduct.MainActivity
import com.example.fetchproduct.urls.Urls
import com.shopify.buy3.GraphCallResult
import com.shopify.buy3.Storefront
import com.shopify.graphql.support.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductList:ViewModel() {

    val message = MutableLiveData<String>()
    var itemTitles:MutableList<String> = mutableListOf()
    val titleItem = MutableLiveData<MutableList<Storefront.ProductEdge>>()

    private fun testing() {
        val urls= Urls()
        try {
            doGraphQLQueryGraph(
                urls,
                Query.shopDetails,
                customResponse = object : CustomResponse {
                    override fun onSuccessQuery(result: GraphCallResult<Storefront.QueryRoot>) {
                        invoke(result)
                    }
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun Response() {
        testing()
    }

    private operator fun invoke(result: GraphCallResult<Storefront.QueryRoot>) {
        if (result is GraphCallResult.Success<*>) {
            consumeResponse(GraphQLResponse.success(result as GraphCallResult.Success<*>))
        } else {
            consumeResponse(GraphQLResponse.error(result as GraphCallResult.Failure))
        }
        return
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
                   var edges = result.data?.shop?.name
                    Log.i("avinash",""+edges)
                    val edgesSize = result.data!!.products.edges.size
                    Log.i("avinash",""+edgesSize)
                    var title =result.data?.products?.edges?.get(0)?.node?.title
                    Log.i("avinash",""+title)
                    var edge: List<Storefront.ProductEdge>? = null
                    edge = result.data!!.products.edges
                    titleItem.setValue(edge)
                    var itr = edge.listIterator()
                    while (itr.hasNext()){
                        var edgeModel = itr.next()

                       // main. = listOf(edgeModel.node.title)
                        Log.i("avinash",""+edgeModel.node.title)
                    }

                }
            }
            Status.ERROR -> message.setValue(reponse.error!!.error.message)
            else -> {
            }
        }
    }

    fun doGraphQLQueryGraph(
        urls: Urls,
        query: Storefront.QueryRootQuery,
        customResponse: CustomResponse
    ) {
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