package de.herrmann.holger.offtonewworlds.dialogs.topleftmenu;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

import javax.annotation.Nonnull;

public class TopLeftMenuController extends BaseAppState implements ScreenController {

    @Override
    protected void initialize(Application app) { }

    @Override
    protected void cleanup(Application app) { }

    @Override
    protected void onEnable() { }

    @Override
    protected void onDisable() { }

    @Override
    public void bind(@Nonnull Nifty nifty, @Nonnull Screen screen) { }

    @Override
    public void onStartScreen() { }

    @Override
    public void onEndScreen() { }

    /**
     * Fired when the user clicks on the buildings menu icon.
     * Suppress warning, as the method is called in the TopLeftMenuDialog, which is not recognized by the IDE.
     */
    @SuppressWarnings("unused")
    public void openBuildingMenu() {
        System.out.println("open buildings menu");
    }
}