package ru.synergy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.synergy.data.entities.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)//мигрировать схему не будем
@TypeConverters(Converters::class)//встроенная аннотация рум
abstract class BooksDatabase : RoomDatabase() {//
    abstract fun bookDao(): BookDao//

    companion object {
        @Volatile//это марк ап для дживиэм
        private var INSTANCE: BooksDatabase? = null//синглтон

        fun getDatabase(appContext: Context): BooksDatabase {//на вход контекст а возврщ просто контекст
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    appContext, BooksDatabase::class.java,
                    BooksDatabase::class.simpleName!!
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}