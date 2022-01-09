package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.AbstractTaskList;
import mx.edu.j2se.estrada.tasks.Task;
import mx.edu.j2se.estrada.tasks.TaskListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TestServices {
    private static AbstractTaskList at,at2;
    @BeforeAll
    static void before(){
        at= TaskListFactory.createTaskList(TaskListFactory.ListTypes.LINKED);
        at.add(new Task("T1",LocalDateTime.parse("2020-01-01T00:00:00")));
        at.add(new Task("T2",LocalDateTime.parse("2020-01-04T00:00:00")));
        at.add(new Task("T3",LocalDateTime.parse("2020-01-23T00:00:00")));
        at.add(new Task("T4",LocalDateTime.parse("2020-03-01T00:00:00")));
    }

    @Test
    public void test() throws CloneNotSupportedException {
        Assertions.assertEquals(at.toString(),"AbstractTaskList{\n" +
                "Task{title='T1', active=true, time=2020-01-01T00:00},\n" +
                "Task{title='T2', active=true, time=2020-01-04T00:00},\n" +
                "Task{title='T3', active=true, time=2020-01-23T00:00},\n" +
                "Task{title='T4', active=true, time=2020-03-01T00:00},\n" +
                "}");
        Assertions.assertEquals(at.hashCode(),132470944);
        //at2= TaskListFactory.createTaskList(TaskListFactory.ListTypes.LINKED);
        at2=(AbstractTaskList) at.clone();
        Assertions.assertEquals(at, at2);
        Assertions.assertEquals(at2.hashCode(),132470944);
        Assertions.assertEquals(at2.toString(),"AbstractTaskList{\n" +
                "Task{title='T1', active=true, time=2020-01-01T00:00},\n" +
                "Task{title='T2', active=true, time=2020-01-04T00:00},\n" +
                "Task{title='T3', active=true, time=2020-01-23T00:00},\n" +
                "Task{title='T4', active=true, time=2020-03-01T00:00},\n" +
                "}");
    }
}
