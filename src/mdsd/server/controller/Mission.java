package mdsd.server.controller;

import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mission {

    private List<Point> points;


    public void updateMission() {
		Point point1 = new Point(0,1); //Surgery
		Point point2 = new Point(0,2); // Consulting
		Point point3 = new Point(0,3); //Surgery nr2
		Point point4 = new Point(0,4); //Surgery nr3
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
    }
}
