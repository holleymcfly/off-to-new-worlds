package de.herrmann.holger.offtonewworlds.dialogs;

import de.lessvoid.nifty.controls.scrollpanel.ScrollPanelControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyMouseInputEvent;

import javax.annotation.Nonnull;

public class MyScrollPanelControl extends ScrollPanelControl {

    /**
     * Scrolling in the nifty ScrollPanelControl is veeeeery slow.
     * We adjust the mouse wheel parameter and forward the event to the super method.
     */
    public void mouseWheel(Element e, @Nonnull NiftyMouseInputEvent inputEvent) {

        inputEvent.initialize(inputEvent.getMouseX(), inputEvent.getMouseY(), inputEvent.getMouseWheel()*20,
                inputEvent.isButton0Down(), inputEvent.isButton1Down(), inputEvent.isButton2Down());
        super.mouseWheel(e, inputEvent);
    }
}
