package com.example.bottomnav.data

import com.squareup.moshi.Json

data class MarsPhoto (
    val id: String,

    //@Json(name = "img_src")
    val img_src: String
)
