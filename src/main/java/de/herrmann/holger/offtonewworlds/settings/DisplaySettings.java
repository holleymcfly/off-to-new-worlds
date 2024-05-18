package de.herrmann.holger.offtonewworlds.settings;

import com.jme3.system.AppSettings;
import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;

import java.awt.*;

public class DisplaySettings {

    /**
     * Use AWT to get the possible screen resolutions. Usually, the resolutions are ordered from small to large.
     * This method uses the very last resolution in fullscreen (if supported).
     * NOTE: This method may not run on macOS.
     */
    public void setToFullScreenWindows(OffToNewWorlds offToNewWorlds) {

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();

        AppSettings appSettings = OffToNewWorldsSettings.get().getAppSettings();

        int modePos = modes.length - 1;
        appSettings.setResolution(modes[modePos].getWidth(), modes[modePos].getHeight());
        appSettings.setFrequency(modes[modePos].getRefreshRate());
        appSettings.setBitsPerPixel(modes[modePos].getBitDepth());
        appSettings.setFullscreen(device.isFullScreenSupported());
        offToNewWorlds.setSettings(appSettings);
    }

    /**
     * Sets the resolution to the given size, using the window in fullscreen.
     */
    public void setToResolution(int width, int height, OffToNewWorlds offToNewWorlds) {

        AppSettings appSettings = OffToNewWorldsSettings.get().getAppSettings();

        appSettings.setResolution(width, height);
        appSettings.setFullscreen(true);

        offToNewWorlds.setSettings(appSettings);
    }
}
