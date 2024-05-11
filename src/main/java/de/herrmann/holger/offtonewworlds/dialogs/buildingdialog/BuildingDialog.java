package de.herrmann.holger.offtonewworlds.dialogs.buildingdialog;

import de.lessvoid.nifty.builder.*;

import javax.annotation.Nonnull;

public class BuildingDialog extends ScreenBuilder {

    public BuildingDialog(@Nonnull String id) {
        super(id);
        controller(new BuildingDialogController());
        buildUserInterface();
    }

    private void buildUserInterface() {

        layer(new LayerBuilder("forground") {{

            childLayoutVertical();

            panel(new PanelBuilder("panelTopMenu") {{
                childLayoutCenter();
                alignLeft();
                valignTop();
                marginTop("10px");
                marginLeft("10px");
                backgroundColor("#5E2F00");
                height("110px");
                width("300px");


                panel(new PanelBuilder("panelTopMenu") {{
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

                    // add text
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
