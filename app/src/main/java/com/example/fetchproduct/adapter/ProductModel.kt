package com.example.fetchproduct.adapter

class ProductModel(title: String) {
    private var title: String
    init {
        this.title = title!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
}