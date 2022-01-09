package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class TestTaskIO {
    private static AbstractTaskList arrayTaskList;
    private static Task t,t1,t2,t3,t4;

    @BeforeAll
    static void init(){
        arrayTaskList=TaskListFactory.createTaskList(TaskListFactory.ListTypes.ARRAY);
        t=new Task("Contract Signing", LocalDateTime.parse("2020-12-21T15:25:30")); //30
        t.setActive(true);
        t1=new Task("Taking introduction course",LocalDateTime.parse("2020-12-25T11:55:20")); //32
        t1.setActive(true);
        t2=new Task("Picking laptop",LocalDateTime.parse("2020-12-31T10:05:00")); //34
        t2.setActive(true);
        t3=new Task("Getting up early",LocalDateTime.parse("2020-12-24T06:00:00"),LocalDateTime.parse("2021-02-01T06:00:00"), Timestamp.ofDays(1)); //31 120 24
        t3.setActive(true);
        t4=new Task("working",LocalDateTime.parse("2020-12-26T09:00:00"),LocalDateTime.parse("2021-12-30T18:00:00"),Timestamp.ofDays(1));
        t4.setActive(true);
        arrayTaskList.add(t);
        arrayTaskList.add(t1);
        arrayTaskList.add(t2);
        arrayTaskList.add(t3);
        arrayTaskList.add(t4);
    }

    @Test
    public void TestBinary() throws FileNotFoundException {
        AbstractTaskList nueva= TaskListFactory.createTaskList(TaskListFactory.ListTypes.ARRAY);
        TaskIO.write(arrayTaskList,new File("C:\\Users\\Principal\\Documents\\file.json"));
        Assertions.assertTrue(Files.exists(Paths.get("C:\\Users\\Principal\\Documents\\file.json")));
        TaskIO.read(nueva,new File("C:\\Users\\Principal\\Documents\\file.json"));
        System.out.println(arrayTaskList.equals(nueva));
        Assertions.assertEquals(arrayTaskList,nueva);
    }

    @Test
    public void TestJSON() throws FileNotFoundException {
        AbstractTaskList nueva= TaskListFactory.createTaskList(TaskListFactory.ListTypes.ARRAY);
        TaskIO.writeText(arrayTaskList,new File("C:\\Users\\Principal\\Documents\\fileJSON.json"));
        Assertions.assertTrue(Files.exists(Paths.get("C:\\Users\\Principal\\Documents\\fileJSON.json")));
        TaskIO.readText(nueva,new File("C:\\Users\\Principal\\Documents\\fileJSON.json"));
        Assertions.assertEquals(arrayTaskList,nueva);
    }

}
