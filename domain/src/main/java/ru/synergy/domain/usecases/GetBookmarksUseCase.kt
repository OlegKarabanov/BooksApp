package ru.synergy.domain.usecases

import ru.synergy.domain.repositories.BooksRepository

class GetBookmarksUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke() = booksRepository.getBookmarks()
}//принимает буксрепозитори и через него идем и получаем все букмарки