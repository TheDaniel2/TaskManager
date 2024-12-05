data class Task(var name: String, var isDone: Boolean = false){
    override fun toString(): String {
        return "${ this.name } - ${ if(this.isDone) "Done" else "Not Done" }"
    }
}



class TaskList(private val taskList: ArrayList<Task> = arrayListOf()) {
    fun add(newTaskName: String){
        //taskList.add(Task(newTaskName))
    }

    fun delete(task: Task){
        //taskList.remove(task)
    }

    fun isEmpty(): Boolean{
        //return taskList.isEmpty()
    }

    fun getAll(): MutableListIterator<Task>{
        //return taskList.listIterator()
    }

    fun getTask(index: Int): Task {
        //return taskList[index]
    }

    fun size(): Int{
        //return taskList.size
    }

}
