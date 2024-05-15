package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class PathLeftToRightInfo extends GroundInfo implements Savable {

    public PathLeftToRightInfo() {
        super(TileType.Grass, "assets/ground/pathLR.glb");
    }

    public PathLeftToRightInfo(float x, float y, float z) {
        super(TileType.Grass, "assets/ground/pathLR.glb", x, y, z);
    }

    /**
     * A simple path can only be built on grass.
     */
    @Override
    public boolean canBeBuildUpon(TileInfo targetTile) {
        return TileType.Grass.equals(targetTile.getType());
    }
}