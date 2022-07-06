package ru.synergy.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApi {
    @GET("books/v1/volumes") //гетзапрос идти по юрл
    suspend fun getBooks(@Query("q") author: String): Response<BooksApiResponse>//м.б.прерываемая
    // на выходе хотим увидеть респонсе по типу буксапиреспонсе, и создаем новый класс в отдельн файле апи
}