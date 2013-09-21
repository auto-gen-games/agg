package uk.ac.kcl.inf.agg.geology;

import java.util.LinkedList;
import java.util.List;

public class Plate {
    private final List<Position> _coverage;
    
    public Plate () {
        _coverage = new LinkedList<> ();
    }
    
    public Plate (Position start) {
        this ();
        _coverage.add (start);
    }
    
    public void add (Position position) {
        _coverage.add (position);
    }
    
    public boolean covers (Position position) {
        for (Position existing : _coverage) {
            if (existing.equals (position)) {
                return true;
            }
        }
        
        return false;
    }
    
    public List<Position> getCoverage () {
        return _coverage;
    }
}
