package de.herrmann.holger.offtonewworlds.model;

import de.herrmann.holger.offtonewworlds.model.building.BuildingInfo;
import de.herrmann.holger.offtonewworlds.model.ground.*;

import java.util.Arrays;
import java.util.Optional;

/**
 * NOTE: If you change the enums, you will have to change the values in different nifty controllers, too.
 */
public enum TileType {

    // Ground tiles
    Grass(1),
    PathWestToEast(2),
    PathNorthToSouth(3),
    PathNorthToEast(4),
    PathWestToNorth(5),
    PathWestToSouth(6),

    // Buildings
    Building(10);

    TileType(int type) {
        this.tileTypeInt = type;
    }

    private final int tileTypeInt;

    /**
     * Returns the TileType for the given int representation of a TileType.
     */
    public static TileType fromIntType(int type) {
        Optional<TileType> tileType = Arrays.stream(TileType.values()).filter((aTile) -> aTile.tileTypeInt == type).findFirst();
        return tileType.orElse(null);
    }

    public int getTileTypeAsInt() {
        return tileTypeInt;
    }

    /**
     * Returns an instance of the specific tile info class of the given TileType.
     */
    public static TileInfo getTileInfoForTileType(TileType type) {

        return switch (type) {
            case Grass -> new GrassInfo();
            case PathWestToEast -> new PathWestToEastInfo();
            case PathNorthToSouth -> new PathNorthToSouthInfo();
            case PathNorthToEast -> new PathNorthToEastInfo();
            case PathWestToNorth -> new PathWestToNorthInfo();
            case PathWestToSouth -> new PathWestToSouthInfo();
            case Building -> new BuildingInfo();
        };
    }
}
