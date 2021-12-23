package mx.edu.j2se.estrada.tasks;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;

public class Tasks {

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        AbstractTaskList abs=null;
        if(tasks instanceof ArrayTaskList){
            abs=TaskListFactory.createTaskList(TaskListFactory.ListTypes.ARRAY);
        }
        else if(tasks instanceof LinkedTaskList){
            abs=TaskListFactory.createTaskList(TaskListFactory.ListTypes.LINKED);
        }
        else
            throw new IllegalArgumentException("El objeto iterable no es aceptado");
        Iterator<Task> it=tasks.iterator();
        while(it.hasNext()){
            Task t=it.next();
            if(t.nextTimeAfter(from)!=null&&t.nextTimeAfter(from).compareTo(from)>=0&&t.nextTimeAfter(from).compareTo(to)<0){
                abs.add(t);
            }
        }
        return (Iterable<Task>) abs;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        SortedMap<LocalDateTime, Set<Task>> mapa=new TreeMap<>();
        try{
        AbstractTaskList abs=(AbstractTaskList) Tasks.incoming(tasks,start,end);
        Iterator<Task> it=abs.iterator();
        while(it.hasNext()){
            Task t=it.next();
            if(t.isRepeated()){
                if(t.getTime().compareTo(start)<0){
                    t.setTime(t.nextTimeAfter(start),t.getEndTime(),t.getRepeatInterval());
                }
                while(t.getTime().compareTo(end)<=0){
                    if(!mapa.containsKey(t.getTime())){
                        mapa.put(t.getTime(),new HashSet<>());
                    }
                    Task tmp=(Task)t.clone();
                    mapa.get(tmp.getTime()).add(tmp);
                    t.setTime(t.getTime().plus(t.getRepeatInterval()),t.getEndTime(),t.getRepeatInterval());
                }
            }
            else{
                if(!mapa.containsKey(t.getTime())){
                    mapa.put(t.getTime(),new HashSet<>());
                }
                mapa.get(t.getTime()).add(t);
            }
        }
        } catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return mapa;
    }

}
