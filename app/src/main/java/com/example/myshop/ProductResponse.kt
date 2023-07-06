package com.example.myshop

data class ProductResponse(
    var product:List<Product>,
    var total:Int,
    var skip:Int,
    var limit:String
)
