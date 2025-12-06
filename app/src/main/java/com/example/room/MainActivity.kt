package com.example.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider

// --- BAGIAN IMPORT YANG DIPERBAIKI SESUAI SCREENSHOT ---

// 1. Mengambil dari folder 'model' (bukan 'data')
import com.example.room.model.AppDatabase
import com.example.room.model.BookRepository

// 2. Mengambil dari folder 'View' (Perhatikan huruf 'V' besar)
// Asumsi: Fungsi composable utama di dalam file BookScreen.kt bernama BookApp
import com.example.room.View.BookApp

// 3. Mengambil dari folder 'ViewModel' (Perhatikan huruf 'V' dan 'M' besar)
import com.example.room.ViewModel.BookViewModel
import com.example.room.ViewModel.BookViewModelFactory

// 4. Mengambil dari folder 'ui.theme' (Ini sudah benar)
import com.example.room.ui.theme.RoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi Database & Repository
        val database = AppDatabase.getDatabase(this)
        val repository = BookRepository(database.bookDao())

        // Inisialisasi ViewModel Factory & ViewModel
        val viewModelFactory = BookViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[BookViewModel::class.java]

        setContent {
            RoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Memanggil UI Utama
                    BookApp(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}