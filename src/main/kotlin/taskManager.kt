package main.kotlin

import main.kotlin.utilities.getValidNumber


val taskList : ArrayList<Task> = arrayListOf()

fun addTask(){
    print("What is task name?: ")
    val name: String = readln().uppercase()
    val newTask = Task(name)
    taskList.add(newTask)
    println("\n The task $name was been added\n")
}

fun deleteTask(){
    taskManager("deleted") { task -> taskList.remove(task) }
}

fun markAsDone(){
    taskManager("marked as done") { task -> task.isDone = true }
}

fun editTask(){
    taskManager("edited") { task ->
        println("Editing task name. Leave blank to maintain the original name.\n")

        print("${ task.name } -> New name: ")
        val newName = readln().uppercase()

        if (newName == "") {
            return@taskManager
        }

        task.name = newName
    }
}

fun showTaskList(){
    if(taskList.isEmpty()){
        println("No tasks available to be Showed.\n")
        return
    }

    for((index, task) in taskList.withIndex()){
        println("${index + 1}. $task")
    }
    println()
}

fun confirmToContinue(ifYes: () -> Unit, taskName: String, action: String){
    println("\n${ taskName } Will be $action\n Continue? [Y]es [N]O: ")
    val confirm = readln()[0].lowercase()

    if(confirm == "y"){
        ifYes()
        println("\n The task $taskName was been $action.\n")
    }
}

fun selectTask(action: String): Task {
    println("Select the task that you want to be $action:\n")
    showTaskList()

    val index = getValidNumber(1..taskList.size) - 1
    val task = taskList[index]
    return task
}

fun taskManager(action: String, function: (Task) -> Unit){
    val actionInUppercase = action.uppercase()

    if(taskList.isEmpty()){
        println("No tasks available to be $actionInUppercase.\n")
        return
    }

    val task = selectTask(actionInUppercase)
    confirmToContinue({ function(task) }, task.name, actionInUppercase)
}
