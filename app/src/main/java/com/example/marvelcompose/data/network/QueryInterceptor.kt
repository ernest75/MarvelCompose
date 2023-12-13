package com.example.marvelcompose.data.network

import com.example.marvelcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.PrivateKey
import java.security.PublicKey
import java.util.Date

class QueryInterceptor(private val privateKey: String, private val publicKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, privateKey, publicKey)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}