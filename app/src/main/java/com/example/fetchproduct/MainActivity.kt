package com.example.fetchproduct

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchproduct.adapter.ProductModel
import com.example.fetchproduct.adapter.ItemAdapter
import com.example.fetchproduct.databinding.ActivityMainBinding
import com.example.fetchproduct.query.ProductList
import com.shopify.buy3.Storefront

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private var productListModel: ProductList? = null
    private val productList = ArrayList<ProductModel>()
    private lateinit var itemAdapter: ItemAdapter
    private var productListItem: ArrayList<String>? = ArrayList()
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        context = this.applicationContext
        productListModel = ViewModelProvider(this).get(ProductList::class.java)
        productListModel!!.message.observe(this, Observer { this.showToast(it) })
        productListModel!!.Response()
        productListModel!!.titleItem.observe(this,
            Observer<MutableList<Storefront.ProductEdge>>
            { this.productData(it) })
        itemAdapter = ItemAdapter(productList)
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.rvItemList.layoutManager = layoutManager
        binding.rvItemList.itemAnimator = DefaultItemAnimator()
        binding.rvItemList.adapter = itemAdapter
        productListModel?.itemTitles
    }

    private fun productData(products: MutableList<Storefront.ProductEdge>) {
        val myproduct = products
        for (i in 0 until myproduct.size){
            productListItem?.add(myproduct.get(i).node.title.toString())
            var item = ProductModel( myproduct.get(i).node.title.toString())
            productList.add(item)
        }
        itemAdapter.notifyDataSetChanged()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}