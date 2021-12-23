package mx.edu.j2se.estrada.tasks;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

/* Class AbstractTaskList
 *  This abstract class create the parent Class of the "ArrayTaskList" and "LinkedTaskList" classes,
 *  which shares the common methods size(),increase(),decrease() and incoming() but have their own implementation
 *  of the methods add(),remove() and getTask()
 *
 *  @author  Yael Estrada
 *  @version 1.0
 *  @since   12/1/2021
 */
public abstract class AbstractTaskList implements Cloneable{
    private int len;
    public int size(){
        return len;
    }
    public void increase(int i){len+=i;}
    public void decrease(int i){len-=i;}
    public void setLength(int i){len=i;}

    /*
     *  It returns a list of task that starts or repeats inside the interval from "from" to "to".
     *  @param    from             The start of the interval
     *  @param    to               The end of the interval
     *  @param    tipo             Enum type of the TaskList
     *  @returns  AbstractTaskList The list of task between the interval.
     */
    public AbstractTaskList incoming(LocalDateTime from, LocalDateTime to, TaskListFactory.ListTypes tipo){
        AbstractTaskList coming=TaskListFactory.createTaskList(tipo);
        /*this.getStream().forEach(t->{
            if(t.getTime()>=from&&t.getTime()<=to){
                coming.add(t);
            }
            else {
                if (t.getStartTime() < from) {
                    if (t.nextTimeAfter(from) != -1) {
                        coming.add(t);
                    }
                }
            }
        });*/
        this.getStream()
                .filter(t->((from.compareTo(t.getTime())<0)&&to.compareTo(t.getTime())>0)||(t.getTime().compareTo(from)<0&&t.nextTimeAfter(from)!=null))
                .forEach(t->coming.add(t));

        return coming;
    }



    @Override
    public String toString() {
        String s="AbstractTaskList{\n";
        Iterator<Task> it=this.iterator();
        while(it.hasNext()){
            s+=it.next().toString();
            s+=",\n";
        }
        s+="}";
        return s;
    }

    @Override
    public boolean equals(Object o) {
       AbstractTaskList at=(AbstractTaskList) o;
        Iterator<Task> it=this.iterator(),it2=at.iterator();
        while(it.hasNext()){
            if(!it.next().equals(it2.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        Iterator<Task> it=this.iterator();
        while(it.hasNext())
            hashCode = 31*hashCode + (it.next()==null ? 0 : it.next().hashCode());
        return hashCode;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract Stream<Task> getStream();
    public abstract Task getTask(int ind);
    public abstract void add(Task t);
    public abstract boolean remove(Task t);
    public abstract Iterator iterator();
}
