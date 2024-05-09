package de.herrmann.holger.offtonewworlds.util;

import java.lang.management.ManagementFactory;
import java.util.regex.Pattern;

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

    /**
     * Checks if the code is running in debug mode.
     */
    public static boolean isDebugging() {

        Pattern debugPattern = Pattern.compile("-Xdebug|jdwp");
        for (String arg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            if (debugPattern.matcher(arg).find()) {
                return true;
            }
        }
        return false;
    }
}
