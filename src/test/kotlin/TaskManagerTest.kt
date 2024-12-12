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
            assertEquals(null, taskList.getTask(newTaskName01))
        }
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
//    @Test
//    fun testEditName(){
//        val tl = TaskList()
//
//        val theOriginalName = "Test task"
//        val newName = "Test task 02"
//
//        tl.add(theOriginalName)
//
//        val task = tl.getTask(0)
//
//        assertEquals("Test task", task.name)
//
//        task.name = newName
//
//        assertEquals(newName, task.name)
//    }
//}
