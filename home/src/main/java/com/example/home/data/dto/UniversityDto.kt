package com.example.home.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity
@TypeConverters(StringListConverter::class)
data class UniversityDto(

    @SerializedName("alpha_two_code")
    val alphaTwoCode: String? = "",

    @SerializedName("country")
    val country: String? = "",

    @SerializedName("domains")
    val domains: List<String>? = emptyList(),

    @PrimaryKey
    @SerializedName("name")
    val name: String,

    @SerializedName("state_province")
    val stateProvince: String? = "",

    @SerializedName("web_pages")
    val webPages: List<String>? = emptyList(),
)


class StringListConverter {

    @TypeConverter
    fun fromString(value: String?): List<String?>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<String?>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String? {
        if (list == null) {
            return null
        }
        return Gson().toJson(list)
    }
}