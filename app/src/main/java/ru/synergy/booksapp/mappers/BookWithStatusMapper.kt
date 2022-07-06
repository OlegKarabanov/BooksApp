package ru.synergy.booksapp.mappers

import ru.synergy.booksapp.entities.BookWithStatus
import ru.synergy.booksapp.entities.BookmarkStatus
import ru.synergy.domain.entities.Volume
import ru.synergy.domain.entities.VolumeInfo

class BookWithStatusMapper {
    fun fromVolumeToBookWithStatus( //
        volumes: List<Volume>,
        bookmarks: List<Volume>
    ): List<BookWithStatus> { // вернуть лист
        val commonElements = volumes.filter { it.id in bookmarks.map { bookmark -> bookmark.id } }
        //отфильтрованный волюме, вытаскиваем айди и проверяем их
        val booksWithStatus = arrayListOf<BookWithStatus>()
        for (volume in volumes) {//пробегаемся
            if (volume in commonElements) {
                booksWithStatus.add(
                    BookWithStatus(
                        volume.id,
                        volume.volumeInfo.title,
                        volume.volumeInfo.authors,
                        volume.volumeInfo.imageUrl,
                        BookmarkStatus.BOOKMARKED
                    )
                )
            } else {
                booksWithStatus.add(
                    BookWithStatus(
                        volume.id,
                        volume.volumeInfo.title,
                        volume.volumeInfo.authors,
                        volume.volumeInfo.imageUrl,
                        BookmarkStatus.UNBOOKMARKED
                    )
                )
            }
        }
        return booksWithStatus.sortedBy { it.id }//сортировка по айди
    }

    fun fromBookWithStatusToVolume(bookWithStatus: BookWithStatus): Volume {
        return Volume(
            bookWithStatus.id,
            VolumeInfo(bookWithStatus.title, bookWithStatus.authors, bookWithStatus.imageUrl)
        )
    }
}