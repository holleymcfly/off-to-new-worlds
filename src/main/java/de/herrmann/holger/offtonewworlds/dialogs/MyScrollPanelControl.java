package de.herrmann.holger.offtonewworlds.dialogs;

import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.controls.scrollpanel.ScrollPanelControl;

import javax.annotation.Nonnull;

public class MyScrollPanelControl extends ScrollPanelControl {

    /**
     * Scrolling in the nifty ScrollPanelControl is veeeeery slow.
     * We adjust the step and page size values and use the init method as entry point.
     */
    @Override
    public void init(@Nonnull Parameters parameter) {
        super.init(parameter);

        super.setUp(20f, 20f, 20f, 20f, AutoScroll.OFF);
    }
}
