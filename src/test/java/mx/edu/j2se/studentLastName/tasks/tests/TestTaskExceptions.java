package mx.edu.j2se.studentLastName.tasks.tests;

import mx.edu.j2se.estrada.tasks.ArrayTaskList;
import mx.edu.j2se.estrada.tasks.LinkedTaskList;
import mx.edu.j2se.estrada.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Period;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTaskExceptions {
    @Test
    void TestConstructors() {
        try{
        Task t1=new Task("Tarea 1",null);
        fail("No se cacho la excepcion");
        }
        catch(IllegalArgumentException e){
            Assertions.assertEquals("La fecha no puede ser null",e.getMessage() );
        }
        try{
            Task t2=new Task("Tarea 2", LocalDateTime.parse("2020-01-01T00:00:00"),LocalDateTime.parse("2020-02-01T00:00:00"),null);
            fail("No se cacho la excepcion");
        }
        catch(IllegalArgumentException e){
            Assertions.assertEquals("El intervalo no puede ser null",e.getMessage() );
        }
        try{
            Task t3=new Task("Tarea 3",LocalDateTime.parse("2020-01-01T00:00:00"),LocalDateTime.parse("2019-01-01T00:00:00"), Period.ofWeeks(5));
            fail("No se cacho la excepcion");
        }
        catch(IllegalArgumentException e){
            Assertions.assertEquals("La fecha de inicio no puede ser mayor a la de fin",e.getMessage() );
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
            arr.add(new Task("Uno",LocalDateTime.parse("2020-01-01T00:00:00")));
            arr.getTask(3);
            fail("No se cacho la excepcion");
        }
        catch(IndexOutOfBoundsException e){
            Assertions.assertEquals("El indice está fuera de los límites",e.getMessage());
        }
    }
}
