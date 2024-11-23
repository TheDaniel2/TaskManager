data class Task(var name: String, var isDone: Boolean = false){
    override fun toString(): String {
        return "${ this.name } - ${ if(this.isDone) "Done" else "Not Done" }"
    }
}
