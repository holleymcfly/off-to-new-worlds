package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class PathNEInfo extends GroundInfo implements Savable {

    public PathNEInfo() {
        super(TileType.PathNE, "assets/ground/pathNE.glb");
    }

    /**
     * A simple path can only be built on grass.
     */
    @Override
    public boolean canBeBuildUpon(TileInfo targetTile) {
        return TileType.Grass.equals(targetTile.getType());
    }

    /**
     * Returns the short description for this tile.
     */
    public static String getShortDescription() {
        return "Pfad von Nord nach Ost.";
    }

    /**
     * Returns the description for this tile.
     */
    public static String getDescription() {
       return "Ein Pfad von Norden nach Osten.";
    }
}
