package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void testPoint(){
        Point p1 = new Point(3.0,3.0);
        Point p2 = new Point(2.5,10.5);

        Assert.assertEquals(Math.round(p1.distance(p2)),8.0);
    }
}
