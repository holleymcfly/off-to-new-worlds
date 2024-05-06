package de.herrmann.holger.offtonewworlds;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import de.herrmann.holger.offtonewworlds.util.Util;

public class RtsCamera implements Control, ActionListener {

    public enum Degree {
        SIDE,
        FWD,
        ROTATE,
        TILT,
        DISTANCE
    }

    private InputManager inputManager;
    private final Camera cam;

    private final int[] direction = new int[5];
    private final float[] accelPeriod = new float[5];

    private final float[] maxSpeed = new float[5];
    private final float[] maxAccelPeriod = new float[5];
    private final float[] minValue = new float[5];
    private final float[] maxValue = new float[5];

    private final Vector3f position = new Vector3f();

    private final Vector3f center = new Vector3f();
    private float tilt = (float) (Math.PI / 4);
    private float rot = 0;
    private float distance = 15;

    private static final int SIDE = Degree.SIDE.ordinal();
    private static final int FWD = Degree.FWD.ordinal();
    private static final int ROTATE = Degree.ROTATE.ordinal();
    private static final int TILT = Degree.TILT.ordinal();
    private static final int DISTANCE = Degree.DISTANCE.ordinal();

    public RtsCamera(Camera cam, Spatial target) {
        this.cam = cam;

        setMinMaxValues(Degree.SIDE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
        setMinMaxValues(Degree.FWD, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
        setMinMaxValues(Degree.ROTATE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
        setMinMaxValues(Degree.TILT, 0.2f, (float) (Math.PI / 2) - 0.001f);
        setMinMaxValues(Degree.DISTANCE, 2, Float.POSITIVE_INFINITY);

        setMaxSpeed(Degree.SIDE, 10f, 0.4f);
        setMaxSpeed(Degree.FWD, 10f, 0.4f);
        setMaxSpeed(Degree.ROTATE, 2f, 0.4f);
        setMaxSpeed(Degree.TILT, 1f, 0.4f);
        setMaxSpeed(Degree.DISTANCE, 15f, 0.4f);
        target.addControl(this);

        initPosition();
    }

    public void setMaxSpeed(Degree deg, float maxSpd, float accelTime) {
        maxSpeed[deg.ordinal()] = maxSpd / accelTime;
        maxAccelPeriod[deg.ordinal()] = accelTime;
    }

    public void registerWithInput(InputManager inputManager) {
        this.inputManager = inputManager;

        String[] mappings = new String[]{"+ROTATE", "+TILT", "+DISTANCE", "-ROTATE", "-TILT", "-DISTANCE",};

        inputManager.addMapping("+ROTATE", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("-ROTATE", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("+TILT", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("-TILT", new KeyTrigger(KeyInput.KEY_F));

        inputManager.addMapping("-DISTANCE", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addMapping("+DISTANCE", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));

        inputManager.addListener(this, mappings);
        inputManager.setCursorVisible(true);
    }

    @Override
    public void write(JmeExporter ex) {
    }

    @Override
    public void read(JmeImporter im) {
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        RtsCamera other = new RtsCamera(cam, spatial);
        other.registerWithInput(inputManager);
        return other;
    }

    @Override
    public void setSpatial(Spatial spatial) {

    }

    @Override
    public void update(float tpf) {

        checkLeftRightMovement();
        checkUpDownMovement();

        for (int i = 0; i < direction.length; i++) {
            int dir = direction[i];

            tpf = checkMouseScrolling(tpf, i);

            switch (dir) {
                case -1:
                    accelPeriod[i] = Util.clamp(-maxAccelPeriod[i], accelPeriod[i] - tpf, accelPeriod[i]);
                    break;
                case 0:
                    if (accelPeriod[i] != 0) {
                        double oldSpeed = accelPeriod[i];
                        if (accelPeriod[i] > 0) {
                            accelPeriod[i] -= tpf;
                        }
                        else {
                            accelPeriod[i] += tpf;
                        }
                        if (oldSpeed * accelPeriod[i] < 0) {
                            accelPeriod[i] = 0;
                        }
                    }
                    break;
                case 1:
                    accelPeriod[i] = Util.clamp(accelPeriod[i], accelPeriod[i] + tpf, maxAccelPeriod[i]);
                    break;
            }
        }

        distance += maxSpeed[DISTANCE] * accelPeriod[DISTANCE] * tpf;
        tilt += maxSpeed[TILT] * accelPeriod[TILT] * tpf;
        rot += maxSpeed[ROTATE] * accelPeriod[ROTATE] * tpf;

        distance = Util.clamp(minValue[DISTANCE], distance, maxValue[DISTANCE]);
        rot = Util.clamp(minValue[ROTATE], rot, maxValue[ROTATE]);
        tilt = Util.clamp(minValue[TILT], tilt, maxValue[TILT]);

        double offX = maxSpeed[SIDE] * accelPeriod[SIDE] * tpf;
        double offZ = maxSpeed[FWD] * accelPeriod[FWD] * tpf;

        center.x += (float) (offX * Math.cos(-rot) + offZ * Math.sin(rot));
        center.z += (float) (offX * Math.sin(-rot) + offZ * Math.cos(rot));

        position.x = center.x + (float) (distance * Math.cos(tilt) * Math.sin(rot));
        position.y = center.y + (float) (distance * Math.sin(tilt));
        position.z = center.z + (float) (distance * Math.cos(tilt) * Math.cos(rot));

        System.out.println("Position: " + position);
        System.out.println("Center: " + center);
        System.out.println("Distance: " + distance);
        System.out.println("Tilt: " + tilt);
        System.out.println("Rot: " + rot);


        cam.setLocation(position);
        cam.lookAt(center, new Vector3f(0, 1, 0));
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
    private void checkLeftRightMovement() {

        if (inputManager.getCursorPosition().x < 20) {
            direction[Degree.SIDE.ordinal()] = 1;
        }
        else if (inputManager.getCursorPosition().x > cam.getWidth()-20) {
            direction[Degree.SIDE.ordinal()] = -1;
        }
        else {
            direction[Degree.SIDE.ordinal()] = 0;
        }
    }

    /**
     * Sets the direction flag if the cursor is on the upper or bottom side of the screen.
     */
    private void checkUpDownMovement() {

        if (inputManager.getCursorPosition().y < 20) {
            direction[Degree.FWD.ordinal()] = 1;
        }
        else if (inputManager.getCursorPosition().y > cam.getHeight()-20) {
            direction[Degree.FWD.ordinal()] = -1;
        }
        else {
            direction[Degree.FWD.ordinal()] = 0;
        }
    }

    /**
     * Resets the flag for changing the position, so that one mouse wheel scroll is stopped again.
     */
    private float checkMouseScrolling(float tpf, int i) {

        // If we scroll with the mouse wheel, no onAction is fired for a stop scrolling.
        // So we scroll a certain amount and stop manually by setting the direction to 0.
        if (i == Degree.DISTANCE.ordinal()) {
            tpf = 5 * tpf;
            direction[i] = 0;
        }

        return tpf;
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

    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        int press = isPressed ? 1 : 0;

        char sign = name.charAt(0);
        if (sign == '-') {
            press = -press;
        }
        else if (sign != '+') {
            return;
        }

        Degree deg = Degree.valueOf(name.substring(1));
        direction[deg.ordinal()] = press;
    }
}