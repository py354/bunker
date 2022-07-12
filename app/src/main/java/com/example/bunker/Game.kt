package com.example.bunker

import android.content.Context
import android.content.SharedPreferences
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

object Game {
    var day = 1
    var food = 100
    var health = 100

    fun newGame() {
        day = 1
        food = 100
        health = 100
    }

    fun complete(pref: SharedPreferences) {
        day = pref.getInt("day", 1)
        food = pref.getInt("food", 100)
        health = pref.getInt("health", 100)
    }

    fun save(pref: SharedPreferences) {
        with(pref.edit()) {
            putInt("day", day)
            putInt("food", food)
            putInt("health", health)
            apply()
        }
    }

    fun saveResult(context: Context) {
        val file = File(context.filesDir, "results.csv")
        file.appendText("$day,")
    }

    fun getResults(context: Context): ArrayList<Int> {
        val results = arrayListOf<Int>()
        val file = File(context.filesDir, "results.csv")
        if (!file.exists()) {
            file.createNewFile()
        }

        var buff = ""
        for (char in file.readText()) {
            if (char == ',') {
                results.add(buff.toInt())
                buff = ""
            } else {
                buff += char
            }
        }

        return results
    }
}

class Result(
    val text: String,
    val count: Int,
    val resType: Int,
)

class Story (
    val startText: String,
    val yesResult: Result,
)

object Stories {
    var stories = arrayListOf<Story>()
    val noResult = Result("За прошедший день вы только потратили еду", -20, 0)
    init {
        stories.add(Story("На улице довольно тихо. Выйти на разведку?", Result("К счастью, по дороге вы нашли магазин с едой", +50, 0)))
        stories.add(Story("На улице довольно тихо. Выйти на разведку?", Result("К счастью, по дороге вы нашли аптеку", +30, 1)))
        stories.add(Story("На улице довольно тихо. Выйти на разведку?", Result("На улице вы встретили бандитов", -30, 1)))
        stories.add(Story("Кто-то стучится в дверь. Открыть её?", Result("Это был бандит", -30, 1)))
        stories.add(Story("Кто-то стучится в дверь. Открыть её?", Result("Взамен на помощь, вам дали еду", 30, 0)))
        stories.add(Story("Кто-то стучится в дверь. Открыть её?", Result("Взамен на помощь, вам дали бинт", 20, 1)))
        stories.add(Story("По радио сообщили о раздаче еды. Проверить место?", Result("Это оказалось ловушкой бандитов", -40, 1)))
        stories.add(Story("По радио сообщили о раздаче еды. Проверить место?", Result("Вам выдали еды", 50, 0)))
    }

    fun getStory(): Story {
        return stories[Random.nextInt(stories.size)]
    }
}

