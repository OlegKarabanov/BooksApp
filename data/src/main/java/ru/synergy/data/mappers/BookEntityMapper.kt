package ru.synergy.data.mappers

import ru.synergy.data.entities.BookEntity
import ru.synergy.domain.entities.Volume
import ru.synergy.domain.entities.VolumeInfo

class BookEntityMapper {
    fun toBookEntity(volume: Volume): BookEntity {//созд класс с телом, возвращ бук ентити
        return BookEntity(
            id = volume.id,
            title = volume.volumeInfo.title,
            authors = volume.volumeInfo.authors,
            imageUrl = volume.volumeInfo.imageUrl
        )
    }

    fun toVolume(bookEntity: BookEntity): Volume { // приняли
        return Volume( //вернули
            bookEntity.id,
            VolumeInfo(bookEntity.title, bookEntity.authors, bookEntity.imageUrl)//перевли в другой формат
        )
    }
}