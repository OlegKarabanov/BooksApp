package ru.synergy.data.db

import androidx.room.*
import ru.synergy.data.entities.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
//реализуем стандартные наборы методов
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBook(book: BookEntity)

    @Query("SELECT * FROM book")
    fun getSavedBooks(): Flow<List<BookEntity>>//получаем флоу из листа букентити

    @Delete
    suspend fun deleteBook(book: BookEntity)//
}