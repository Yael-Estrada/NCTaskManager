package mx.edu.j2se.estrada.tasks;

/* Class AbstractTaskList
 *  This abstract class create the parent Class of the "ArrayTaskList" and "LinkedTaskList" classes,
 *  which shares the common methods size(),increase(),decrease() and incoming() but have their own implementation
 *  of the methods add(),remove() and getTask()
 *
 *  @author  Yael Estrada
 *  @version 1.0
 *  @since   12/1/2021
 */
public abstract class AbstractTaskList {
    protected int len;
    public int size(){
        return len;
    }
    public void increase(int i){len+=i;}
    public void decrease(int i){len-=i;}

    /*
     *  It returns a list of task that starts or repeats inside the interval from "from" to "to".
     *  @param    from             The start of the interval
     *  @param    to               The end of the interval
     *  @param    tipo             Enum type of the TaskList
     *  @returns  AbstractTaskList The list of task between the interval.
     */
    public AbstractTaskList incoming(int from,int to,TaskListFactory.ListTypes tipo){
        AbstractTaskList coming=TaskListFactory.createTaskList(tipo);

        for(int i=0;i<size();i++){
            Task t=getTask(i);
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
        }
        return coming;
    }

    public abstract Task getTask(int ind);
    public abstract void add(Task t);
    public abstract boolean remove(Task t);
}
