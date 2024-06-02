package de.herrmann.holger.offtonewworlds.core;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import de.herrmann.holger.offtonewworlds.dialogs.DialogsHelper;
import de.herrmann.holger.offtonewworlds.model.TileInfo;
import de.herrmann.holger.offtonewworlds.util.Constants;
import de.herrmann.holger.offtonewworlds.util.Util;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Real time strategy camera.
 * Scrolls to the east, west, north and south when the mouse moves near the borders.
 * Scrolls into / out from the view using the mouse wheel.
 * Rotates when holding the middle mouse button and moving the mouse.
 */
public class RtsCamera implements Control, ActionListener, AnalogListener {

    private final OffToNewWorlds application;

    public enum Degree {
        SIDE,
        FWD,
        ROTATE,
        TILT,
        DISTANCE
    }

    private final ColorRGBA backgroundColor = new ColorRGBA(0.7f, 0.8f, 1f, 1f);
    private InputManager inputManager;
    private final Camera cam;
    private final Spatial target;

    private final int[] direction = new int[5];
    private final float[] accelPeriod = new float[5];

    private final float[] maxSpeed = new float[5];
    private final float[] maxAccelPeriod = new float[5];
    private final float[] minValue = new float[5];
    private final float[] maxValue = new float[5];

    private final Vector3f position = new Vector3f();

    private final Vector3f center = new Vector3f();
    private float tilt = (float) (Math.PI / 4);
    private float rotate = 0;
    private float distance = 15;

    private static final int SIDE = Degree.SIDE.ordinal();
    private static final int FWD = Degree.FWD.ordinal();
    private static final int ROTATE = Degree.ROTATE.ordinal();
    private static final int TILT = Degree.TILT.ordinal();
    private static final int DISTANCE = Degree.DISTANCE.ordinal();

    private boolean middleMouseButtonClicked = false;

    private final String BACK = "Back";
    private final String FORWARD = "Forward";
    private final String WEST = "West";
    private final String EAST = "East";
    private final String MOUSE_MIDDLE = "MouseMiddleClick";
    private final String MOUSE_LEFT_CLICK = "MouseLeftClick";
    private final String MOUSE_RIGHT_CLICK = "MouseRightClick";
    private final String NORTH = "North";
    private final String SOUTH = "South";

    private final JmeCursor buildOkCursor;
    private final JmeCursor buildNotOkCursor;

