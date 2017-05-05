package com.study.P20170505.z3.p4_4_2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过复制数据来维持线程安全 在数据量打的时候性能低
 * 每次getLocation 都是复制数据实际位置变化了 但是复制数据是不会改变的 这种情况的好坏取决于你的需求
 * Created by zd on 2017/5/5.
 */
public class MonitorVehicleTracker {

    private Map<String, VehicleTracker> locations;

    public MonitorVehicleTracker(Map<String, VehicleTracker> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, VehicleTracker> getLocations() {
        return deepCopy(locations);
    }

    public synchronized VehicleTracker getLocation(String name) {
        VehicleTracker tracker = locations.get(name);
        return tracker == null ? null : new VehicleTracker(tracker);
    }

    public synchronized void setLocation(String name, int x, int y) {
        VehicleTracker tracker = locations.get(name);
        if (tracker == null) {
            throw new IllegalArgumentException("no such element");
        }
        tracker.getPoint().x = x;
        tracker.getPoint().y = y;
    }

    private static Map<String, VehicleTracker> deepCopy(Map<String, VehicleTracker> locations) {
        Map<String, VehicleTracker> copy = new HashMap<>();
        for (Map.Entry<String, VehicleTracker> entry : locations.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        //不可修改的map
        return Collections.unmodifiableMap(copy);
    }
}
