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
import de.herrmann.holger.offtonewworlds.model.building.BuildingInfo;
import de.herrmann.holger.offtonewworlds.model.ground.GrassInfo;
import de.herrmann.holger.offtonewworlds.settings.OffToNewWorldsSettings;
import de.herrmann.holger.offtonewworlds.util.Constants;
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

        Node building = (Node) assetManager.loadModel("assets/ground/Building.glb");
        building.move(new Vector3f(0, 0, 0));
        builderHelper.addUserDataToNode(building, Constants.USER_DATA, new BuildingInfo(0, 0, 0));
        rootNode.attachChild(building);

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100; i++) {
                double rand = Math.random();
                String grassSource = "assets/ground/grass.glb";
                if (rand < 0.5) {
                    grassSource = "assets/ground/grass2.glb";
                }
                Node newGrass = (Node) assetManager.loadModel(grassSource);
                newGrass.move(new Vector3f(i * 2f, 0, j * 2f));
                builderHelper.addUserDataToNode(newGrass, Constants.USER_DATA, new GrassInfo(i * 2f, 0, j * 2f));
                rootNode.attachChild(newGrass);
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
