package mx.edu.j2se.estrada.tasks;

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
public class Task {
    private String title; //Description of the task
    private boolean active; //True if the task is active
    private int time; //Start time of a non-repeated task
    private int start,end,interval; //Information of a repeated task

    //Constructor of a Non-Repeated task
    public Task(String title,int time){
        this.title=title;
        this.time=time;
        this.active=false;
        this.start=this.end=this.interval=-1;
    }

    //Constructor of a Repeated task from start to end each interval time.
    public Task(String title, int start, int end, int interval){
        this.title=title;
        this.start=start;
        this.end=end;
        this.interval=interval;
        this.active=false;
        this.time=-1;
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
    public int getTime() {
        if(start!=-1)
            return this.start;
        else
            return time;
    }

    /*
        If the task is repeated it turns to a non-repeated task
     */
    public void setTime(int time) {
        this.time = time;
        if(this.start!=-1){
            this.start=-1;
            this.end=-1;
            this.interval=-1;
        }
    }

    public int getStartTime(){
        if(this.time!=-1)
            return this.time;
        return this.start;
    }

    public int getEndTime(){
        if(this.time!=-1)
            return this.time;
        return this.end;
    }

    public int getRepeatInterval(){
        if(this.time!=-1)
            return 0;
        return this.interval;
    }

    /*
        If the task is non-repeated it turns to a repeated task
     */
    public void setTime(int start,int end,int interval){
        if(this.time!=-1)
            this.time=-1;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }

    public boolean isRepeated(){
        return this.interval!=-1;
    }

    /*
     *  It returns the next start time of the task execution after the current time.
     *  If after the specified time  the task is not executed anymore, the method must
     *  return -1.
     *  @param    current   The current time
     *  @returns  int       The next start time task will execute
     */
    public int nextTimeAfter(int current){
        if(this.time!=-1){
            if(current<this.time)
                return this.time;
            return -1;
        }
        if(current>this.end)
            return -1;
        if(current<this.start)
            return this.start;
        return ((current/this.interval)+1)*this.interval;
    }
}
