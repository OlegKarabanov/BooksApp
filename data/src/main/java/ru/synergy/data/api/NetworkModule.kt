package ru.synergy.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkModule {

    private val moshi by lazy {//инициализацию лжйзи
        val moshiBuilder = Moshi.Builder() //что такое моши???
            .add(KotlinJsonAdapterFactory())
        moshiBuilder.build()
    }

    private val loggingInterceptor by lazy {// интересептор - перехвыатчик
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }

    private val httpClient by lazy {//??
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // перехватывать запросы в боди
            .build()
    }
// в ретрофите выставляем все то, что написали выше
    private fun getRetrofit(endpointURL: String): Retrofit {//конструимруем ретрорфит
        return Retrofit.Builder()
            .baseUrl(endpointURL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())// т.к. колл надо преобразовать при получении
            .build()
    }

    fun createBooksApi(endpointURL: String): BooksApi { //
        val retrofit = getRetrofit(endpointURL)
        return retrofit.create(BooksApi::class.java)
    }
}