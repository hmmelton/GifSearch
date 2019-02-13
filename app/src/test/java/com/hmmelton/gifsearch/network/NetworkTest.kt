package com.hmmelton.gifsearch.network

import com.hmmelton.gifsearch.NetworkTestHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import retrofit2.Call
import retrofit2.Response

open class NetworkTest {

    protected lateinit var testHelper: NetworkTestHelper

    @Before
    open fun setUp() {
        testHelper = NetworkTestHelper()
    }

    protected fun <T> execute(action: () -> Call<T>): Response<T> {
        val call: Call<T> = action()
        return call.execute()
    }

    protected fun givenResponse(code: Int, responseBody: String) {
        testHelper.setResponse(code = code, body = responseBody)
    }

    protected fun <T> thenCallSuccessful(response: Response<T>) {
        assertTrue(response.isSuccessful)
        assertEquals(200, response.code())
        assertNotNull(response.body())
    }

    protected fun <T> thenCallUnsuccessful(
        response: Response<T>,
        expectedResponseCode: Int
    ) {
        assertFalse(response.isSuccessful)
        assertEquals(expectedResponseCode, response.code())
        assertNull(response.body())
        assertEquals("", response.errorBody()?.string())
    }
}