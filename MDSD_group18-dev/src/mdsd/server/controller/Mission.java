package mdsd.server.controller;

import com.sun.xml.internal.bind.v2.TODO;

import project.Point;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mission {

    private List<Point> points;

    public void updateMission() {
		Point point = new Point(0,1);
		Point point2 = new Point(0,2);
		Point point3 = new Point(0,3);
		Point point4 = new Point(0,4);
		Point point5 = new Point(0,5);
		points.add(point);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);
		Collections.shuffle(points);
    }
}
