package com.example.firsthwapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firsthwapp.ui.theme.FirstHwAppTheme

data class Book(
    val title: String,
    val author: String,
    val price: Int
) {
    fun contains(key: String, ignoreCase: Boolean): Boolean {
        return title.contains(key, ignoreCase = ignoreCase)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstHwAppTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "search") {
                        composable("search") { MySearch(navController, books) }
                        composable("library") { Library(navController, books) }
                    }
                }
            }
        }
    }
}

@Composable
fun MySearch(navController: NavHostController, books: List<Book>) {
    var search by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(emptyList<Book>()) }

    Column(modifier = Modifier
        .padding(top = 80.dp, start = 10.dp, end = 10.dp)
        .fillMaxSize()) {

        TextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Введите название книги") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                result = books
                    .filter { it.contains(search, ignoreCase = true) }
                    .take(5)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
        ) {
            Text("Поиск")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate("library") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
        ) {
            Text("Библиотека")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(result) { book ->

                BookItem(book)
            }
        }
    }
}

@Composable
fun Library(navController: NavHostController, books: List<Book>) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(books) { book ->
                BookItem(book)
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = book.title, style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = "${book.price} рублей",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF000000)
                )
            }
        }
    }
}

val books = listOf(
    Book("Преступление и наказание", "Ф. Достоевский", 450),
    Book("Анна Каренина", "Л. Толстой", 520),
    Book("Братья Карамазовы", "Ф. Достоевский", 600),
    Book("Война и мир", "Л. Толстой", 850),
    Book("Доктор Живаго", "Б. Пастернак", 480),
    Book("Лолита", "В. Набоков", 430),
    Book("Мастер и Маргарита", "М. Булгаков", 620),
    Book("Собачье сердце", "М. Булгаков", 350),
    Book("Великий Гэтсби", "Ф. Фицджеральд", 390),
    Book("1984", "Дж. Оруэлл", 410),
    Book("Убить пересмешника", "Харпер Ли", 440),
    Book("Гордость и предубеждение", "Джейн Остин", 380),
    Book("Дневник Анны Франк", "Анна Франк", 300),
    Book("Гарри Поттер", "Дж. Роулинг", 990),
    Book("Властелин колец", "Дж. Р. Р. Толкин", 1200),
    Book("Маленький принц", "А. де Сент-Экзюпери", 250),
    Book("Алхимик", "Пауло Коэльо", 340),
    Book("Зеленая миля", "Стивен Кинг", 550),
    Book("Унесенные ветром", "Маргарет Митчелл", 700),
    Book("Сто лет одиночества", "Г. Маркес", 580),
    Book("Божественная комедия", "Данте Алигьери", 650),
    Book("Одиссея", "Гомер", 400),
    Book("Дон Кихот", "Мигель де Сервантес", 520),
    Book("Триумфальная арка", "Э. М. Ремарк", 460),
    Book("Над пропастью во ржи", "Дж. Сэлинджер", 370),
    Book("Метро 2033", "Дмитрий Глуховский", 500),
    Book("Пикник на обочине", "Братья Стругацкие", 420),
    Book("Трудно быть богом", "Братья Стругацкие", 410),
    Book("451 градус по Фаренгейту", "Рэй Брэдбери", 390),
    Book("Заводной апельсин", "Энтони Бёрджесс", 360),
    Book("Портрет Дориана Грея", "Оскар Уайльд", 400),
    Book("Код да Винчи", "Дэн Брэун", 590),
    Book("Sapiens", "Юваль Ной Харари", 800),
    Book("Думай медленно, решай быстро", "Даниэль Канеман", 950)
)