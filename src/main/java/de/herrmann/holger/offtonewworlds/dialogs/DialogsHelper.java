package de.herrmann.holger.offtonewworlds.dialogs;

import java.util.HashMap;
import java.util.Map;

public class DialogsHelper {

    private static int order = 0;

    private static final HashMap<Integer, MyScreenBuilder> modalDialogs = new HashMap<>();

    private DialogsHelper() {}

    public static void addModalDialog(MyScreenBuilder dialog) {
        modalDialogs.put(getNextOrder(), dialog);
    }

    public static boolean isModalDialogOpen() {
        return !modalDialogs.isEmpty();
    }

    public static void removeModalDialogById(String dialogId) {

        for (Map.Entry<Integer, MyScreenBuilder> entry : modalDialogs.entrySet()) {
            if (entry.getValue().getId().equals(dialogId)) {
                modalDialogs.remove(entry.getKey());
                break;
            }
        }
    }

    public static String getLatestModalDialogId() {

        Integer latest = -1;

        for (Map.Entry<Integer, MyScreenBuilder> entry : modalDialogs.entrySet()) {
            if (entry.getKey() > latest) {
                latest = entry.getKey();
            }
        }

        if (latest != -1) {
            return modalDialogs.get(latest).getId();
        }

        return null;
    }

    private static int getNextOrder() {
        order++;
        return order;
    }
}
