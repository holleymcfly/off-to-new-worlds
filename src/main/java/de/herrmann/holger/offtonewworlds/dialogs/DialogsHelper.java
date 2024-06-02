package de.herrmann.holger.offtonewworlds.dialogs;

import de.lessvoid.nifty.builder.ScreenBuilder;

import java.util.HashMap;

public class DialogsHelper {

    private static final HashMap<String, ScreenBuilder> modalDialogs = new HashMap<>();

    private DialogsHelper() {}

    public static void addModalDialog(String dialogId, ScreenBuilder dialog) {
        modalDialogs.put(dialogId, dialog);
    }

    public static boolean isModalDialogOpen() {
        return !modalDialogs.isEmpty();
    }

    public static void removeDialogById(String dialogId) {
        modalDialogs.remove(dialogId);
    }
}
