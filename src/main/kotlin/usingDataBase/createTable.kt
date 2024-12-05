package usingDataBase

fun createTable(){
    val connection = connect()
    val statement = connection.createStatement()
    statement.executeUpdate(
        """
            CREATE TABLE IF NOT EXISTS tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                title INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                isDone BOOLEAN NOT NULL
            )
        """.trimIndent()
    )
    statement.close()
    connection.close()
}