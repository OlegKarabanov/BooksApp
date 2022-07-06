package ru.synergy.data.mappers

import ru.synergy.data.api.BooksApiResponse
import ru.synergy.domain.entities.Volume
import ru.synergy.domain.entities.VolumeInfo


class BookApiResponseMapper {
    fun toVolumeList(response: BooksApiResponse): List<Volume> { //получаем респонсе, вернуть лист волюме
        return response.items.map {// если есть респонсе айтемс
            Volume( // воспользуемся мапой
                it.id, VolumeInfo( // конвертировать каждую пришедшую сущность в волюме
                    it.volumeInfo.title,
                    it.volumeInfo.authors,
                    it.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")//
                // проверяем не пустые ли они, если что делаем реплейс, заменяем хттп на хттпс
                )
            )
        }
    }
}