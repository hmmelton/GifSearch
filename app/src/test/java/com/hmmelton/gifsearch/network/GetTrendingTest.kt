package com.hmmelton.gifsearch.network

import com.squareup.moshi.JsonAdapter
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import junit.framework.Assert.assertNotNull
import org.junit.Test

/**
 * This class tests possible responses received when making a call to Giphy's `v1/gifs/trending` endpoint. Response
 * codes are taken from: https://developers.giphy.com/docs/#response-codes
 */
class GetTrendingTest : NetworkTest() {

    private lateinit var gifResponseAdapter: JsonAdapter<GifResponse>

    override fun setUp() {
        super.setUp()

        gifResponseAdapter = testHelper.moshi.adapter(GifResponse::class.java)
    }

    @Test
    fun getTrending_none_success200() {
        givenResponse(code = 200, responseBody = testHelper.sampleResponse)

        val response = whenGetTrending()

        thenCallSuccessful(response = response)
        thenBodyParsedCorrectly(responseBody = response.body())
    }

    @Test
    fun getTrending_none_failure400() {
        givenResponse(code = 400, responseBody = "")

        val response = whenGetTrending()

        thenCallUnsuccessful(response = response, expectedResponseCode = 400)
        thenBodyIsNull(responseBody = response.body())
    }

    @Test
    fun getTrending_none_failure403() {
        givenResponse(code = 403, responseBody = "")

        val response = whenGetTrending()

        thenCallUnsuccessful(response = response, expectedResponseCode = 403)
        thenBodyIsNull(responseBody = response.body())
    }

    @Test
    fun getTrending_none_failure404() {
        givenResponse(code = 404, responseBody = "")

        val response = whenGetTrending()

        thenCallUnsuccessful(response = response, expectedResponseCode = 404)
        thenBodyIsNull(responseBody = response.body())
    }

    @Test
    fun getTrending_none_failure429() {
        givenResponse(code = 429, responseBody = "")

        val response = whenGetTrending()

        thenCallUnsuccessful(response = response, expectedResponseCode = 429)
        thenBodyIsNull(responseBody = response.body())
    }

    private fun whenGetTrending() = execute { testHelper.service.getTrending(limit = 10, offset = 0) }

    private fun thenBodyParsedCorrectly(responseBody: GifResponse?) {
        assertNotNull(responseBody)
        assertEquals(gifResponseAdapter.fromJson(testHelper.sampleResponse), responseBody)
    }

    private fun thenBodyIsNull(responseBody: GifResponse?) {
        assertNull(responseBody)
    }
}