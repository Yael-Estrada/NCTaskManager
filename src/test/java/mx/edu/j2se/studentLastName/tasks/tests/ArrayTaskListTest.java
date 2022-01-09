package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrayTaskListTest {
    private static ArrayTaskList arrayTaskList;
    private static Task t,t1,t2,t3,t4;

    @BeforeAll
    static void beforeAll() {
        arrayTaskList=new ArrayTaskList();
        t=new Task("Contract Signing",LocalDateTime.parse("2020-12-21T15:25:30")); //30
        t.setActive(true);
        t1=new Task("Taking introduction course",LocalDateTime.parse("2020-12-25T11:55:20")); //32
        t1.setActive(true);
        t2=new Task("Picking laptop",LocalDateTime.parse("2020-12-31T10:05:00")); //34
        t2.setActive(true);
        t3=new Task("Getting up early",LocalDateTime.parse("2020-12-24T06:00:00"),LocalDateTime.parse("2021-02-01T06:00:00"), Timestamp.ofDays(1)); //31 120 24
        t3.setActive(true);
        t4=new Task("working",LocalDateTime.parse("2020-12-26T09:00:00"),LocalDateTime.parse("2021-12-30T18:00:00"), Timestamp.ofDays(7));
        t4.setActive(true);
    }


    @Test
    @Order(1)
    void TestAdd() {

        arrayTaskList.add(t);
        Assertions.assertEquals(1,arrayTaskList.size());
        arrayTaskList.add(t1);
        Assertions.assertEquals(2,arrayTaskList.size());
    }

    @Test
    @Order(2)
    void TestRemove() {
        Assertions.assertEquals(2,arrayTaskList.size());
        Assertions.assertTrue(arrayTaskList.remove(t1));
        Assertions.assertEquals(1,arrayTaskList.size());
        Assertions.assertFalse(arrayTaskList.remove(t2));
        Assertions.assertEquals(1,arrayTaskList.size());
    }

    @Test
    @Order(3)
    void TestgetTask() {
        arrayTaskList.add(t1);
        arrayTaskList.add(t2);
        Assertions.assertEquals(t1,arrayTaskList.getTask(1));
    }

    @Test
    @Order(4)
    void TestIncoming(){
        Assertions.assertEquals(3,arrayTaskList.size());
        arrayTaskList.add(t3);
        arrayTaskList.add(t4);
        AbstractTaskList coming=arrayTaskList.incoming(LocalDateTime.parse("2020-12-30T10:05:00"),LocalDateTime.parse("2021-01-25T10:05:00"), TaskListFactory.ListTypes.ARRAY);
        Assertions.assertEquals(3,coming.size());
        Assertions.assertEquals(t3,coming.getTask(1));
    }

}
