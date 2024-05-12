package de.herrmann.holger.offtonewworlds.dialogs.topleftmenu;

import de.herrmann.holger.offtonewworlds.OffToNewWorlds;
import de.lessvoid.nifty.builder.*;

import javax.annotation.Nonnull;

public class TopLeftMenu extends ScreenBuilder {

    public TopLeftMenu(@Nonnull String id, OffToNewWorlds application) {
        super(id);
        controller(new TopLeftMenuController(application));
        buildUserInterface();
    }

    private void buildUserInterface() {

        layer(new LayerBuilder("forground") {{

            childLayoutVertical();

            panel(new PanelBuilder("outerPanelTopMenu") {{
                childLayoutCenter();
                alignLeft();
                valignTop();
                marginTop("10px");
                marginLeft("10px");
                backgroundColor("#5E2F00");
                height("110px");
                width("300px");

                panel(new PanelBuilder("innerPanelTopMenu") {{
                    childLayoutCenter();
                    alignLeft();
                    valignTop();
                    marginTop("10px");
                    marginLeft("10px");
                    backgroundColor("#954A00");
                    height("90px");
                    width("280px");

                    image(new ImageBuilder() {{
                        filename("assets/icons/button_menu_building.png");
                        marginLeft("10px");
                        height("64px");
                        width("64px");
                        align(Align.Left);
                        visibleToMouse(true);
                        interactOnClick("openBuildingMenu()");
                    }});

                    text(new TextBuilder() {{
                        text("Baumenü");
                        marginLeft("10px");
                        align(Align.Left);
                        valign(VAlign.Bottom);
                        font("aurulent-sans-16.fnt");
                        color("#000");
                        width("64px");
                        height("20px");
                    }});
                }});
            }});
        }});
    }
}
