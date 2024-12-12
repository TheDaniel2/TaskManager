import usingDataBase.connect
import java.sql.SQLException

data class Task(var name: String, var isDone: Boolean = false){
    override fun toString(): String {
        return "${ this.name } - ${ if(this.isDone) "Done" else "Not Done" }"
    }
}


class TaskList {
    init{
        val connection = connect()
        val statement = connection.createStatement()
        statement.executeUpdate(
            """
            CREATE TABLE IF NOT EXISTS tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                name TEXT NOT NULL,
                isDone BOOLEAN NOT NULL
            )
        """.trimIndent()
        )
        statement.close()
        connection.close()
    }

    fun add(newTaskName: String) {
        val connection = connect()
        try {
            val preparedStatement = connection.prepareStatement(
                "INSERT INTO tasks (name, isDone) VALUES (?, ?)"
            )
            preparedStatement.setString(1, newTaskName)
            preparedStatement.setBoolean(2, false)
            preparedStatement.executeUpdate() // Execute the SQL COMMAND

            println("\nTask '$newTaskName' added successfully!\n")
        } catch (e: SQLException) {
            println("Error adding task: ${e.message}")
        } finally {
            connection.close() // Close the connection
        }
    }

    fun delete(id: Int){
        val connection = connect()
        try {
            val preparedStatement = connection.prepareStatement(
                "DELETE FROM tasks WHERE (id) VALUES (?)"
            )
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()

            println("\nTask $id has been deleted successfully!\n")
        } catch (e: SQLException) {
            println("Error delete task: ${e.message}")
        } finally {
            connection.close()
        }
    }

    fun isEmpty(): Boolean{
        val connection = connect()
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(
            """
            SELECT name
            FROM sqlite_master
            WHERE type = 'table' AND name NOT LIKE 'sqlite_%'
            """.trimIndent()
        )

        val isEmpty = resultSet.next()// Checks if there is at least one result

        resultSet.close()
        statement.close()
        connection.close()

        return isEmpty
    }

    fun getFormattedTasks(): List<String> {
        val connection = connect()
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(
        """
            SELECT id, name, isDone
            FROM tasks
            ORDER BY id
            """
        )

        val tasks = mutableListOf<String>()

        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val name = resultSet.getString("name")
            val status = if (resultSet.getBoolean("isDone")) "Done" else "Not Done"
            tasks.add("$id. $name - $status")
        }

        resultSet.close()
        statement.close()
        connection.close()

        return tasks
    }


    fun getTask(index: Int){
        //return taskList[index]
    }

    fun size(){
        //return taskList.size
    }

}
