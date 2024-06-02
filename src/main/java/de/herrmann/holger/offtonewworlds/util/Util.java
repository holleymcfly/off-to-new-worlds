package de.herrmann.holger.offtonewworlds.util;

import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.SceneProcessor;
import com.jme3.util.SafeArrayList;
import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.herrmann.holger.offtonewworlds.dialogs.DialogsHelper;
import de.herrmann.holger.offtonewworlds.dialogs.MyScreenBuilder;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;

import java.lang.management.ManagementFactory;
import java.util.regex.Pattern;

public class Util {

    /**
     * Returns the value if it's between min and max.
     * If not, min or max are returned.
     */
    public static float clamp(float min, float value, float max) {
        if (value < min) {
            return min;
        }
        else return Math.min(value, max);
    }

    /**
     * Checks if the code is running in debug mode.
     */
    public static boolean isDebugging() {

        Pattern debugPattern = Pattern.compile("-Xdebug|jdwp");
        for (String arg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            if (debugPattern.matcher(arg).find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the element with the given id of the screen with the given id.
     */
    public static Element getElementById(OffToNewWorlds application, String screenId, String elementId) {

        SafeArrayList<SceneProcessor> processors = application.getGuiViewPort().getProcessors();
        for (SceneProcessor processor : processors) {

            if (!(processor instanceof NiftyJmeDisplay)) {
                continue;
            }

            Screen buildingDialog = ((NiftyJmeDisplay) processor).getNifty().getScreen(screenId);
            if (buildingDialog == null) {
                continue;
            }

            Element root = buildingDialog.getRootElement();
            Element element = getElementById(root, elementId);
            if (element != null) {
                return element;
            }
        }

        return null;
    }

    /**
     * Returns the element with the given id of the given screen.
     */
    private static Element getElementById(Element element, String id) {

        if (id.equals(element.getId())) {
            return element;
        }

        for (Element child : element.getChildren()) {
            Element result = getElementById(child, id);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * Changes the visibility of a UI element in a given screen.
     */
    public static void setVisibility(OffToNewWorlds application, String screenId, String elementId, boolean visible) {

        Element element = getElementById(application, screenId, elementId);

        if (element != null) {
            element.setVisible(visible);
        }
    }

    public static void createDialog(OffToNewWorlds application, MyScreenBuilder dialog, String screenId) {

        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(application.getAssetManager(),
                application.getInputManager(), application.getAudioRenderer(), application.getGuiViewPort());
        Nifty nifty = niftyDisplay.getNifty();

        dialog.create(nifty);
        nifty.addScreen(screenId, dialog.build(nifty));
        nifty.gotoScreen(screenId);

        application.getGuiViewPort().addProcessor(niftyDisplay);
        DialogsHelper.addModalDialog(screenId, dialog);
    }

    /**
     * Returns the screen with the given id.
     */
    public static void removeDialogById(OffToNewWorlds application, String dialogId) {

        SafeArrayList<SceneProcessor> processors = application.getGuiViewPort().getProcessors();
        for (SceneProcessor processor : processors) {

            Screen screen = ((NiftyJmeDisplay) processor).getNifty().getScreen(dialogId);
            if (screen != null) {
                application.getGuiViewPort().removeProcessor(processor);
                break;
            }
        }

        DialogsHelper.removeDialogById(dialogId);
    }
}
