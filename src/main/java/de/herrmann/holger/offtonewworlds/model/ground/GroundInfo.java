package de.herrmann.holger.offtonewworlds.model.ground;

import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;

public abstract class GroundInfo extends TileInfo implements Savable {

    public GroundInfo(TileType type, String filename, float x, float y, float z) {
        super(type, filename, x, y, z);
    }
}
