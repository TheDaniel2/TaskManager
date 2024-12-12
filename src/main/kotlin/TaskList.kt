import usingDataBase.connect
import java.sql.SQLException

//data class Task(var name: String, var isDone: Boolean = false){
//    override fun toString(): String {
//        return "${ this.name } - ${ if(this.isDone) "Done" else "Not Done" }"
//    }
//}


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

    fun delete(name: String){
        val connection = connect()
        try {
            val preparedStatement = connection.prepareStatement(
                "DELETE FROM tasks WHERE name = ?"
            )
            preparedStatement.setString(1, name)
            preparedStatement.executeUpdate()

            println("\nTask $name has been deleted successfully!\n")
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

    fun getTask(name: String): Triple<Int, String, Boolean>?{
        val connection = connect()
        val preparedStatement = connection.prepareStatement("SELECT id, name, isDone FROM tasks WHERE name = ?")
        preparedStatement.setString(1, name)

        val result = preparedStatement.executeQuery()

        return if (result.next()) {
            val taskID = result.getInt("id")
            val taskName = result.getString("name")
            val taskIsDone = result.getBoolean("isDone")

            result.close()
            preparedStatement.close()
            connection.close()

            Triple(taskID, taskName, taskIsDone)
        } else {
            result.close()
            preparedStatement.close()
            connection.close()

            null
        }
    }

    fun size(): Int{
        val connection = connect()
        val statement = connection.createStatement()
        val result = statement.executeQuery("SELECT COUNT(*) FROM tasks")

        val size = result.getInt(1)

        connection.close()
        statement.close()
        result.close()

        return size
    }

}
