package de.herrmann.holger.offtonewworlds.dialogs;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;

import javax.annotation.Nonnull;

/**
 * This class adds the abstract method <code>create</code> to the ScreenBuilder class.
 * That is because I want to create a dialog in the static Util method. That would not be possible,
 * because of a wrong order (nifty would be part of the constructor, but shall only be created in the util method).
 */
public abstract class MyScreenBuilder extends ScreenBuilder {

    private final String id;

    public MyScreenBuilder(@Nonnull String id) {
        super(id);
        this.id = id;
    }

    public abstract void create(Nifty nifty);

    public String getId() {
        return id;
    }
}
