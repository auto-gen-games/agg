package uk.ac.kcl.inf.agg.geology;

public class Position {
    public final int x, y, z;
    
    public Position (int atX, int atY, int atZ) {
        x = atX;
        y = atY;
        z = atZ;
    }

    @Override
    public boolean equals (Object otherPosition) {
        Position other;
        
        if (otherPosition instanceof Position) {
            other = (Position) otherPosition;
            return x == other.x && y == other.y && z == other.z;
        }
        return false;
    }

    @Override
    public int hashCode () {
        return x + y + z;
    }
    
    public Position movedToX (int newX) {
        return new Position (newX, y, z);
    }
    
    public Position movedToY (int newY) {
        return new Position (x, newY, z);
    }

    public Position movedToZ (int newZ) {
        return new Position (x, y, newZ);
    }
}
