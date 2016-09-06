package com.example.gleb.mediumme.entities

import java.io.Serializable

data class PostEntityResponse (var author: String, var title: String, var thumbnail: String, var num_comments: Int, var url: String, var isNew: Boolean = true) : Serializable