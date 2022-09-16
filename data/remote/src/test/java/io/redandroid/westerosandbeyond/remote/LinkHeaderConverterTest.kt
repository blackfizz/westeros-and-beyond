package io.redandroid.westerosandbeyond.remote

import io.redandroid.westerosandbeyond.remote.core.converter.LinkHeaderConverter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class LinkHeaderConverterTest {

    val nextUrl = "https://hereIsTheUrlToTheNextPage.com"
    val previousUrl = "https://hereIsTheUrlToThePreviousPage.com"
    val firstUrl = "https://hereIsTheUrlToTheFirstPage.com"
    val lastUrl = "https://hereIsTheUrlToTheLastPage.com"

    val linkHeader = "<$nextUrl>; rel=\"next\", " +
            "<$previousUrl>; rel=\"prev\", " +
            "<$firstUrl>; rel=\"first\", " +
            "<$lastUrl>; rel=\"last\""



    @Test
    fun `convert string with link header to a map`() = runTest {
        val converter = LinkHeaderConverter()
        val result = converter.convert(linkHeader)

        Assert.assertEquals(nextUrl, result["next"])
        Assert.assertEquals(previousUrl, result["prev"])
        Assert.assertEquals(firstUrl, result["first"])
        Assert.assertEquals(lastUrl, result["last"])
    }
}