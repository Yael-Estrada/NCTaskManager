package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.AbstractTaskList;
import mx.edu.j2se.estrada.tasks.Task;
import mx.edu.j2se.estrada.tasks.TaskListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestServices {
    private static AbstractTaskList at,at2;
    private static Task t;
    @BeforeAll
    static void before(){
        t=new Task("T0",124,223,12);
        at= TaskListFactory.createTaskList(TaskListFactory.ListTypes.LINKED);
        at.add(new Task("T1",0));
        at.add(new Task("T2",4));
        at.add(new Task("T3",23));
        at.add(new Task("T4",65));
    }

    @Test
    public void test() throws CloneNotSupportedException {
        Assertions.assertEquals(at.toString(),"AbstractTaskList{\n" +
                "Task{title='T1', active=false, time=0},\n" +
                "Task{title='T2', active=false, time=4},\n" +
                "Task{title='T3', active=false, time=23},\n" +
                "Task{title='T4', active=false, time=65},\n" +
                "}");
        Assertions.assertEquals(at.hashCode(),86016);
        //at2= TaskListFactory.createTaskList(TaskListFactory.ListTypes.LINKED);
        at2=(AbstractTaskList) at.clone();
        Assertions.assertTrue(at.equals(at2));
        Assertions.assertEquals(at2.hashCode(),86016);
        Assertions.assertEquals(at2.toString(),"AbstractTaskList{\n" +
                "Task{title='T1', active=false, time=0},\n" +
                "Task{title='T2', active=false, time=4},\n" +
                "Task{title='T3', active=false, time=23},\n" +
                "Task{title='T4', active=false, time=65},\n" +
                "}");
    }
}
