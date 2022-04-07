package com.artemmoroz.anew.core.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        doIntercept(chain)

    private fun doIntercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()

        request.addHeader(HEADER_AUTHORIZATION, "Bearer $API_KEY")

        val newRequest = request.method(original.method, original.body).build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val API_KEY = "7019973f03494525b62199f2e92fe71f"
    }
}