package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void Test1() {
        Task task=new Task("First Day of Job",0);
        Task task2=new Task("Wake up in the morning",0,744,24);
        Assertions.assertFalse(task.isActive());
        task2.setActive(true);
        Assertions.assertTrue(task2.isActive());
        task.setActive(true);
        Assertions.assertEquals(24,task2.getRepeatInterval());
        Assertions.assertEquals(552,task2.nextTimeAfter(542));
        Assertions.assertEquals(-1,task2.nextTimeAfter(800));
    }

    @Test
    void TestNextTimeAfter() {
        Task t=new Task("T1",5,20,6);
        Task t1=new Task("T1",0,744,24);
        t.setActive(true);
        t1.setActive(true);
        Assertions.assertEquals(11,t.nextTimeAfter(10));
        Assertions.assertEquals(-1,t.nextTimeAfter(18));
        Assertions.assertEquals(5,t.nextTimeAfter(0));
        Assertions.assertEquals(-1,t.nextTimeAfter(22));
        Assertions.assertEquals(17,t.nextTimeAfter(15));
        Assertions.assertEquals(240,t1.nextTimeAfter(226));
    }
}
