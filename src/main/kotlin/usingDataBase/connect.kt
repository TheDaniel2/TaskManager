package usingDataBase

import java.sql.Connection
import java.sql.DriverManager

fun connect(): Connection{
    val url = "jdbc:sqlite:tasks.db"
    return DriverManager.getConnection(url)
}