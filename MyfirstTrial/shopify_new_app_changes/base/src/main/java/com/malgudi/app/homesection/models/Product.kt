package com.malgudi.app.homesection.models

import android.content.Intent
import android.view.View

import com.malgudi.app.basesection.models.ListData
import com.malgudi.app.productsection.activities.ProductView
import com.malgudi.app.utils.Constant

class Product {
    fun productClick(view: View, data: ListData) {
        val productintent = Intent(view.context, ProductView::class.java)
        productintent.putExtra("ID", data.product!!.id.toString())
        productintent.putExtra("tittle", data.textdata)
        productintent.putExtra("product", data.product)
        view.context.startActivity(productintent)
        Constant.activityTransition(view.context)
    }
}
