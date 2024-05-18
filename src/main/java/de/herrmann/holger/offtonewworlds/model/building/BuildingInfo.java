package de.herrmann.holger.offtonewworlds.model.building;

import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

/**
 * Temporarily: This will be abstract later.
 */
public class BuildingInfo extends TileInfo {

    public BuildingInfo() {
        super(TileType.Building, "assets/ground/Building.glb");
    }

    @Override
    public boolean canBeBuildUpon(TileInfo targetTile) {
        return false;
    }
}
