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

}