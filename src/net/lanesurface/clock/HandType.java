package net.lanesurface.clock;

/**
 * The type which a clock hand may take on (second, minute, or hour).
 * @see Hand
 */
public enum HandType {
    SECOND(60), 
    MINUTE(60),
    HOUR(12);

    public final int divisions;

    private HandType(int divisions) {
        this.divisions = divisions;
    }
}
