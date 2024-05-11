package de.herrmann.holger.offtonewworlds.model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;

public abstract class TileInfo implements Savable {

    private final TileType type;
    private final String filename;

    private final float x;
    private final float y;
    private final float z;

    public TileInfo(TileType type, String filename, float x, float y, float z) {
        this.type = type;
        this.filename = filename;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public TileType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " at position [" + getX() + ", " + getY() + ", " + getZ() + "]";
    }

    @Override
    public void write(JmeExporter ex) { }

    @Override
    public void read(JmeImporter im) { }
}
