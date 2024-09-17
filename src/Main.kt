fun main(args: Array<String>) {
    noErrorHandling(args)
}

fun noErrorHandling(args: Array<String>) {
    val input = args[0]
    try {
        println("%.${2}f".format(input.toDouble()))
    } catch (e: NumberFormatException) {
        println("'$input' is not a valid number")
        println("The original cause was ${e.cause}")
        println("The original message was ${e.message}")
    }
}