package ru.synergy.data.repositories.books

import ru.synergy.data.api.BooksApi
import ru.synergy.data.mappers.BookApiResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.synergy.data.repositories.books.BooksRemoteDataSource
import ru.synergy.domain.entities.Volume
import ru.synergy.domain.common.Result

class BooksRemoteDataSourceImpl(//конструктор
    private val service: BooksApi,// перем сервайс букапи, и подключаем
    private val mapper: BookApiResponseMapper // и маппер подключаем
) : BooksRemoteDataSource {
    override suspend fun getBooks(author: String): Result<List<Volume>> = // пишем метод
        withContext(Dispatchers.IO) {
            try {//если что-то пойдет не так
                val response = service.getBooks(author)//передаем автора
                if (response.isSuccessful) { //если респонс удачен
                    return@withContext Result.Success(mapper.toVolumeList(response.body()!!))//
                    //возвращаем , созд новый объект маппер и проебраз в него респонсе боди, если не пустое
                } else {
                    return@withContext Result.Error(Exception(response.message()))//проектир интерфейс
                    //чтобы потом его реализовать в другом классе
                }
            } catch (e: Exception) { //ловим эксепшн
                return@withContext Result.Error(e)//возращ резалт эррор
            }
        }
}