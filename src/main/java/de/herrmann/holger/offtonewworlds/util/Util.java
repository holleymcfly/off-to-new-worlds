package de.herrmann.holger.offtonewworlds.util;

public class Util {

    /**
     * Returns the value if it's between min and max.
     * If not, min or max are returned.
     */
    public static float clamp(float min, float value, float max) {
        if (value < min) {
            return min;
        }
        else return Math.min(value, max);
    }
}
