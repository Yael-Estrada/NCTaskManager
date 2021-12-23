package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.AbstractTaskList;
import mx.edu.j2se.estrada.tasks.LinkedTaskList;
import mx.edu.j2se.estrada.tasks.Task;
import mx.edu.j2se.estrada.tasks.TaskListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Iterator;

public class TestIterators {


    @Test
    void TestLinkedIterator() {
        AbstractTaskList at= TaskListFactory.createTaskList(TaskListFactory.ListTypes.LINKED);
        at.add(new Task("T1", LocalDateTime.parse("2020-01-01T00:00:00")));
        at.add(new Task("T2",LocalDateTime.parse("2020-01-04T00:00:00")));
        at.add(new Task("T3",LocalDateTime.parse("2020-01-23T00:00:00")));
        at.add(new Task("T4",LocalDateTime.parse("2020-03-05T00:00:00")));
        Iterator<Task> it=at.iterator();
        Assertions.assertEquals(at.getTask(0),it.next());
        Assertions.assertEquals(at.getTask(1),it.next());
        Iterator<Task> it2=at.iterator();
        Assertions.assertEquals(at.getTask(0),it2.next());
        Assertions.assertEquals(at.getTask(1),it2.next());
    }

    @Test
    void TestArrayIterator() {
        AbstractTaskList at= TaskListFactory.createTaskList(TaskListFactory.ListTypes.ARRAY);
        at.add(new Task("T1", LocalDateTime.parse("2020-01-01T00:00:00")));
        at.add(new Task("T2",LocalDateTime.parse("2020-01-04T00:00:00")));
        at.add(new Task("T3",LocalDateTime.parse("2020-01-23T00:00:00")));
        at.add(new Task("T4",LocalDateTime.parse("2020-03-05T00:00:00")));
        Iterator<Task> it=at.iterator();
        Assertions.assertEquals(at.getTask(0),it.next());
        Assertions.assertEquals(at.getTask(1),it.next());
        Iterator<Task> it2=at.iterator();
        Assertions.assertEquals(at.getTask(0),it2.next());
        Assertions.assertEquals(at.getTask(1),it2.next());
    }
}
