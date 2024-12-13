import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class TaskManagerTest {

    @Test
    fun testAddTask() {
        val taskList = TaskList()
        val currentSize = taskList.size()
        val newTaskName = "testTask01 from testAddTask"

        try {
            taskList.add(newTaskName)

            assertEquals(currentSize + 1, taskList.size())

            val task = taskList.getTask(newTaskName)

            assertNotNull(task, "The task was not found in the database")
            task?.let { (_, name, isDone) ->
                assertEquals(newTaskName, name)
                assertEquals(false, isDone)
            }
        } finally {
            taskList.delete(newTaskName)
            assertNull(taskList.getTask(newTaskName), "Failed to clean up the test task with the new name.")
        }


    }

    @Test
    fun testDeleteTask(){
        val taskList = TaskList()
        val currentSize = taskList.size()
        val newTaskName01 = "testTask01 from testDeleteTask"

        try {
            taskList.add(newTaskName01)
            assertEquals(currentSize + 1, taskList.size())

            val task = taskList.getTask(newTaskName01)
            assertNotNull(task, "The task was not found in the database")
            task?.let{ (_, name, _) ->
                assertEquals(newTaskName01, name)
            }
        }finally {
            taskList.delete(newTaskName01)
            assertNull(taskList.getTask(newTaskName01), "Failed to clean up the test task with the new name.")
        }
    }

//    @Test
//    fun testMarkAsDone(){
//        val tl = TaskList()
//
//        tl.add("Test task")
//
//        val task = tl.getTask(0)
//
//        assertEquals("${task.name} - Not Done", task.toString())
//
//        task.isDone = true
//
//        assertEquals("${task.name} - Done", task.toString())
//    }
//

    @Test
    fun testEditName() {
        val taskList = TaskList()
        val theOriginalName = "Test task from testEditName"
        val newName = "Test task 02"

        try {

            taskList.add(theOriginalName)


            val oldTask = taskList.getTask(theOriginalName)
            assertNotNull(oldTask, "The task with the original name was not found in the database.")


            taskList.editTaskName(theOriginalName, newName)


            assertNull(taskList.getTask(theOriginalName), "The task with the old name still exists in the database.")


            val taskWithNewName = taskList.getTask(newName)
            assertNotNull(taskWithNewName, "The task with the new name was not found in the database.")

            taskWithNewName?.let { (_, updatedName, _) ->
                assertEquals(newName, updatedName)
            }
        } finally {
            taskList.delete(newName)
            assertNull(taskList.getTask(newName), "Failed to clean up the test task with the new name.")
        }
    }
}
