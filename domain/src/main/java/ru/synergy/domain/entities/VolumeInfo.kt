package ru.synergy.domain.entities

data class VolumeInfo(//дата класс. в кот храним титле , автора и ссылку на обложку
    val title: String,
    val authors: List<String>,
    val imageUrl: String?
)