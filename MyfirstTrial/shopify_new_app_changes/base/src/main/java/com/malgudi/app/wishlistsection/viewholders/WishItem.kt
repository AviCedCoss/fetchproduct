package com.malgudi.app.wishlistsection.viewholders

import androidx.recyclerview.widget.RecyclerView

import com.malgudi.app.databinding.MWishitemBinding
class WishItem:RecyclerView.ViewHolder{
    var binding:MWishitemBinding
    constructor( binding: MWishitemBinding):super(binding.root){
        this.binding=binding;
    }
}
