package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

public class PathWEInfo extends GroundInfo implements Savable {

    public PathWEInfo() {
        super(TileType.PathWE, "assets/ground/pathWE.glb");
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
        return "Pfad von West nach Ost.";
    }

    /**
     * Returns the description for this tile.
     */
    public static String getDescription() {
       return "Ein Pfad ist die minimale Voraussetzung dafür, dass Waren und Rohstoffe von Lagern und " +
               "Produktionsstätten zum Zielort transportiert werden können. " +
               "Pfade können kostenlos getauscht werden: Wird z.B. anstelle eines Pfades von West nach Ost ein Pfad " +
               "mit drei Ausgängen benötigt, kann der bisherige Pfad kostenlos ersetzt werden.";
    }
}
