package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.ArrayTaskList;
import mx.edu.j2se.estrada.tasks.LinkedTaskList;
import mx.edu.j2se.estrada.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTaskExceptions {
    @Test
    void TestConstructors() {
        try{
        Task t1=new Task("Tarea 1",-3);
        fail("No se cacho la excepcion");
        }
        catch(IllegalArgumentException e){
            Assertions.assertEquals("El tiempo no puede ser negativo",e.getMessage() );
        }
        try{
            Task t2=new Task("Tarea 2",4,10,-5);
            fail("No se cacho la excepcion");
        }
        catch(IllegalArgumentException e){
            Assertions.assertEquals("El intevalo no puede ser menor o igual a 0",e.getMessage() );
        }
        try{
            Task t3=new Task("Tarea 3",15,10,5);
            fail("No se cacho la excepcion");
        }
        catch(IllegalArgumentException e){
            Assertions.assertEquals("El inicio no puede ser mayor al final",e.getMessage() );
        }

    }

    @Test
    void TestArrayTaskExceptions() {
        ArrayTaskList arr=new ArrayTaskList();
        try{
            arr.add(null);
            fail("No se cacho la excepcion");
        }
        catch(IllegalArgumentException e){
            Assertions.assertEquals("La tarea no puede ser null",e.getMessage());
        }
        try{
            arr.add(new Task("Uno",5));
            arr.getTask(3);
            fail("No se cacho la excepcion");
        }
        catch(IndexOutOfBoundsException e){
            Assertions.assertEquals("El indice está fuera de los límites",e.getMessage());
        }
    }
}
