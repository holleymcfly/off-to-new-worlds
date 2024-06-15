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
    PathWNE(9),
    PathWNS(10),
    PathWES(11),

    // Buildings
    Building(50);

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
            case PathWNE -> new PathWNEInfo();
            case PathWNS -> new PathWNSInfo();
            case PathWES -> new PathWESInfo();
            case Building -> new BuildingInfo();
        };
    }
}
