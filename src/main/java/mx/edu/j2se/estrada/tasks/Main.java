package mx.edu.j2se.estrada.tasks;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class Main {
	
	public static void main(String[] args) {
		ArrayTaskList arrayTaskList=new ArrayTaskList();
		Task t,t1,t2,t3,t4;
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
		arrayTaskList.add(t);
		arrayTaskList.add(t1);
		arrayTaskList.add(t2);
		arrayTaskList.add(t3);
		arrayTaskList.add(t4);

		ArrayTaskList nueva= null;
		try {
			nueva = (ArrayTaskList) Tasks.incoming(arrayTaskList, LocalDateTime.parse("2020-12-23T11:55:20"),LocalDateTime.parse("2020-12-26T11:55:20"));
		} catch (Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println(nueva.toString());

		SortedMap<LocalDateTime, Set<Task>> calendar=Tasks.calendar(arrayTaskList,LocalDateTime.parse("2020-12-23T11:55:20"),LocalDateTime.parse("2020-12-26T11:55:20"));
		for(Map.Entry<LocalDateTime,Set<Task>> e: calendar.entrySet()){
			for(Task ta: e.getValue()){
				System.out.println(e.getKey()+" "+ta);
			}
		}
	}
	
}
