package de.herrmann.holger.offtonewworlds.util;

import com.jme3.app.SimpleApplication;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.scene.Node;

import java.io.File;
import java.io.IOException;

public class BlendToJ3oConverter extends SimpleApplication {

    public static void main(String[] args) {

        BlendToJ3oConverter blendToJ3oConverter = new BlendToJ3oConverter();
        blendToJ3oConverter.start();
    }

    @Override
    public void simpleInitApp() {

        String grassSource = "assets/ground/Building.glb";
        Node newGrass = (Node) assetManager.loadModel(grassSource);
        BinaryExporter binaryExporter = BinaryExporter.getInstance();
        try {
            binaryExporter.save(newGrass, new File("src/main/resources/assets/ground/Building.j3o"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
