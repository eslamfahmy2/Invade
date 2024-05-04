package com.example.home.domain.models

import com.example.home.data.dto.UniversityDto


data class University(
    val name: String,
    val country: String,
    val alphaTwoCode: String,
    val state: String,
    val domains: List<String>,
    val web: List<String>,
) : java.io.Serializable

fun UniversityDto.toDomain(): University {
    return University(
        name = this.name,
        alphaTwoCode = this.alphaTwoCode ?: "",
        country = this.country ?: "",
        state = this.stateProvince ?: "",
        domains = this.domains ?: emptyList(),
        web = this.webPages ?: emptyList()
    )
}