package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.Period;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedTaskListTest {
    private static LinkedTaskList LinkedTaskList;
    private static Task t,t1,t2,t3,t4;

    @BeforeAll
    static void beforeAll() {
        LinkedTaskList=new LinkedTaskList();
        t=new Task("Contract Signing", LocalDateTime.parse("2020-12-21T15:25:30")); //30
        t.setActive(true);
        t1=new Task("Taking introduction course",LocalDateTime.parse("2020-12-25T11:55:20")); //32
        t1.setActive(true);
        t2=new Task("Picking laptop",LocalDateTime.parse("2020-12-31T10:05:00")); //34
        t2.setActive(true);
        t3=new Task("Getting up early",LocalDateTime.parse("2020-12-24T06:00:00"),LocalDateTime.parse("2021-02-01T06:00:00"), Period.ofDays(1)); //31 120 24
        t3.setActive(true);
        t4=new Task("working",LocalDateTime.parse("2020-12-26T09:00:00"),LocalDateTime.parse("2021-12-30T18:00:00"),Period.ofDays(7));
        t4.setActive(true);
    }


    @Test
    @Order(1)
    void TestAdd() {

        LinkedTaskList.add(t);
        Assertions.assertEquals(1,LinkedTaskList.size());
        LinkedTaskList.add(t1);
        Assertions.assertEquals(2,LinkedTaskList.size());
    }

    @Test
    @Order(2)
    void TestRemove() {
        Assertions.assertEquals(2,LinkedTaskList.size());
        Assertions.assertTrue(LinkedTaskList.remove(t1));
        Assertions.assertEquals(1,LinkedTaskList.size());
        Assertions.assertFalse(LinkedTaskList.remove(t2));
        Assertions.assertEquals(1,LinkedTaskList.size());
    }

    @Test
    @Order(3)
    void TestgetTask() {
        LinkedTaskList.add(t1);
        LinkedTaskList.add(t2);
        Assertions.assertEquals(t1,LinkedTaskList.getTask(1));
    }

    @Test
    @Order(4)
    void TestIncoming(){
        Assertions.assertEquals(3,LinkedTaskList.size());
        LinkedTaskList.add(t3);
        LinkedTaskList.add(t4);
        AbstractTaskList coming=LinkedTaskList.incoming(LocalDateTime.parse("2020-12-30T10:05:00"),LocalDateTime.parse("2021-01-25T10:05:00"), TaskListFactory.ListTypes.ARRAY);
        Assertions.assertEquals(3,coming.size());
        Assertions.assertEquals(t3,coming.getTask(1));
    }

}
