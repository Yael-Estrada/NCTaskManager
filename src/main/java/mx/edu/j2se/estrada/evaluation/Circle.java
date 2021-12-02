package mx.edu.j2se.estrada.evaluation;

public class Circle {
    private int radius;
    public Circle(){
        this.radius=1;
    }
    public Circle(int radius){
        if(radius<=0){
            throw new IllegalArgumentException("El radio es inválido");
        }
        this.radius=radius;
    }

    public void setRadius(int radius){
        if(radius<=0){
            throw new IllegalArgumentException("El radio es inválido");
        }
        this.radius=radius;
    }

    public int getRadius(){
        return radius;
    }

    public double getArea(){
        return Math.PI*radius*radius;
    }

}
