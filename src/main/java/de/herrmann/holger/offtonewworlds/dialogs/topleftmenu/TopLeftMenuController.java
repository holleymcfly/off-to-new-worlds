package de.herrmann.holger.offtonewworlds.dialogs.topleftmenu;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.herrmann.holger.offtonewworlds.dialogs.DialogId;
import de.herrmann.holger.offtonewworlds.dialogs.DialogsHelper;
import de.herrmann.holger.offtonewworlds.dialogs.MyScreenBuilder;
import de.herrmann.holger.offtonewworlds.dialogs.buildingdialog.BuildingDialog;
import de.herrmann.holger.offtonewworlds.util.Util;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

import javax.annotation.Nonnull;

public class TopLeftMenuController extends BaseAppState implements ScreenController {

    private final OffToNewWorlds application;

    public TopLeftMenuController(OffToNewWorlds application) {
        this.application = application;
    }

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

        if (DialogsHelper.isModalDialogOpen()) {
            return;
        }

        MyScreenBuilder dialog = new BuildingDialog(DialogId.BuildingDialog.name(), application);
        Util.createDialog(application, dialog, DialogId.BuildingDialog.name());
    }
}