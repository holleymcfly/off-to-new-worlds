package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class GrassInfo extends GroundInfo implements Savable {

    public GrassInfo() {
        super(TileType.Grass, "assets/ground/grass.glb");
    }

    @Override
    public boolean canBeBuildUpon(TileInfo targetTile) {
        return false;
    }
}
