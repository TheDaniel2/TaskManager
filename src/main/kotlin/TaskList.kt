class TaskList(private val taskList: ArrayList<Task> = arrayListOf()) {
    fun add(newTaskName: String){
        taskList.add(Task(newTaskName))
    }

    fun delete(task: Task){
        taskList.remove(task)
    }

    fun isEmpty(): Boolean{
        return taskList.isEmpty()
    }

    fun getAll(): MutableListIterator<Task>{
        return taskList.listIterator()
    }

    fun getTask(index: Int): Task {
        return taskList[index]
    }

    fun size(): Int{
        return taskList.size
    }

}
