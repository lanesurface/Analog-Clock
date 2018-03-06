package net.lanesurface.clock;

/**
 * A set of (x, y) integer-based coordinates.
 */
public final class CoordinatePair {
    public int x, y;

    public CoordinatePair() {
        this(0, 0);
    }
    
    public CoordinatePair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
