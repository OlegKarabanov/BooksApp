package ru.synergy.data.api

import com.squareup.moshi.Json


class BooksApiResponse(val items: List<Item>)// на вход вал лист айтемов

data class Item(// создаем дата класс
    @field:Json(name = "id") //будеь сюриализовываться как джисон объект с именем айди
    val id : String, //
    @field:Json(name = "volumeInfo") //
    val volumeInfo: ApiVolumeInfo //
)

data class ApiVolumeInfo(  //вложенный класс, на входе вал титле, автор и обложку
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageLinks?
)

data class ImageLinks(val smallThumbnail: String, val thumbnail: String) //создаем имеджлинкс
//этим мы описали структуру АПИ