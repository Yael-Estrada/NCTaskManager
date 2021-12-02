package mx.edu.j2se.estrada.evaluation;

public class Evaluation1 {

    public static int biggestCircle(Circle[] circles){
        if(circles.length==0) return -1;
        int ind=0;
        for(int i=1;i<circles.length;i++){
            if(circles[i].getArea()>circles[ind].getArea()){
                ind=i;
            }
        }
        return ind;
    }

    public static void main(String[] args) {
        try{
            Circle c1=new Circle(-123);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        Circle[] circles={new Circle(12),new Circle(5),new Circle(30)};

        int biggest=biggestCircle(circles);

        System.out.println("The radius of the biggest circle is: "+circles[biggest].getRadius());

    }
}
