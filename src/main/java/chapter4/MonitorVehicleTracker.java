package chapter4;


import annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MonitorVehicleTracker {
    
    private final Map<String, MutablePoint> localtions;
    
    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.localtions = deepCopy(locations);
    }

    public synchronized MutablePoint getLocaltion(String id) {
        MutablePoint loc = localtions.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    private Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m) {
        Map<String,MutablePoint> result =
                new HashMap<String,MutablePoint>();
        for (String id : m.keySet())
            result.put(id, new MutablePoint(m.get(id)));

        return Collections.unmodifiableMap(result);
    }
}
