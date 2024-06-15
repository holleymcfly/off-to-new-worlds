package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class PathWESInfo extends GroundInfo implements Savable {

    public PathWESInfo() {
        super(TileType.PathWES, "assets/ground/pathWES.glb");
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
        return "Pfad von West nach Ost und Süd.";
    }

    /**
     * Returns the description for this tile.
     */
    public static String getDescription() {
       return "Ein Pfad mit einer Kreuzung; er hat Wege in den Westen, Osten und Süden.";
    }
}
