package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.ArrayTaskList;
import mx.edu.j2se.estrada.tasks.Task;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrayTaskListTest {
    private static ArrayTaskList arrayTaskList;
    private static Task t,t1,t2,t3,t4;

    @BeforeAll
    static void beforeAll() {
        arrayTaskList=new ArrayTaskList();
        t=new Task("Contract Signing",30);
        t1=new Task("Taking introduction course",32);
        t2=new Task("Picking laptop",34);
        t3=new Task("Getting up early",31,120,24);
        t4=new Task("working",35,100,8);
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
        ArrayTaskList coming=arrayTaskList.incoming(33,60);
        Assertions.assertEquals(3,coming.size());
        Assertions.assertEquals(t3,coming.getTask(1));
    }

}
