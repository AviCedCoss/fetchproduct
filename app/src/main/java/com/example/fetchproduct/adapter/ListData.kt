package com.example.fetchproduct.adapter

import android.text.Spanned
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shopify.buy3.Storefront

class ListData:BaseObservable() {
    var textdata: String? = null
    var product: Storefront.Product? = null
    var id: String? = null

    var image_url:String?=null
        set(value) {
            field=value
           // notifyPropertyChanged(BR.image_url)
        }

    var description: String? = null
    var descriptionhmtl: Spanned? = null

    var specialprice: String? = null
        set(value) {
            field = value
           // notifyPropertyChanged(BR.specialprice)
        }

    var regularprice: String? = null
        set(value) {
            field = value
            //notifyPropertyChanged(BR.regularprice)
        }

    var offertext: String? = null
        set(value) {
            field = value
           // notifyPropertyChanged(BR.offertext)
        }

    var addtowish: String? = null
        set(addtowish) {
            field = addtowish
           // notifyPropertyChanged(BR.addtowish)
        }

    var isStrike: Boolean = false
    var arimage: String? = null
}
