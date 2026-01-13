package com.example.firsthwapp

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firsthwapp.ui.theme.FirstHwAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstHwAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MySearch()
                }
            }
        }
    }
}
@Composable
fun MySearch() {
    var search by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(emptyList<String>()) }

    Column(modifier = Modifier.padding(top = 180.dp, start = 10.dp, end = 10.dp).fillMaxSize()) {
        TextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Введите название книги") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                result = books
                    .filter { it.contains(search, ignoreCase = true) }
                    .take(5)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE91E63)
            )
        ) {
            Text("Поиск")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(result) { title ->
                Text(
                    text = title,
                    modifier = Modifier.padding(vertical = 10.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                HorizontalDivider()
            }
        }
    }
}
private val books = listOf(
    "Преступление и наказание",
    "Анна Каренина",
    "Братья Карамазовы",
    "Война и мир",
    "Доктор Живаго",
    "Лолита",
    "Мастер и Маргарита",
    "Собачье сердце",
    "Великий Гэтсби",
    "1984",
    "Убить пересмешника",
    "Гордость и предубеждение",
    "Дневник Анны Франк",
    "Гарри Поттер и философский камень",
    "Властелин колец",
    "Маленький принц",
    "Алхимик",
    "Зеленая миля",
    "Унесенные ветром",
    "Сто лет одиночества",
    "Божественная комедия",
    "Одиссея",
    "Дон Кихот",
    "Триумфальная арка",
    "Над пропастью во ржи",
    "Скотный двор",
    "Улисс",
    "Шум и ярость",
    "На маяк",
    "Илиада",
    "Гамлет",
    "Мартин Иден",
    "Собор Парижской Богоматери",
    "Кладбище домашних животных",
    "Сияние",
    "Оно",
    "Бойцовский клуб",
    "На игле",
    "Автостопом по Галактике",
    "451 градус по Фаренгейту",
    "Заводной апельсин",
    "Повелитель мух",
    "Портрет Дориана Грея",
    "Джейн Эйр",
    "Грозовой перевал",
    "Франкенштейн",
    "Приключения Гекльберри Финна",
    "Зов предков",
    "Граф Монте-Кристо",
    "Гроздья гнева",
    "Приключения Шерлока Холмса",
    "Таинственный сад",
    "Хладнокровное убийство",
    "Робинзон Крузо",
    "Большой сон",
    "Ребекка",
    "Остров сокровищ",
    "Процесс",
    "Миссис Дэллоуэй",
    "Старик и море",
    "Ярмарка тщеславия",
    "Отверженные",
    "На дороге",
    "Блэкаут",
    "Исход",
    "Generation П",
    "Чапаев и Пустота",
    "Метро 2033",
    "Пикник на обочине",
    "Трудно быть богом",
    "Понедельник начинается в субботу",
    "О дивный новый мир",
    "Уловка-22",
    "Посторонний",
    "Чума",
    "Тошнота",
    "Над пропастью во ржи",
    "Бойня номер пять",
    "Заводной апельсин",
    "Американский психопат",
    "Парфюмер",
    "Имя розы",
    "Код да Винчи",
    "Шелк",
    "Чтец",
    "Облачный атлас",
    "Дорога",
    "Исчезнувшая",
    "Девушка с татуировкой дракона",
    "Прислуга",
    "Виноваты звезды",
    "Марсианин",
    "Проект «Аве Мария»",
    "Задача трех тел",
    "Память о прошлом Земли",
    "Автостопом по галактике",
    "Магия утра",
    "Sapiens. Краткая история человечества",
    "Homo Deus. Краткая история завтрашнего дня",
    "Думай медленно, решай быстро")