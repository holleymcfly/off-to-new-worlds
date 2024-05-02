package de.herrmann.holger.offtonewworlds.settings;

import com.jme3.system.AppSettings;

/**
 * Singleton configuration class.
 */
public class OffToNewWorldsSettings {

    private static OffToNewWorldsSettings offToNewWorldsSettings;

    private AppSettings appSettings;
    private DisplaySettings displaySettings;

    public static OffToNewWorldsSettings get() {
        if (offToNewWorldsSettings == null) {
            offToNewWorldsSettings = new OffToNewWorldsSettings();
        }

        return offToNewWorldsSettings;
    }

    private OffToNewWorldsSettings() {
        this.appSettings = new AppSettings(true);
        this.displaySettings = new DisplaySettings();
    }

    public AppSettings getAppSettings() {
        return appSettings;
    }

    public DisplaySettings getDisplaySettings() {
        return displaySettings;
    }
}
