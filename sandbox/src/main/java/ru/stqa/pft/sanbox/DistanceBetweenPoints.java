package ru.stqa.pft.sanbox;

public class DistanceBetweenPoints {
    public static void main(String[] args){

        Point p1 = new Point(3.0,3.0);
        Point p2 = new Point(5.0,5.0);
        Point p3 = new Point(2.5,10.5);

        double result = Math.round(p1.distance(p2));
        double result1 = Math.round(p1.distance(p3));
        double result2 = Math.round(p2.distance(p3));

        System.out.println(result+" "+result1+" "+result2);
    }
}
