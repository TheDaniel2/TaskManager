package main.kotlin.utilities

fun getValidNumber(range: IntRange): Int {
    var option: Int

    while(true){

        print("Enter a number: ")
        option = readlnOrNull()?.toIntOrNull() ?: -1

        if(range.contains(option)){
            break
        }

        println("Enter a valid option.")
    }

    return option
}