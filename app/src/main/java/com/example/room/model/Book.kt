package com.example.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String,
    val year: String // Menggunakan String agar mudah diinput, bisa Int jika ingin validasi angka
)