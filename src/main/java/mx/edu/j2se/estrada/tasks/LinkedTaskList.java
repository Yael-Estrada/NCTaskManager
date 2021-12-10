package mx.edu.j2se.estrada.tasks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* Class LinkedTaskList
     *  This class implements a list of tasks that allows you to simultaneously work with
     *  several tasks. Tasks in the list can be repeated, the order of the tasks does not matter,
     *  but it should not change unless the tasks are added to or removed from the list.
     *
     *  @author  Yael Estrada
     *  @version 1.1
     *  @since   12/1/2021
     */
public class LinkedTaskList extends AbstractTaskList implements Iterable {
        private List<Task> arr;

        public LinkedTaskList(){
            this.arr=new LinkedList<>();
        }
        /*
         *  Returns the task in the specified index inside the array.
         *  @param    ind       The index to be returned
         *  @returns  Task      The object Task at the specified index.
         */
        public Task getTask(int ind){
            if(ind<0||ind>=super.size()){
                throw new IndexOutOfBoundsException("El indice está fuera de los límites");
            }
            return arr.get(ind);
        }

        /*
         *  Inserts a new task at the end of the array.
         *  @param    t         The task to be inserted
         */
        public void add(Task t){
            if(t==null){
                throw new IllegalArgumentException("La tarea no puede ser null");
            }
            super.increase(1);
            arr.add(t);
        }

        /*
         *  It removes a Task element from the array if it exists, and returns true, if not exists
         *  returns false and doesn't remove any element.
         *  @param    t         The task to be removed.
         *  @returns  boolean   True if the element existed and was removed, otherwise false.
         */
        public boolean remove(Task t){
            if(arr.remove(t)){
                super.decrease(1);
                return true;
            }
            return false;
        }

    @Override
    public Iterator iterator() {
        return arr.iterator();
    }


}
