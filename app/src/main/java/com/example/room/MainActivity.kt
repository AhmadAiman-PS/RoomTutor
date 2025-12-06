package com.example.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels // Perlu dependency activity-ktx
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.room.View.BookScreen
import com.example.room.ViewModel.BookViewModel
import com.example.room.ViewModel.BookViewModelFactory
import com.example.room.model.AppDatabase
import com.example.room.model.BookRepository
import com.example.room.ui.theme.RoomTheme // Sesuaikan nama tema project kamu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi Database
        val database = AppDatabase.getDatabase(this)
        // 2. Inisialisasi Repository
        val repository = BookRepository(database.bookDao())
        // 3. Inisialisasi ViewModel menggunakan Factory
        val viewModel: BookViewModel by viewModels {
            BookViewModelFactory(repository)
        }

        setContent {
            RoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 4. Panggil Screen dan lempar ViewModel
                    BookScreen(viewModel = viewModel)
                }
            }
        }
    }
}