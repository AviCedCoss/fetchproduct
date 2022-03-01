package com.example.fetchproduct.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproduct.R
import com.example.fetchproduct.databinding.CardViewDesignBinding
import com.example.fetchproduct.query.ProductList
import com.example.fetchproduct.urls.Urls
import com.shopify.buy3.Storefront
import org.json.JSONArray

class ProductAdapter
constructor() : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    lateinit var products: MutableList<Storefront.ProductEdge>
    private var activity: Activity? = null
    private var repository: Urls? = null

    // private lateinit var firebaseAnalytics: FirebaseAnalytics
    var presentmentcurrency: String? = null
    var whilistArray = JSONArray()
    fun setData(products: List<Storefront.ProductEdge>?, activity: Activity) {
        this.products = products as MutableList<Storefront.ProductEdge>
        this.activity = activity
      //  this.repository = repository
        // firebaseAnalytics = Firebase.analytics
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemViewType(position: Int): Int {
        var viewtype = 0
        if (!products[position].node.availableForSale) {
            viewtype = -1
        }
        return viewtype
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<CardViewDesignBinding>(
            LayoutInflater.from(parent.context),
            R.layout.card_view_design,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val variant = this.products[position].node.variants.edges[0].node
        val data = ListData()
        Log.i("MageNative", "Product ID" + this.products[position].node.id)
        data.product = this.products[position].node
        data.textdata = this.products[position].node.title
        data.description = this.products[position].node.description
        //  holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(val binding:CardViewDesignBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(model : ProductList){

                }
            }

}
