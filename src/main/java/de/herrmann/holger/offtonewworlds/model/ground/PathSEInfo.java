package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class PathSEInfo extends GroundInfo implements Savable {

    public PathSEInfo() {
        super(TileType.PathSE, "assets/ground/pathSE.glb");
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
        return "Pfad von Süd nach Ost.";
    }

    /**
     * Returns the description for this tile.
     */
    public static String getDescription() {
       return "Ein Pfad von Süden nach Osten.";
    }
}
