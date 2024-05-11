package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class GrassInfo extends GroundInfo implements Savable {

    public GrassInfo(float x, float y, float z) {
        super(TileType.Grass, "assets/ground/grass.glb", x, y, z);
    }
}
