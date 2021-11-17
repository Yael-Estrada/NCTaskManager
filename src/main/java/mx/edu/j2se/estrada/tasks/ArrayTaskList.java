package mx.edu.j2se.estrada.tasks;

/* Class ArrayTaskList
 *  This class implements a list of tasks that allows you to simultaneously work with
 *  several tasks. Tasks in the list can be repeated, the order of the tasks does not matter,
 *  but it should not change unless the tasks are added to or removed from the list.
 *
 *  @author  Yael Estrada
 *  @version 1.0
 *  @since   11/16/2021
 */
public class ArrayTaskList {
    private Task[] arr;
    private int len;

    public ArrayTaskList(){
        this.arr=new Task[0];
        len=0;
    }
    public int size(){
        return len;
    }

    /*
     *  Returns the task in the specified index inside the array.
     *  @param    ind       The index to be returned
     *  @returns  Task      The object Task at the specified index.
     */
    public Task getTask(int ind){
        return arr[ind];
    }

    /*
     *  Inserts a new task at the end of the array.
     *  @param    t         The task to be inserted
     */
    public void add(Task t){
        Task[] aux=new Task[len+1];
        if(len>0)
            System.arraycopy(arr,0,aux,0,len);
        len++;
        arr=aux;
        arr[len-1]=t;
    }

    /*
     *  It removes a Task element from the array if it exists, and returns true, if not exists
     *  returns false and doesn't remove any element.
     *  @param    t         The task to be removed.
     *  @returns  boolean   True if the element existed and was removed, otherwise false.
     */
    public boolean remove(Task t){
        Task[] aux=new Task[len-1];
        int j=0;
        boolean found=false;
        for(int i=0;i<len;i++){
            if(arr[i].equals(t)&&!found){
                found=true;
                continue;
            }
            if(len-1==0){
                aux=new Task[len];
            }
            aux[j++]=arr[i];
        }
        if(found) {
            arr = aux;
            len--;
        }
        return found;
    }

    /*
     *  It returns a list of task that starts or repeats inside the interval from "from" to "to".
     *  @param    from          The start of the interval
     *  @param    to            The end of the interval
     *  @returns  ArrayTaskList The list of task between the interval.
     */
    public ArrayTaskList incoming(int from,int to){
        ArrayTaskList coming=new ArrayTaskList();
        for(Task t:arr){
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

}
