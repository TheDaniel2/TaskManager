import utilities.getValidNumber


val taskList = TaskList()

fun addTask() {
    print("What is task name?: ")
    val name: String = readln().uppercase()
    taskList.add(name)
}

fun deleteTask(){
    println("Which TASK do you want to delete?: ")
    showTaskList()
    val taskName = readln()
    val taskInfo = taskList.getTask(taskName)
    taskInfo?.let{
        taskList.delete(taskName)
    }
}

fun markAsDone(){
    println("Which TASK do you want to mark as done?: ")
    showTaskList()
    val taskName = readln()
    val taskInfo = taskList.getTask(taskName)
    taskInfo?.let {
        taskList.markAsDone(taskName)

        return
    }
    println("The name is not found, impossible to continue the operation.")
}


fun editTaskName(){
    println("Which TASK do you want to change the name?: ")
    showTaskList()
    val taskName = readln()
    val taskInfo = taskList.getTask(taskName)
    taskInfo?.let{
        println("Enter the new name (leave in black to not change): ")
        val newName = readln()
        if(newName != ""){
            taskList.editTaskName(taskName, newName)
        }
        return
    }
    println("The name is not found, impossible to continue the operation.")
}

fun showTaskList() {
    val tasks = taskList.getFormattedTasks()

    if (taskList.isEmpty()){
        println("There are no task to SHOW.\n")
    } else {
        tasks.forEach { println(it) }
    }
    println()

}

//fun confirmToContinue(ifYes: () -> Unit, taskName: String, action: String){
//    println("\n${ taskName } Will be $action\n Continue? [Y]es [N]O: ")
//    val confirm = readln()[0].lowercase()
//
//    if(confirm == "y"){
//        ifYes()
//    }
//}

//fun selectTask(action: String): Task {
//    println("Select the task that you want to be $action:\n")
//    showTaskList()
//
//    val index = getValidNumber(1..taskList.size()) - 1
//    val task = taskList.getTask(index)
//    return task
//}

//fun taskManager(action: String, function: (taskName: String) -> Unit){
//    val actionInUppercase = action.uppercase()
//
//    if(taskList.isEmpty()){
//        println("No tasks available to be $actionInUppercase.\n")
//        return
//    }
//
//    //val task = selectTask(actionInUppercase)
//    confirmToContinue({ function(task) }, task.name, actionInUppercase)
//}
