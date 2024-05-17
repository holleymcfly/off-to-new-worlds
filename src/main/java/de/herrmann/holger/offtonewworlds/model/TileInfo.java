package de.herrmann.holger.offtonewworlds.model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;

public abstract class TileInfo implements Savable {

    private TileType type;
    private String filename;

    private float x;
    private float y;
    private float z;

    public TileInfo() {
        throw new UnsupportedOperationException("Don't call the empty constructor of a tile info.");
    }

    public TileInfo(TileType type, String filename) {
        this.type = type;
        this.filename = filename;
    }

    public TileInfo(TileType type, String filename, float x, float y, float z) {
        this(type, filename);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
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

    public String getFilename() {
        return filename;
    }

    /**
     * Says if the tile can be built upon the given target tile.
     */
    public abstract boolean canBeBuildUpon(TileInfo targetTile);

    @Override
    public String toString() {
        return type + " at position [" + getX() + ", " + getY() + ", " + getZ() + "]";
    }

    @Override
    public void write(JmeExporter ex) { }

    @Override
    public void read(JmeImporter im) { }
}
