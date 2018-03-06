package net.lanesurface.clock;

/**
 * One of the hands of the clock. Stores state which describes its type, its
 * color when rendered on-screen, and the coordinates that the hand points to.
 */
public final class Hand {
    public final HandType type;
    public final java.awt.Color color;
    public CoordinatePair coordinates;
    
    public Hand(HandType type, java.awt.Color color) {
        this.type = type;
        this.color = color;
        coordinates = new CoordinatePair();
    }
}
