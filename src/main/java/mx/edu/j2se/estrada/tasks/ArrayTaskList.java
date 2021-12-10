package mx.edu.j2se.estrada.tasks;

import java.util.Arrays;
import java.util.Iterator;

/* Class ArrayTaskList
 *  This class implements a list of tasks that allows you to simultaneously work with
 *  several tasks. Tasks in the list can be repeated, the order of the tasks does not matter,
 *  but it should not change unless the tasks are added to or removed from the list.
 *
 *  @author  Yael Estrada
 *  @version 1.1
 *  @since   12/1/2021
 */
public class ArrayTaskList extends AbstractTaskList implements Iterable{
    private Task[] arr;

    public ArrayTaskList(){
        this.arr=new Task[0];
        super.len=0;
    }

    /*
     *  Returns the task in the specified index inside the array.
     *  @param    ind       The index to be returned
     *  @returns  Task      The object Task at the specified index.
     */

    public Task getTask(int ind){
        if(ind<0||ind>=this.size()){
            throw new IndexOutOfBoundsException("El indice está fuera de los límites");
        }
        return arr[ind];
    }

    /*
     *  Inserts a new task at the end of the array.
     *  @param    t         The task to be inserted
     */
    public void add(Task t){
        if(t==null){
            throw new IllegalArgumentException("La tarea no puede ser null");
        }
        Task[] aux=new Task[this.size()+1];
        if(this.size()>0)
            System.arraycopy(arr,0,aux,0,this.size());
        this.increase(1);
        arr=aux;
        arr[this.size()-1]=t;
    }


    /*
     *  It removes a Task element from the array if it exists, and returns true, if not exists
     *  returns false and doesn't remove any element.
     *  @param    t         The task to be removed.
     *  @returns  boolean   True if the element existed and was removed, otherwise false.
     */
    public boolean remove(Task t){
        Task[] aux=new Task[this.size()-1];
        int j=0;
        boolean found=false;
        for(int i=0;i<this.size();i++){
            if(arr[i].equals(t)&&!found){
                found=true;
                continue;
            }
            if(this.size()-1==0){
                aux=new Task[this.size()];
            }
            aux[j++]=arr[i];
        }
        if(found) {
            arr = aux;
            this.decrease(1);
        }
        return found;
    }

    @Override
    public Iterator iterator() {
        return Arrays.stream(arr).iterator();
    }


}
