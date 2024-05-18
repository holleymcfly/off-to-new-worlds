package de.herrmann.holger.offtonewworlds.core;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.ConstantVerifierState;
import com.jme3.audio.AudioListenerState;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Node;
import de.herrmann.holger.offtonewworlds.dialogs.DialogId;
import de.herrmann.holger.offtonewworlds.dialogs.topleftmenu.TopLeftMenu;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;
import de.herrmann.holger.offtonewworlds.settings.OffToNewWorldsSettings;
import de.herrmann.holger.offtonewworlds.util.Constants;
import de.herrmann.holger.offtonewworlds.util.TileUtil;
import de.herrmann.holger.offtonewworlds.util.Util;
import de.lessvoid.nifty.Nifty;

public class OffToNewWorlds extends SimpleApplication {

    private final BuilderHelper builderHelper;

    // This map contains the world with all the tiles in integer representation. It's important that this map
    // is always in sync with the actual root node children, because it is used for e.g. getting the neighbours of
    // a tile (e.g. for checking if a specific building can be built).
    private int[][] worldAsTileIntegers;

    public static void main(String[] args) {

        OffToNewWorlds offToNewWorlds = new OffToNewWorlds();
        if (!Util.isDebugging()) {
            OffToNewWorldsSettings.get().getDisplaySettings().setToFullScreenWindows(offToNewWorlds);
        }
        offToNewWorlds.start();
    }

    private OffToNewWorlds() {
        super(new StatsAppState(), new AudioListenerState(), new DebugKeysAppState(),
                new ConstantVerifierState());
        builderHelper = new BuilderHelper(this);
    }

    @Override
    public void simpleInitApp() {

        initWorld();
        initTopLeftMenu();
        initLight();
        initCamera();

        setDisplayFps(false);
        setDisplayStatView(false);
    }

    private void initWorld() {

        worldAsTileIntegers = TileUtil.loadWorld();
        for (int row=0; row< worldAsTileIntegers.length; row++) {
            for (int column=0; column<worldAsTileIntegers[0].length; column++) {
                int tileAsInt = worldAsTileIntegers[row][column];
                TileType type = TileType.fromIntType(tileAsInt);
                TileInfo tileInfo = TileType.getTileInfoForTileType(type);
                tileInfo.setX(column*2f);
                tileInfo.setY(0f);
                tileInfo.setZ(row*2f);
                tileInfo.setRow(row);
                tileInfo.setColumn(column);
                Node tile = (Node) assetManager.loadModel(tileInfo.getFilename());
                tile.move(new Vector3f(column*2f, 0, row*2f));
                builderHelper.addUserDataToNode(tile, Constants.USER_DATA, tileInfo);
                rootNode.attachChild(tile);
            }
        }
    }

    private void initTopLeftMenu() {

        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(assetManager, inputManager, audioRenderer,
                guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();

        nifty.addScreen(DialogId.TopLeftMenu.name(), new TopLeftMenu(DialogId.TopLeftMenu.name(), this)
                .build(nifty));
        nifty.gotoScreen(DialogId.TopLeftMenu.name());

        guiViewPort.addProcessor(niftyDisplay);
    }

    private void initLight() {

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        rootNode.addLight(sun);
    }

    private void initCamera() {

        final RtsCamera rtsCamera = new RtsCamera(cam, rootNode, this);
        rtsCamera.registerWithInput(inputManager);
        rtsCamera.setCenter(new Vector3f(0, 0f, 0));
    }

    public BuilderHelper getBuilderHelper() {
        return builderHelper;
    }

    /**
     * If the given tile shall be set to the world, we have to update the world map.
     * Note: This method only updates the world map, and doesn't set the tile to the corresponding node.
     */
    public void setTileToWorldMap(TileInfo tile) {
        worldAsTileIntegers[tile.getRow()][tile.getColumn()] = tile.getType().getTileTypeAsInt();
    }
}
