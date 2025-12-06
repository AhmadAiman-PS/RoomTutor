package com.example.room.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.room.ViewModel.BookViewModel
import com.example.room.model.Book

@Composable
fun BookScreen(viewModel: BookViewModel) {
    // Mengambil state dari ViewModel
    val bookList by viewModel.allBooks.collectAsState()

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- Form Input ---
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul Buku") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Penulis") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Tahun Terbit") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Simpan
        Button(
            onClick = {
                viewModel.addBook(title, author, year)
                // Reset form setelah simpan
                title = ""
                author = ""
                year = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Buku")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        // --- List Data (LazyColumn) ---
        Text(text = "Daftar Buku:", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(bookList) { book ->
                BookItem(book)
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = book.title, style = MaterialTheme.typography.titleLarge)
            Text(text = "Penulis: ${book.author}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Tahun: ${book.year}", style = MaterialTheme.typography.bodySmall)
        }
    }
}