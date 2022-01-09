package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.Task;
import mx.edu.j2se.estrada.tasks.Timestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TaskTest {
    @Test
    void Test1() {
        Task task=new Task("First Day of Job", LocalDateTime.parse("2020-01-01T00:00:00"));
        Task task2=new Task("Wake up in the morning",LocalDateTime.parse("2020-01-01T06:00:00"),LocalDateTime.parse("2021-12-31T06:00:00"),Timestamp.ofDays(5));
        Assertions.assertFalse( !task.isActive());
        task2.setActive(true);
        Assertions.assertTrue(task2.isActive());
        task.setActive(true);
        Assertions.assertEquals(Timestamp.ofDays(5),task2.getRepeatInterval());
        Assertions.assertEquals(LocalDateTime.parse("2020-01-16T06:00:00"),task2.nextTimeAfter(LocalDateTime.parse("2020-01-13T00:00:00")));
        Assertions.assertEquals(null,task2.nextTimeAfter(LocalDateTime.parse("2022-01-01T00:00:00")));
    }

    @Test
    void TestNextTimeAfter() {
        Task t=new Task("T1",LocalDateTime.parse("2022-01-05T00:00:00"),LocalDateTime.parse("2022-01-20T00:00:00"), Timestamp.ofHours(12));
        Task t1=new Task("T1",LocalDateTime.parse("2020-01-01T00:00:00"),LocalDateTime.parse("2022-01-21T00:00:00"), Timestamp.ofDays(24));
        t.setActive(true);
        t1.setActive(true);
        Assertions.assertEquals(LocalDateTime.parse("2022-01-10T12:00:00"),t.nextTimeAfter(LocalDateTime.parse("2022-01-10T00:00:00")));
        Assertions.assertEquals(LocalDateTime.parse("2020-01-25T00:00:00"),t1.nextTimeAfter(LocalDateTime.parse("2020-01-01T00:00:00")));
    }
}
