package de.herrmann.holger.offtonewworlds.core;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.model.TileType;
import de.herrmann.holger.offtonewworlds.model.TileUtil;
import de.herrmann.holger.offtonewworlds.util.Constants;

/**
 * Methods for building tiles.
 */
public class BuilderHelper {

    private final OffToNewWorlds application;

    // Set when the user selects a tile from the building menu.
    private TileType tileTypeToBeBuilt = null;
    // The original tile that has been replaced by the preview tile (tileToBeBuilt).
    private Geometry temporarilyReplacedTile = null;
    // The preview tile.
    private Node previewTile = null;

    public BuilderHelper(OffToNewWorlds application) {
        this.application = application;
    }

    /**
     * Adds the given user data to the node and all it's children.
     * This way, they are available when clicking on a tile.
     */
    public void addUserDataToNode(Node node, String key, Object data) {

        node.setUserData(key, data);

        for (int i = 0; i < node.getChildren().size(); i++) {
            node.getChild(i).setUserData(key, data);
            if (node.getChild(i) instanceof Node) {
                addUserDataToNode((Node) node.getChild(i), key, data);
            }
        }
    }

    public void setTileTypeToBeBuilt(TileType tileTypeToBeBuilt) {
        this.tileTypeToBeBuilt = tileTypeToBeBuilt;
    }

    public boolean isTileToBeBuilt() {
        return tileTypeToBeBuilt != null;
    }

    /**
     * Creates the tile to be built and replaces it at the position of the given geometry.
     */
    public void showTileToBeBuilt(Geometry g) {

        if (g == null) {
            // The mouse pointer hasn't hit a tile.
            replacePreviousPreview();
            previewTile = null;
            temporarilyReplacedTile = null;
            return;
        }

        TileInfo temporarilyReplacedTileInfo = g.getUserData(Constants.USER_DATA);

        if (previewTile != null) {
            TileInfo previewTileInfo = previewTile.getUserData(Constants.USER_DATA);
            if (previewTileInfo.getX() == temporarilyReplacedTileInfo.getX() &&
                    previewTileInfo.getY() == temporarilyReplacedTileInfo.getY() &&
                    previewTileInfo.getZ() == temporarilyReplacedTileInfo.getZ()) {
                return;
            }
        }

        replacePreviousPreview();

        temporarilyReplacedTile = g;
        application.getRootNode().detachChild(g);

        TileInfo tileInfo = TileUtil.getTileInfoForType(tileTypeToBeBuilt);
        if (tileInfo == null) {
            return;
        }

        previewTile = (Node) application.getAssetManager().loadModel(tileInfo.getFilename());
        previewTile.move(new Vector3f(temporarilyReplacedTileInfo.getX(), temporarilyReplacedTileInfo.getY(),
                temporarilyReplacedTileInfo.getZ()));
        tileInfo.setX(temporarilyReplacedTileInfo.getX());
        tileInfo.setY(temporarilyReplacedTileInfo.getY());
        tileInfo.setZ(temporarilyReplacedTileInfo.getZ());
        addUserDataToNode(previewTile, Constants.USER_DATA, tileInfo);
        application.getRootNode().attachChild(previewTile);
    }

    /**
     * Replaces the tile that has been replaced by the preview tile with the original tile.
     */
    private void replacePreviousPreview() {

        if (temporarilyReplacedTile == null || previewTile == null) {
            return;
        }

        application.getRootNode().detachChild(previewTile);

        TileInfo tileInfo = temporarilyReplacedTile.getUserData(Constants.USER_DATA);
        Node previousTile = (Node) application.getAssetManager().loadModel(tileInfo.getFilename());
        previousTile.move(new Vector3f(tileInfo.getX(), tileInfo.getY(),
                tileInfo.getZ()));
        addUserDataToNode(previousTile, Constants.USER_DATA, tileInfo);
        application.getRootNode().attachChild(previousTile);
    }

    /**
     * If a new tile shall be built (previewTile), this method returns if this tile can be built on the
     * currently selected position (temporarilyReplacedTile).
     */
    public boolean canBeBuilt() {

        if (previewTile == null || temporarilyReplacedTile == null) {
            return false;
        }

        TileInfo temporarilyReplacedTileInfo = temporarilyReplacedTile.getUserData(Constants.USER_DATA);
        TileInfo previewTileInfo = previewTile.getUserData(Constants.USER_DATA);

        return previewTileInfo.canBeBuildUpon(temporarilyReplacedTileInfo);
    }
}
