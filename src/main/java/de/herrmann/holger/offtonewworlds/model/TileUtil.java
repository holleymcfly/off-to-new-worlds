package de.herrmann.holger.offtonewworlds.model;

import de.herrmann.holger.offtonewworlds.model.building.BuildingInfo;
import de.herrmann.holger.offtonewworlds.model.ground.PathLeftToRightInfo;

public class TileUtil {

    public static TileInfo getTileInfoForType(TileType type) {

        return switch (type) {
            case Grass -> null;
            case PathLeftToRight -> new PathLeftToRightInfo();
            case Building -> new BuildingInfo();
        };
    }
}
