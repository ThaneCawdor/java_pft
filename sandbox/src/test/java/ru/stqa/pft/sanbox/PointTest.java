package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    Point p1 = new Point(3.0,3.0);
    Point p2 = new Point(5.0,5.0);
    Point p3 = new Point(2.5,10.5);
    @Test
    public void testPoint(){
        Assert.assertEquals(Math.round(p1.distance(p2)),3.0);
    }
    @Test
    public void testPoint1(){
        Assert.assertEquals(Math.round(p1.distance(p3)),8.0);
    }
    @Test
    public void testPoint2(){
        Assert.assertEquals(Math.round(p2.distance(p3)),6.0);
    }
}
