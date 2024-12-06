import utilities.menu


fun main() {
    do{
        println("--- Welcome to the MY TASK MANAGER SYSTEM ---\n")

        val options = arrayOf("ADD NEW TASK", "SHOW TASK LIST", "MARK TASK AS DONE", "DELETE TASK", "EDIT TASK NAME")

        val functions = arrayOf(
            { addTask() },
            { showTaskList() },
//            { markAsDone() },
//            { deleteTask() },
//            { editTaskName() }
        )

    }while(menu(options, functions))
}
