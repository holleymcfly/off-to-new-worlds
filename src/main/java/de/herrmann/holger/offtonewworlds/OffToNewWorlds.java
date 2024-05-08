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
        OffToNewWorldsSettings.get().getDisplaySettings().setToFullScreenWindows(offToNewWorlds);
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

        Spatial building = assetManager.loadModel("assets/ground/Building.glb");
        building.move(new Vector3f(0, 0, 0));
        rootNode.attachChild(building);

        for (int j=0; j<100; j++) {
            for (int i = 0; i < 100; i++) {
                double rand = Math.random();
                String grassSource = "assets/ground/grass.glb";
                if (rand < 0.5) {
                    grassSource = "assets/ground/grass2.glb";
                }
                Spatial newGrass = assetManager.loadModel(grassSource);
                newGrass.move(new Vector3f(i*2f, 0, j*2f));
                rootNode.attachChild(newGrass);
            }
        }
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
