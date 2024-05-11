package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class PathLeftToRightInfo extends GroundInfo implements Savable {

    public PathLeftToRightInfo(float x, float y, float z) {
        super(TileType.Grass, "assets/ground/pathLR.glb", x, y, z);
    }
}
