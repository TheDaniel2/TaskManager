import main.kotlin.Task
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class TaskManagerTest {

    @Test
    fun testAddTask() {

        val taskList = arrayListOf<Task>()


        val newTask = Task("Test Task")
        taskList.add(newTask)


        assertEquals(1, taskList.size)
        assertEquals("Test Task", taskList[0].name)
        assertFalse(taskList[0].isDone)
    }

}