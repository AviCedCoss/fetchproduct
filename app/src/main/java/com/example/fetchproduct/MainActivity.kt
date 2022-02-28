package com.example.fetchproduct

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproduct.adapter.ProductAdapter
import com.example.fetchproduct.databinding.ActivityMainBinding
import com.example.fetchproduct.query.ProductList
import com.shopify.buy3.Storefront

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    var productListModel: ProductList? = null
    private var productlist: RecyclerView? = null
    private var products: MutableList<Storefront.ProductEdge>? = null
    lateinit var product_grid_adapter: ProductAdapter
    private var productcursor: String? = null
    private var tags: String = ""
    companion object{
        lateinit var context:Context
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (tags.isEmpty()) {
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                var firstVisibleItemPosition = 0
                if (recyclerView.layoutManager is LinearLayoutManager) {
                    firstVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                } else if (recyclerView.layoutManager is GridLayoutManager) {
                    firstVisibleItemPosition =
                        (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                }
                if (!recyclerView.canScrollVertically(1)) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition > 0
                        && totalItemCount >= products!!.size
                    ) {
                        productListModel!!.number = 20
                        productListModel!!.cursor = productcursor!!
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        //context = this.applicationContext
        productListModel = ViewModelProvider(this).get(ProductList::class.java)
        productListModel!!.message.observe(this, Observer { this.showToast(it) })
        productListModel!!.Response()
        productListModel!!.filteredproducts.observe(
            this,
            Observer<MutableList<Storefront.ProductEdge>> { this.setRecylerData(it) })
        productlist!!.addOnScrollListener(recyclerViewOnScrollListener)

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun setRecylerData(products: MutableList<Storefront.ProductEdge>) {
        try {
            if (products.size > 0) {
                    if (this.products == null) {
                        this.products = products
                        product_grid_adapter.setData(
                            this.products,
                            this,
                            productListModel!!.urls
                        )
                        productlist!!.adapter = product_grid_adapter
                    } else {
                        this.products!!.addAll(products)
                        product_grid_adapter.notifyDataSetChanged()
                    }
                    productcursor = this.products!![this.products!!.size - 1].cursor
                    Log.i("MageNative", "Cursor : " + productcursor!!)

            } else {
                showToast("No product")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}