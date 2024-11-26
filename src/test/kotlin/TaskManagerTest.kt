import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class TaskManagerTest {

    @Test
    fun testAddTask() {

        val taskList = TaskList()

        taskList.add("Test Task")
        val task = taskList.getTask(0)

        assertEquals(1, taskList.size())
        assertEquals("Test Task", task.name)
        assertFalse(task.isDone)
    }

    @Test
    fun testDeleteTask(){
        val tl = TaskList()

        tl.add("Test task 01")
        tl.add("Test task 02")

        val task01 = tl.getTask(0)

        assertEquals(2, tl.size())
        assertEquals("Test task 01", task01.name)

        tl.delete(task01)

        assertEquals(1, tl.size())
        assertEquals("Test task 02", tl.getTask(0).name)
    }

    @Test
    fun testMarkAsDone(){
        val tl = TaskList()

        tl.add("Test task 01")

        val task01 = tl.getTask(0)

        assertEquals("${task01.name} - Not Done", task01.toString())

        task01.isDone = true

        assertEquals("${task01.name} - Done", task01.toString())
    }
}