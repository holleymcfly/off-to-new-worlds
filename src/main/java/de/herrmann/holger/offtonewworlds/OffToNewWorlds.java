package de.herrmann.holger.offtonewworlds;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.ConstantVerifierState;
import com.jme3.audio.AudioListenerState;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import de.herrmann.holger.offtonewworlds.settings.OffToNewWorldsSettings;

public class OffToNewWorlds extends SimpleApplication {

    public static void main(String[] args) {

        OffToNewWorlds offToNewWorlds = new OffToNewWorlds();
//        OffToNewWorldsSettings.get().getDisplaySettings().setToFullScreenWindows(offToNewWorlds);
        offToNewWorlds.start();
    }

    private OffToNewWorlds() {
        super(new StatsAppState(), new AudioListenerState(), new DebugKeysAppState(),
                new ConstantVerifierState());
    }

    @Override
    public void simpleInitApp() {

        initWorld();
        initLight();
        initCamera();
    }

    private void initWorld() {

        Spatial grass = assetManager.loadModel("assets/ground/grass.glb");
        grass.move(new Vector3f(0, 0, 0));
        rootNode.attachChild(grass);

        Spatial grass2 = assetManager.loadModel("assets/ground/grass.glb");
        grass2.move(new Vector3f(2.0f, 0, 0));
        rootNode.attachChild(grass2);
    }

    private void initLight() {

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        rootNode.addLight(sun);
    }

    private void initCamera() {

        final RtsCamera rtsCamera = new RtsCamera(cam, rootNode);
        rtsCamera.registerWithInput(inputManager);
        rtsCamera.setCenter(new Vector3f(0,0f,0));
    }
}
