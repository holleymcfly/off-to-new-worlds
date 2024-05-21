package de.herrmann.holger.offtonewworlds.dialogs.buildingdialog;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.herrmann.holger.offtonewworlds.dialogs.DialogId;
import de.herrmann.holger.offtonewworlds.model.TileType;
import de.herrmann.holger.offtonewworlds.util.Util;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

import javax.annotation.Nonnull;

public class BuildingDialogController extends BaseAppState implements ScreenController {

    private final OffToNewWorlds application;

    public BuildingDialogController(OffToNewWorlds application) {
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
    public void openGroundSelection() {

        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathWestToEastPanel", true);
        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathNorthToSouthPanel", true);
        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathNorthToEastPanel", true);
        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathWestToNorthPanel", true);

        Util.setVisibility(application, DialogId.BuildingDialog.name(), "building1Panel", false);
    }

    @SuppressWarnings("unused")
    public void openBuildingSelection() {

        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathWestToEastPanel", false);
        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathNorthToSouthPanel", false);
        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathNorthToEastPanel", false);
        Util.setVisibility(application, DialogId.BuildingDialog.name(), "pathWestToNorthPanel", false);

        Util.setVisibility(application, DialogId.BuildingDialog.name(), "building1Panel", true);
    }

    /**
     * Sets the selected tile type as "to build tile", so that the camera can replace the tile of the screen
     * temporarily with the tile to be built.
     */
    @SuppressWarnings("unused")
    public void selectTile(String buildInfo) {
        application.getBuilderHelper().setTileTypeToBeBuilt(TileType.valueOf(buildInfo));
        Util.removeScreenById(application, DialogId.BuildingDialog.name());
    }
}