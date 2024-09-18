import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
//    println(noErrorHandling(args))

    val content = readTextFile("src/resources/stocks.csv")
    if (content.isEmpty()) return
    val yield = content
        .trim()
        .lines()
        .drop(1)
        .map { it.split(",") }.associate { columns ->
            val stock = columns[0]
            val lastPrice = columns[1].toDouble()
            val dividend = columns[2].toDouble()

            val yield = (dividend / lastPrice) * 100
            stock to "%.2f".format(yield)
        }
    println(yield)
}

/**
 * All allowed cases of execution handling
 *
 * 1. try {} catch () {}
 * 2. try {} catch () {} catch () {} .. // Multiple catch `and` we should maintain the order
 * 3. try {} catch () {} finally {}
 * 4. try {} finally {}
 *
 * After `exitProcess(-1)` no code will be executed not even finally.
 *
 * You should never catch `throwable` because if we look at java hierarchy of exceptions,
 * we'll see that if you catch throw, you catch the entire universe of exceptions as it is the parent class of `exception` and `error`.
 * This `error` will include like stack overflow error, and out of memory error.
 ***/
fun noErrorHandling(args: Array<String>) = try {
    "%.${2}f".format(args[0].toDouble())
} catch (e: ArrayIndexOutOfBoundsException) {
    "Please provide at least one number."
} catch (e: NumberFormatException) {
    "'${args[0]}' is not a valid number"
} catch (e: Exception) {
    "The original message was ${e.message}"
}

fun readTextFile(filePath: String) = try {
    File(filePath).readText()
} catch (e: FileNotFoundException) {
    "The file `$filePath` was not found"
} catch (e: IOException) {
    "An error occurred while reading the file: ${e.message}"
}