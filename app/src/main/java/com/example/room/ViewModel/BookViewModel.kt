package com.example.room.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.room.model.Book
import com.example.room.model.BookRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    // Mengambil data dari repo dan mengubahnya jadi StateFlow agar bisa diobservasi UI
    val allBooks: StateFlow<List<Book>> = repository.allBooks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addBook(title: String, author: String, year: String) {
        if (title.isNotBlank() && author.isNotBlank() && year.isNotBlank()) {
            val newBook = Book(title = title, author = author, year = year)
            viewModelScope.launch {
                repository.insertBook(newBook)
            }
        }
    }
}

// Factory untuk membuat ViewModel dengan parameter Repository
class BookViewModelFactory(private val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}