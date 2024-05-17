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

        int[][] worldAsTileIntegers = TileUtil.loadWorld();
        for (int lineNumber=0; lineNumber< worldAsTileIntegers.length; lineNumber++) {
            for (int columnNumber=0; columnNumber<worldAsTileIntegers[0].length; columnNumber++) {
                int tileAsInt = worldAsTileIntegers[lineNumber][columnNumber];
                TileType type = TileType.fromIntType(tileAsInt);
                TileInfo tileInfo = TileType.getTileInfoForTileType(type);
                tileInfo.setX(columnNumber*2f);
                tileInfo.setY(0f);
                tileInfo.setZ(lineNumber*2f);
                Node tile = (Node) assetManager.loadModel(tileInfo.getFilename());
                tile.move(new Vector3f(columnNumber*2f, 0, lineNumber*2f));
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
}
