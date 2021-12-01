package mx.edu.j2se.estrada.tasks;


/* Class TaskListFactory
 *  This class serves as an Abstract factory to create the TaskList from the Enum types declared in its inner Enum class
 *  it has only one static method which has the function to return the AbstractTaskList class with the object instantiated
 *  of the class requested.
 *
 *  @author  Yael Estrada
 *  @version 1.0
 *  @since   12/1/2021
 */

public class TaskListFactory {

    /* Enum ListTypes
     * Is an enum class which has only 2 types, ARRAY and LINKED, each one has one class that belong to the corresponding
     * implementation of the TaskList class.
     */
    public enum ListTypes{
        ARRAY(ArrayTaskList.class),LINKED(LinkedTaskList.class);
        private final Class clase;
        ListTypes(Class clase){
            this.clase=clase;
        }
        public Class getClase(){
            return this.clase;
        }
    }

    // Returns a new instance of the class that belongs to the ListType object passed by parameter
    public static AbstractTaskList createTaskList(ListTypes type){
        AbstractTaskList ob=null;
        try{
            ob=(AbstractTaskList) type.getClase().newInstance();
        } catch (InstantiationException e) {
            System.out.println("Error en la instancia");
        } catch (IllegalAccessException e) {
            System.out.println("Error accesando a la clase");
        }
        return ob;
    }
}
