package io.redandroid.westerosandbeyond.core

import java.net.URLEncoder

fun String.encodeUrl() = URLEncoder.encode(this, "utf-8")
fun String.decodeUrl() = URLEncoder.encode(this, "utf-8")