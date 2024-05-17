package de.herrmann.holger.offtonewworlds.model;

import de.herrmann.holger.offtonewworlds.model.building.BuildingInfo;
import de.herrmann.holger.offtonewworlds.model.ground.GrassInfo;
import de.herrmann.holger.offtonewworlds.model.ground.PathLeftToRightInfo;

import java.util.Arrays;
import java.util.Optional;

/**
 * NOTE: If you change the enums, you will have to change the values in different nifty controllers, too.
 */
public enum TileType {

    // Ground tiles
    Grass(1),
    PathLeftToRight(2),

    // Buildings
    Building(10);

    TileType(int type) {
        this.tileTypeInt = type;
    }

    public int tileTypeInt;

    /**
     * Returns the TileType for the given int representation of a TileType.
     */
    public static TileType fromIntType(int type) {
        Optional<TileType> tileType = Arrays.stream(TileType.values()).filter((aTile) -> aTile.tileTypeInt == type).findFirst();
        return tileType.orElse(null);
    }

    /**
     * Returns an instance of the specific tile info class of the given TileType.
     */
    public static TileInfo getTileInfoForTileType(TileType type) {

        return switch (type) {
            case Grass -> new GrassInfo();
            case PathLeftToRight -> new PathLeftToRightInfo();
            case Building -> new BuildingInfo();
        };
    }
}
