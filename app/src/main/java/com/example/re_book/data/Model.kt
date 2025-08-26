package com.example.re_book.data

data class Model(
    val id: String? = null,
    val bookName: String? = null,
    val bookData: String? = null,
    val createDate: String? = null,
    val bookCategory: String? = null   // new field
)

data class ParentModel(
    val id  :String?=null,
    val model: Model?=null
)