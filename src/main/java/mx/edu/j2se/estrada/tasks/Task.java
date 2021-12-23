package mx.edu.j2se.estrada.tasks;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;

/*
 *  Class Task
 *  Implements an application to create and manage Tasks Repeated and Non-Repeated
 *  Tasks have some text that describes the details of the task, such as "Cleaning the room."
 *  In addition, tasks can be active or inactive . For example, during the holiday, }
 *  task "Morning run" may be inactive and temporarily not performed.
 *  Tasks can be scheduled to be performed once, for example "Meeting in a cafe on June 26 at
 *  18:00" or the task can be scheduled to be performed regularly over a period of time at a
 *  given interval (in hours), such as "Morning run from June 1 to June 5 every day at 8:00"
 *
 *  @author  Yael Estrada
 *  @version 1.0
 *  @since   11/10/2021
 */
public class Task implements Cloneable{
    private String title; //Description of the task
    private boolean active; //True if the task is active
    private LocalDateTime time; //Start time of a non-repeated task
    private LocalDateTime start,end;
    private TemporalAmount interval; //Information of a repeated task

    //Constructor of a Non-Repeated task
    public Task(String title,LocalDateTime time){
        if(time==null){
            throw new IllegalArgumentException("La fecha no puede ser null");
        }
        this.title=title;
        this.time=time;
        this.active=true;
        this.start=this.end=null;
        this.interval=null;
    }

    //Constructor of a Repeated task from start to end each interval time.
    public Task(String title, LocalDateTime start, LocalDateTime end, TemporalAmount interval){
        if(start==null){
            throw new IllegalArgumentException("La fecha de inicio no puede ser null");
        }
        if(end==null){
            throw new IllegalArgumentException("La fecha de fin no puede ser null");
        }
        if(interval==null){
            throw new IllegalArgumentException("El intervalo no puede ser null");
        }
        if(start.compareTo(end)>0){
            throw new IllegalArgumentException("La fecha de inicio no puede ser mayor a la de fin");
        }
        this.title=title;
        this.start=start;
        this.end=end;
        this.interval=interval;
        this.active=true;
        this.time=null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active=active;
    }

    /*
        If the task is repeated, it returns the start time of the task
     */
    public LocalDateTime getTime() {
        if(!this.isActive())
            return null;
        if(start!=null)
            return this.start;
        else
            return time;
    }

    /*
        If the task is repeated it turns to a non-repeated task
     */
    public void setTime(LocalDateTime time) {
        if(time==null){
            throw new IllegalArgumentException("La fecha no puede ser null");
        }
        this.time = time;
        if(this.start!=null){
            this.start=null;
            this.end=null;
            this.interval=null;
        }
    }

    public LocalDateTime getStartTime(){
        return this.getTime();
    }

    public LocalDateTime getEndTime(){
        if(!this.isActive())
            return null;
        if(this.time!=null)
            return this.time;
        return this.end;
    }

    public TemporalAmount getRepeatInterval(){
        return this.interval;
    }

    /*
        If the task is non-repeated it turns to a repeated task
     */
    public void setTime(LocalDateTime start,LocalDateTime end,TemporalAmount interval){
        if(start==null){
            throw new IllegalArgumentException("La fecha de inicio no puede ser null");
        }
        if(end==null){
            throw new IllegalArgumentException("La fecha de fin no puede ser null");
        }
        if(interval==null){
            throw new IllegalArgumentException("El intervalo no puede ser null");
        }
        if(start.compareTo(end)>0){
            throw new IllegalArgumentException("La fecha de inicio no puede ser mayor a la de fin");
        }
        if(this.time!=null)
            this.time=null;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }

    public boolean isRepeated(){
        return this.getRepeatInterval()!=null;
    }

    /*
     *  It returns the next start time of the task execution after the current time.
     *  If after the specified time  the task is not executed anymore, the method must
     *  return -1.
     *  @param    current   The current time
     *  @returns  int       The next start time task will execute
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current){
        if(current==null){
            throw new IllegalArgumentException("El tiempo actual no puede ser null");
        }
        if(!this.isActive())
            return null;
        if(this.time!=null){
            if(current.compareTo(this.time)<0)
                return this.time;
            return null;
        }
        if(current.compareTo(this.end)>=0)
            return null;
        if(current.compareTo(this.start)<0)
            return this.start;

        LocalDateTime actual=this.start;
        while(actual.compareTo(this.end)<0){
            if(actual.compareTo(current)>0){
                break;
            }
            actual=actual.plus(this.interval);
        }
        if(actual.equals(this.end)){
            return null;
        }
        return actual;
    }

    @Override
    public int hashCode() {
        int hash=1;
        hash+=title.hashCode();
        if(isRepeated()){
            hash+=Math.abs(interval.hashCode()+start.hashCode()+end.hashCode());
        }
        else
            hash+=Math.abs(time.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Task t=(Task)obj;
        if(this.isRepeated()){
            return this.title==t.title&&this.active==t.active&&this.interval==t.interval&&this.start.equals(t.start)
                    &&this.end.equals(t.end);
        }
        else{
            return this.title==t.title&&this.active==t.active&&this.time.equals(t.time);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        if(isRepeated()){
            return "Task{" +
                    "title='" + title + '\'' +
                    ", active=" + active +
                    ", start=" + start +
                    ", end=" + end +
                    ", interval=" + interval +
                    '}';
        }
        return "Task{" +
                "title='" + title + '\'' +
                ", active=" + active +
                ", time=" + time +
                '}';

    }
}
