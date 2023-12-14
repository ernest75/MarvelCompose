package com.example.marvelcompose.data.network

import com.example.marvelcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import com.example.marvelcompose.PrivateKey
import com.example.marvelcompose.PublicKey
import java.util.Date
import javax.inject.Inject

class QueryInterceptor @Inject constructor(@PrivateKey private val privateKey: String, @PublicKey private val publicKey: String) : Interceptor {
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