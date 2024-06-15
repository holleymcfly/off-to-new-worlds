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
    PathWE(2),
    PathNS(3),
    PathNE(4),
    PathWN(5),
    PathWS(6),
    PathSE(7),
    PathNES(8),

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
            case PathWE -> new PathWEInfo();
            case PathNS -> new PathNSInfo();
            case PathNE -> new PathNEInfo();
            case PathWN -> new PathWNInfo();
            case PathWS -> new PathWSInfo();
            case PathSE -> new PathSEInfo();
            case PathNES -> new PathNESInfo();
            case Building -> new BuildingInfo();
        };
    }
}
