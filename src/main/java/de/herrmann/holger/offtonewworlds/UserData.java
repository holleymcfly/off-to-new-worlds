package de.herrmann.holger.offtonewworlds;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import de.herrmann.holger.offtonewworlds.model.TileType;

/**
 * This class holds all information of a tile.
 */
public class UserData implements Savable {

    private final float x;
    private final float y;
    private final float z;
    private final TileType type;

    public UserData(float x, float y, float z, TileType type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
    }

    public String toString() {
        return type.name() + " at " + x + ", " + y + ", " + z;
    }

    @Override
    public void write(JmeExporter ex) { }

    @Override
    public void read(JmeImporter im) { }
}