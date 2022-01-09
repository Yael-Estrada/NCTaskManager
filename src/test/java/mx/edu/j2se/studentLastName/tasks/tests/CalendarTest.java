package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.*;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalendarTest {
    private static LinkedTaskList arr;
    private static Task t,t1,t2,t3,t4;

    @BeforeAll
    static void init() {
        arr= new LinkedTaskList();
        t=new Task("Lunch with a beautiful girl", LocalDateTime.parse("2022-08-24T16:00"));
        t1=new Task("Morning run",LocalDateTime.parse("2022-03-01T08:15"),LocalDateTime.parse("2022-09-01T08:15"), Timestamp.ofDays(1));
        t2=new Task("Taking medication",LocalDateTime.parse("2022-08-20T08:15"),LocalDateTime.parse("2022-08-28T08:15"), Timestamp.ofHours(12));
        t3=new Task("Meeting with friends",LocalDateTime.parse("2022-09-01T18:00"));
        arr.add(t);
        arr.add(t1);
        arr.add(t2);
        arr.add(t3);
        System.out.println(arr);
    }

    @Test
    @Order(1)
    void testIncoming() {
        AbstractTaskList nueva= null;
        try {
            nueva = (AbstractTaskList) Tasks.incoming(arr, LocalDateTime.parse("2022-07-23T11:55:20"),LocalDateTime.parse("2022-08-26T11:55:20"));
        } catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(nueva);
        Assertions.assertEquals(3,nueva.size());
    }

    @Test
    @Order(2)
    void testCalendar() {
        SortedMap<LocalDateTime, Set<Task>> calendar=Tasks.calendar(arr,LocalDateTime.parse("2022-08-25T08:00"),LocalDateTime.parse("2022-08-26T08:00"));
        for(Map.Entry<LocalDateTime,Set<Task>> e: calendar.entrySet()){
            for(Task ta: e.getValue()){
                System.out.println(e.getKey()+" "+ta);
            }
        }
        Assertions.assertEquals(2,calendar.size());
        Assertions.assertEquals(2,calendar.get(LocalDateTime.parse("2022-08-25T08:15")).size());
        Assertions.assertEquals(1,calendar.get(LocalDateTime.parse("2022-08-25T20:15")).size());
    }
}
