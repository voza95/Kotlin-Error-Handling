import kotlin.system.exitProcess

fun main(args: Array<String>) {
    noErrorHandling(args)
}

/***
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
fun noErrorHandling(args: Array<String>) {
    val input = args[0]
    try {
        println("%.${2}f".format(input.toDouble()))
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Please provide at least one number.")
        println("The original cause was ${e.cause}")
        println("The original message was ${e.message}")
    } catch (e: NumberFormatException) {
        println("'$input' is not a valid number")
        println("The original cause was ${e.cause}")
        println("The original message was ${e.message}")
    } catch (e: Exception) {
        println("The original cause was ${e.cause}")
        println("The original message was ${e.message}")
    } finally {
        println("Finally block")
    }
    println("Bye!!!")
}