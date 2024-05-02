package de.herrmann.holger.offtonewworlds;

import com.jme3.app.SimpleApplication;
import de.herrmann.holger.offtonewworlds.settings.OffToNewWorldsSettings;

public class OffToNewWorlds extends SimpleApplication {

    public static void main(String[] args) {

        OffToNewWorlds offToNewWorlds = new OffToNewWorlds();
        OffToNewWorldsSettings.get().getDisplaySettings().setToFullScreenWindows(offToNewWorlds);
        offToNewWorlds.start();
    }

    @Override
    public void simpleInitApp() {

    }
}
