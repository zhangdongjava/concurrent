package com.study.P20170505.z3.p4_2_2;

import java.awt.*;

/**
 * 车辆
 * Created by zd on 2017/5/5.
 */
public class VehicleTracker {

    private String name;

    private Point point;

    public VehicleTracker(VehicleTracker tracker) {
        this.name = tracker.name;
        this.point = new Point(tracker.point);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
