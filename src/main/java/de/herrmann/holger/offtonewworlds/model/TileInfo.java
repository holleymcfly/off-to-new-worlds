package de.herrmann.holger.offtonewworlds.model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;

public abstract class TileInfo implements Savable {

    private final TileType type;
    private final String filename;

    // The location of the node in the world.
    private float x;
    private float y;
    private float z;

    // The location in the world definition map.
    private int row;
    private int column;

    // Needed to ensure that all concrete subclasses implement this constructor.
    public TileInfo() {
        throw new UnsupportedOperationException("Don't call the empty constructor of a tile info.");
    }

    public TileInfo(TileType type, String filename) {
        this.type = type;
        this.filename = filename;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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
