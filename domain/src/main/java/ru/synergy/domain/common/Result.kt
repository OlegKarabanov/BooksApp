package ru.synergy.domain.common

/**
 * A generic class that holds a value or an error status.
 * @param <T>
 */
sealed class Result<out R> {// конфигурируем извне какого класса будет резалт

    data class Success<out T>(val data: T) : Result<T>()//в конструктор передавать дата
    data class Error(val exception: Exception) : Result<Nothing>() //если эррор,
    //мы будем ждать на вход эксепшн

    override fun toString(): String {//приведение в трингу
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}