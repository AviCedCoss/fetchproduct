package com.malgudi.app.productsection.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.malgudi.app.databinding.MPersonalisedBinding
import com.malgudi.app.databinding.MProductfilteritemBinding

class ProductFilterItem : RecyclerView.ViewHolder{
    var binding: MProductfilteritemBinding?=null
    var personalbinding: MPersonalisedBinding?=null
    constructor(binding: MProductfilteritemBinding) : super(binding.root) {
        this.binding=binding
    }
    constructor(personalbinding: MPersonalisedBinding) : super(personalbinding.root) {
        this.personalbinding=personalbinding
    }
}
