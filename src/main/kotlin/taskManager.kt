import utilities.getValidNumber


val taskList = TaskList()

fun addTask() {
    print("What is task name?: ")
    val name: String = readln().uppercase()
    taskList.add(name)
}

//fun deleteTask(){
//    taskManager("deleted") { task -> taskList.delete(task) }
//}
//
//fun markAsDone(){
//    taskManager("marked as done") { task -> task.isDone = true }
//}
//
//fun editTaskName(){
//    taskManager("edited") { task ->
//        println("Editing task name. Leave blank to maintain the original name.\n")
//
//        print("${task.name} -> New name: ")
//        val newName = readln().uppercase()
//
//        if (newName == "") {
//            return@taskManager
//        }
//
//        task.name = newName
//    }
//}

fun showTaskList() {
    val tasks = taskList.getFormattedTasks()

    if (!taskList.isEmpty()){
        println("There are no task to SHOW.\n")
    } else {
        tasks.forEach { println(it) }
    }
    println()

}

fun confirmToContinue(ifYes: () -> Unit, taskName: String, action: String){
    println("\n${ taskName } Will be $action\n Continue? [Y]es [N]O: ")
    val confirm = readln()[0].lowercase()

    if(confirm == "y"){
        ifYes()
    }
}

//fun selectTask(action: String): Task {
//    println("Select the task that you want to be $action:\n")
//    showTaskList()
//
//    val index = getValidNumber(1..taskList.size()) - 1
//    val task = taskList.getTask(index)
//    return task
//}

//fun taskManager(action: String, function: (Task) -> Unit){
//    val actionInUppercase = action.uppercase()
//
//    if(taskList.isEmpty()){
//        println("No tasks available to be $actionInUppercase.\n")
//        return
//    }

//    val task = selectTask(actionInUppercase)
//    confirmToContinue({ function(task) }, task.name, actionInUppercase)
//}
