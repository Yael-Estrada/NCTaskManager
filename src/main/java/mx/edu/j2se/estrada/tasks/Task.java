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
        if(time<0){
            throw new IllegalArgumentException("El tiempo no puede ser negativo");
        }
        this.title=title;
        this.time=time;
        this.active=false;
        this.start=this.end=this.interval=-1;
    }

    //Constructor of a Repeated task from start to end each interval time.
    public Task(String title, int start, int end, int interval){
        if(start<0||end<0){
            throw new IllegalArgumentException("El tiempo no puede ser negativo");
        }
        if(start>end){
            throw new IllegalArgumentException("El inicio no puede ser mayor al final");
        }
        if(interval<=0){
            throw new IllegalArgumentException("El intevalo no puede ser menor o igual a 0");
        }
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
        if(!this.isActive())
            return -1;
        if(start!=-1)
            return this.start;
        else
            return time;
    }

    /*
        If the task is repeated it turns to a non-repeated task
     */
    public void setTime(int time) {
        if(time<0){
            throw new IllegalArgumentException("El tiempo no puede ser negativo");
        }
        this.time = time;
        if(this.start!=-1){
            this.start=-1;
            this.end=-1;
            this.interval=-1;
        }
    }

    public int getStartTime(){
        return this.getTime();
    }

    public int getEndTime(){
        if(!this.isActive())
            return -1;
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
        if(start<0||end<0){
            throw new IllegalArgumentException("El tiempo no puede ser negativo");
        }
        if(start>end){
            throw new IllegalArgumentException("El inicio no puede ser mayor al final");
        }
        if(interval<=0){
            throw new IllegalArgumentException("El intevalo no puede ser menor o igual a 0");
        }
        if(this.time!=-1)
            this.time=-1;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }

    public boolean isRepeated(){
        return this.getRepeatInterval()!=0;
    }

    /*
     *  It returns the next start time of the task execution after the current time.
     *  If after the specified time  the task is not executed anymore, the method must
     *  return -1.
     *  @param    current   The current time
     *  @returns  int       The next start time task will execute
     */
    public int nextTimeAfter(int current){
        if(current<0){
            throw new IllegalArgumentException("El tiempo actual no puede ser menor a 0");
        }
        if(!this.isActive())
            return -1;
        if(this.time!=-1){
            if(current<this.time)
                return this.time;
            return -1;
        }
        if(current>=this.end)
            return -1;
        if(current<this.start)
            return this.start;

        //Caso de prueba start=5 end=20 inter=6
        //x=5+6y      lo=0 hi=20/6=3
        int lo=0,hi=this.end/this.interval,mid,y=-1;
        while(lo<=hi){
            mid=(hi+lo)>>1;
            if((this.start+this.interval*mid)>current){
                y=mid;
                hi=mid-1;
            }
            else{
                lo=mid+1;
            }
        }

        if((this.start+this.interval*y)>=this.end||y==-1){
            return -1;
        }
        return this.start+this.interval*y;
    }
}