    public RtsCamera(Camera cam, Spatial target, OffToNewWorlds application) {
        this.cam = cam;
        this.target = target;
        this.application = application;

        buildOkCursor = initCursor("assets/cursor/mouse_ok.png");
        buildNotOkCursor = initCursor("assets/cursor/mouse_not_ok.png");

        setMinMaxValues(Degree.SIDE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
        setMinMaxValues(Degree.FWD, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
        setMinMaxValues(Degree.ROTATE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
        setMinMaxValues(Degree.TILT, 0.2f, (float) (Math.PI / 2) - 0.001f);
        setMinMaxValues(Degree.DISTANCE, 2, Float.POSITIVE_INFINITY);

        setMaxSpeed(Degree.SIDE, 5f, 0.4f);
        setMaxSpeed(Degree.FWD, 5f, 0.4f);
        setMaxSpeed(Degree.ROTATE, 2f, 0.4f);
        setMaxSpeed(Degree.TILT, 1f, 0.4f);
        setMaxSpeed(Degree.DISTANCE, 15f, 0.4f);

        target.addControl(this);

        initPosition();
    }

    protected JmeCursor initCursor(String imagePath) {
        Texture cursorTexture = application.getAssetManager().loadTexture(imagePath);

        Image image = cursorTexture.getImage();
        ByteBuffer imgByteBuff = image.getData(0).rewind();
        IntBuffer curIntBuff = BufferUtils.createIntBuffer(image.getHeight() * image.getWidth());

        while (imgByteBuff.hasRemaining()) {
            int rgba = imgByteBuff.getInt();
            int argb = ((rgba & 255) << 24) | (rgba >> 8);
            curIntBuff.put(argb);
        }

        JmeCursor cursor = new JmeCursor();
        cursor.setHeight(image.getHeight());
        cursor.setWidth(image.getWidth());
        cursor.setNumImages(1);
        cursor.setyHotSpot(image.getHeight() - 3);
        cursor.setxHotSpot(3);
        cursor.setImagesData(curIntBuff.rewind());
        return cursor;
    }

    public void setMaxSpeed(Degree deg, float maxSpd, float accelTime) {
        maxSpeed[deg.ordinal()] = maxSpd / accelTime;
        maxAccelPeriod[deg.ordinal()] = accelTime;
    }

    public void registerWithInput(InputManager inputManager) {
        this.inputManager = inputManager;

        inputManager.setCursorVisible(true);

        inputManager.addMapping(BACK, new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addMapping(FORWARD, new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        inputManager.addMapping(MOUSE_MIDDLE, new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));
        inputManager.addMapping(MOUSE_LEFT_CLICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping(MOUSE_RIGHT_CLICK, new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addListener(this, FORWARD, BACK, MOUSE_MIDDLE, MOUSE_LEFT_CLICK, MOUSE_RIGHT_CLICK);

        inputManager.addMapping(WEST, new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping(EAST, new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping(NORTH, new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping(SOUTH, new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addListener(this, WEST, EAST, NORTH, SOUTH);
    }

    @Override
    public void write(JmeExporter ex) {
    }

    @Override
    public void read(JmeImporter im) {
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        RtsCamera other = new RtsCamera(cam, spatial, application);
        other.registerWithInput(inputManager);
        return other;
    }

    @Override
    public void setSpatial(Spatial spatial) {

    }

    @Override
    public void update(float tpf) {

        checkWestEastMovement();
        checkNorthSouthMovement();

        float tpfOriginal = tpf;

        for (int i = 0; i < direction.length; i++) {
            int dir = direction[i];

            tpf = tpfOriginal;
            checkMouseScrolling(i);
            tpf = adjustTpf(tpf, i);

            switch (dir) {
                case -1:
                    accelPeriod[i] = Util.clamp(-maxAccelPeriod[i], accelPeriod[i] - tpf, accelPeriod[i]);
                    break;
                case 0:
                    accelPeriod[i] = 0;
                    break;
                case 1:
                    accelPeriod[i] = Util.clamp(accelPeriod[i], accelPeriod[i] + tpf, maxAccelPeriod[i]);
                    break;
            }
        }

        distance += maxSpeed[DISTANCE] * accelPeriod[DISTANCE] * tpf;
        tilt += maxSpeed[TILT] * accelPeriod[TILT] * tpf;
        rotate += maxSpeed[ROTATE] * accelPeriod[ROTATE] * tpf;

        distance = Util.clamp(minValue[DISTANCE], distance, maxValue[DISTANCE]);
        rotate = Util.clamp(minValue[ROTATE], rotate, maxValue[ROTATE]);
        tilt = Util.clamp(minValue[TILT], tilt, maxValue[TILT]);

        double offX = maxSpeed[SIDE] * accelPeriod[SIDE] * tpf;
        double offZ = maxSpeed[FWD] * accelPeriod[FWD] * tpf;

        center.x += (float) (offX * Math.cos(-rotate) + offZ * Math.sin(rotate));
        center.z += (float) (offX * Math.sin(-rotate) + offZ * Math.cos(rotate));

        position.x = center.x + (float) (distance * Math.cos(tilt) * Math.sin(rotate));
        position.y = center.y + (float) (distance * Math.sin(tilt));
        position.z = center.z + (float) (distance * Math.cos(tilt) * Math.cos(rotate));

        cam.setLocation(position);
        cam.lookAt(center, new Vector3f(0, 1, 0));

        handleMouseCursor();
    }

    /**
     * If a tile to build has been selected, the mouse cursor has to be changed, having a red cross or a green
     * check to indicate whether the selected tile can be built there.
     */
    private void handleMouseCursor() {

        if (application.getBuilderHelper().isTileToBeBuilt()) {
            if (application.getBuilderHelper().canBeBuilt()) {
                application.getInputManager().setMouseCursor(buildOkCursor);
            }
            else {
                application.getInputManager().setMouseCursor(buildNotOkCursor);
            }
        }
        else {
            application.getInputManager().setMouseCursor(null);
        }
    }

    /**
     * Sets the position of the initial camera further away from the world.
     */
    private void initPosition() {

        distance = 65f;

        position.x = 0f;
        position.y = 50f;
        position.z = 50f;

        cam.setLocation(position);
        cam.lookAt(center, new Vector3f(0, 1, 0));
    }

    /**
     * Sets the direction flag if the cursor is on the left or right side of the screen.
     */
    private void checkWestEastMovement() {

        if (DialogsHelper.isModalDialogOpen()) {
            return;
        }

        if (inputManager.getCursorPosition().x > cam.getWidth() - 20) {
            direction[SIDE] = 1;
        }
        else if (inputManager.getCursorPosition().x < 20) {
            direction[SIDE] = -1;
        }
        else {
            direction[SIDE] = 0;
        }
    }

    /**
     * Sets the direction flag if the cursor is on the upper or bottom side of the screen.
     */
    private void checkNorthSouthMovement() {

        if (DialogsHelper.isModalDialogOpen()) {
            return;
        }

        if (inputManager.getCursorPosition().y < 20) {
            direction[FWD] = 1;
        }
        else if (inputManager.getCursorPosition().y > cam.getHeight() - 20) {
            direction[FWD] = -1;
        }
        else {
            direction[FWD] = 0;
        }
    }

    /**
     * Resets the flag for changing the position, so that one mouse wheel scroll is stopped again.
     */
    private void checkMouseScrolling(int i) {

        // If we scroll with the mouse wheel, no onAction is fired for a stop scrolling.
        // So we scroll a certain amount and stop manually by setting the direction to 0.
        if (i == DISTANCE) {
            direction[i] = 0;
        }
    }

    /**
     * Some movement should be with different speed.
     */
    private float adjustTpf(float tpf, int i) {

        if (i == DISTANCE) {
            return tpf * 10;
        }
        if (i == TILT) {
            return tpf / 2;
        }
        else if (i == ROTATE) {
            return tpf / 10;
        }
        else {
            return tpf;
        }
    }

    // SIDE and FWD min/max values are ignored
    public void setMinMaxValues(Degree dg, float min, float max) {
        minValue[dg.ordinal()] = min;
        maxValue[dg.ordinal()] = max;
    }

    public void setCenter(Vector3f center) {
        this.center.set(center);
    }

    @Override
    public void render(RenderManager rm, ViewPort vp) {
        vp.setBackgroundColor(backgroundColor);
    }

    /**
     * Handles the action input mappings (mouse button, mouse wheel).
     */
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {

        if (DialogsHelper.isModalDialogOpen()) {
            return;
        }

        if (MOUSE_LEFT_CLICK.equals(name) && !isPressed) {
            handleLeftMouseClick(tpf);
        }

        if (MOUSE_RIGHT_CLICK.equals(name)) {
            handleRightMouseClick();
        }

        if (MOUSE_MIDDLE.equals(name)) {
            middleMouseButtonClicked = isPressed;
            return;
        }

        if (BACK.equals(name)) {
            direction[DISTANCE] = -1;
        }
        else if (FORWARD.equals(name)) {
            direction[DISTANCE] = 1;
        }
    }

    private void handleRightMouseClick() {

        if (application.getBuilderHelper().isTileToBeBuilt()) {
            application.getBuilderHelper().showTileToBeBuilt(null);
            application.getBuilderHelper().setTileTypeToBeBuilt(null);
        }
    }

    private void handleLeftMouseClick(float tpf) {

        Geometry g = getHitTile(tpf);
        if (g != null) {

            TileInfo tileInfo = g.getUserData(Constants.USER_DATA);
            System.out.println(tileInfo);

            if (application.getBuilderHelper().isTileToBeBuilt()) {
                application.getBuilderHelper().build();
                return;
            }

            // Temp: change color of the hidden tile.
            Material mat = new Material(application.getAssetManager(),"assets/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.randomColor());
            g.setMaterial(mat);
            // ------- end temp
        }
    }

    private Geometry getHitTile(float tpf) {

        CollisionResults results = new CollisionResults();
        Vector2f click2d = inputManager.getCursorPosition();
        click2d.setY(click2d.getY());
        Vector3f click3d = cam.getWorldCoordinates(click2d, 0);
        // Adjust the y position - if not, the click is performed in the middle of the cursor icon, not on its tip.
        click3d.setY(click3d.getY() + (16f*tpf));
        Vector3f dir = cam.getWorldCoordinates(click2d, 1).subtractLocal(click3d);
        Ray ray = new Ray(click3d, dir);

        target.collideWith(ray, results);
        if (results.size() > 0) {

            CollisionResult closest = results.getClosestCollision();
            return closest.getGeometry();
        }

        return null;
    }

    /**
     * Handles the analog input mappings (mouse movement).
     */
    @Override
    public void onAnalog(String name, float value, float tpf) {

        if (DialogsHelper.isModalDialogOpen()) {
            return;
        }

        if (middleMouseButtonClicked) {
            if (name.equals(NORTH)) {
                direction[TILT] = 1;
            }
            else if (name.equals(SOUTH)) {
                direction[TILT] = -1;
            }

            if (name.equals(EAST)) {
                direction[ROTATE] = 1;
            }
            else if (name.equals(WEST)) {
                direction[ROTATE] = -1;
            }
        }
        else if (application.getBuilderHelper().isTileToBeBuilt()) {
            Geometry g = getHitTile(tpf);
            application.getBuilderHelper().showTileToBeBuilt(g);

            direction[ROTATE] = 0;
            direction[TILT] = 0;
        }
        else {
            direction[ROTATE] = 0;
            direction[TILT] = 0;
        }
    }
}