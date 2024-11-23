package utilities

fun menu(options: Array<String>, functions: Array<() -> Unit>): Boolean{

    val max = options.size + 1

    for ((index, op) in options.withIndex()){
        println("${ index + 1 } - $op")
    }
    println("$max - EXIT")

    println("\nChoose a number to select the functionality.")
    val selectedOption = getValidNumber(1..max) - 1

    if(selectedOption + 1 == max){
        return false
    }

    println("\n--- ${options[selectedOption]} ---\n")
    functions[selectedOption]()

    return true
}