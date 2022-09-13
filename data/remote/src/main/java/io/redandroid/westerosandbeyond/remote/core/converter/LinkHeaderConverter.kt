package io.redandroid.westerosandbeyond.remote.core.converter

class LinkHeaderConverter {

    val linkRegEx = Regex(".*<(.+)>;.*rel=\"(.+)\".*")

    fun convert(linkHeader: String): Map<String, String> {
        val headers = mutableMapOf<String, String>()

        linkHeader.split(",").map {
            val regExMatch = linkRegEx.find(it) ?: return@map
            val (pageUrl, key) = regExMatch.destructured
            headers[key] = pageUrl
        }

        return headers
    }
}