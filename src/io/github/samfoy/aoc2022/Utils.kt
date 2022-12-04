package io.github.samfoy.aoc2022

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import java.io.Closeable
import java.io.File
import java.io.FileNotFoundException
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*
import javax.naming.TimeLimitExceededException

fun String.asLines(): List<String> = lines().dropLastWhile { it == "\n" || it == "" }
suspend fun getInputForDay(day: Int) = fetchInput(2022, day, File("src/resources")).dropLastWhile { it == '\n' }

fun <T> List<T>.toPair(): Pair<T, T> {
    if (this.size > 2) throw IllegalArgumentException()
    return Pair(this.first(), this.last())
}

suspend fun fetchInput(year: Int, day: Int, outDir: File): String {
    val tokenFile = File(outDir, "session")
    if (!tokenFile.exists())
        throw FileNotFoundException("Session Token File does not exist")
    val token = tokenFile.readText()

    val dayFileName = String.format("Day%02d", day)
    val dayFile = File(outDir, dayFileName)
    if (dayFile.exists())
        return dayFile.readText()

    val est = ZoneOffset.ofHours(-5)
    val timeNow = ZonedDateTime.now().withZoneSameInstant(est)
    val puzzleTime = ZonedDateTime.of(year, 12, day, 0, 0, 0, 0, est)

    if (timeNow < puzzleTime)
        throw TimeLimitExceededException("The puzzle is not out yet")

    println("Fetching")
    val scraper = Scraper(token)
    val data = scraper.use {
        it.grabInput(year, day)
    }

    return withContext(Dispatchers.Default) {
        launch(Dispatchers.IO) {
            dayFile.writeText(data)
        }
        async {
            data
        }.await()
    }
}

class Scraper(private val sessionToken: String) : Closeable {

    private val client = HttpClient(OkHttp) {
        install(ContentEncoding) {
            deflate()
            gzip()
        }
    }

    @Throws(ResponseException::class)
    suspend fun grabInput(year: Int, day: Int): String {
        val response = client.get("https://adventofcode.com/$year/day/$day/input") {
            headers {
                append(
                    "User-Agent",
                    "github.com/samfoy/KotlinAOC2022 by sam.foy.painter@gmail.com"
                )
                append(
                    "cookie",
                    "session=$sessionToken"
                )
            }
        }

        when (response.status) {
            HttpStatusCode.Accepted, HttpStatusCode.OK -> return response.body()
            else -> throw ResponseException(response, "AOC:: " + response.body())
        }
    }

    override fun close() {
        client.close()
    }
}