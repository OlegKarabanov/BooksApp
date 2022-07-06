package ru.synergy.booksapp

import android.app.Application
import ru.synergy.booksapp.di.ServiceLocator
import ru.synergy.booksapp.mappers.BookWithStatusMapper
import ru.synergy.data.repositories.books.BooksRepositoryImpl
import ru.synergy.domain.usecases.BookmarkBookUseCase
import ru.synergy.domain.usecases.GetBookmarksUseCase
import ru.synergy.domain.usecases.GetBooksUseCase
import ru.synergy.domain.usecases.UnbookmarkBookUseCase

import timber.log.Timber
// пишем проект будущего кода, пишем методы которые нам понадобяться
class CleanArchitectureBlueprintsApplication : Application() {
    private val booksRepository: BooksRepositoryImpl // доставать книги с сервера
        get() = ServiceLocator.provideBooksRepository(this) // получаем инстанс и передаем контекст

    val getBooksUseCase: GetBooksUseCase // достаем книги к юзеру
        get() = GetBooksUseCase(booksRepository) // принмает в себя букрепозитори

    val getBookmarksUseCase: GetBookmarksUseCase
        get() = GetBookmarksUseCase(booksRepository)

    val bookmarkBooksUseCase: BookmarkBookUseCase // поставить выделение на книгиу, сердечко
        get() = BookmarkBookUseCase(booksRepository) // получает значение

    val unbookmarkBookUseCase: UnbookmarkBookUseCase // снять выделение с книги, убрать из закладок
        get() = UnbookmarkBookUseCase(booksRepository) // получает значение

    val bookWithStatusMapper = BookWithStatusMapper() // мапать книги из репозитори напрямую

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree()) // внешняя библиотека
    }
}