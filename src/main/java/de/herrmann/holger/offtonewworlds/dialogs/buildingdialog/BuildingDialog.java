package de.herrmann.holger.offtonewworlds.dialogs.buildingdialog;

import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.lessvoid.nifty.builder.*;

import javax.annotation.Nonnull;

public class BuildingDialog extends ScreenBuilder {

    public BuildingDialog(@Nonnull String id, OffToNewWorlds application) {
        super(id);
        controller(new BuildingDialogController(application));
        buildUserInterface();
    }

    private void buildUserInterface() {

        layer(new LayerBuilder("forground") {{

            childLayoutCenter();

            panel(new PanelBuilder("outerPanelBuildingDialog") {{
                childLayoutCenter();
                alignCenter();
                valignCenter();
                marginTop("10px");
                marginLeft("10px");
                backgroundColor("#5E2F00");
                height("80%");
                width("80%");

                panel(new PanelBuilder("innerPanelBuildingDialog") {{

                    childLayoutHorizontal();
                    alignCenter();
                    valignCenter();
                    width("99%");
                    height("98%");
                    backgroundColor("#954A00");

                    panel(new PanelBuilder("categorySelectionPanel") {{

                        childLayoutVertical();
                        alignLeft();
                        marginLeft("20px");
                        width("10%");
                        height("100%");

                        image(new ImageBuilder() {{
                            filename("assets/icons/button_grass.png");
                            marginTop("30px");
                            height("32px");
                            width("64px");
                            visibleToMouse(true);
                            interactOnClick("openGroundSelection()");
                        }});

                        text(new TextBuilder() {{
                            text("Boden");
                            font("aurulent-sans-16.fnt");
                            color("#000");
                            width("64px");
                            height("20px");
                            interactOnClick("openGroundSelection()");
                        }});

                        image(new ImageBuilder() {{
                            filename("assets/icons/button_menu_building.png");
                            marginTop("30px");
                            height("64px");
                            width("64px");
                            visibleToMouse(true);
                            interactOnClick("openBuildingSelection()");
                        }});

                        text(new TextBuilder() {{
                            text("Wohngebäude");
                            font("aurulent-sans-16.fnt");
                            color("#000");
                            width("64px");
                            height("20px");
                            interactOnClick("openBuildingSelection()");
                        }});
                    }});

                    panel(new PanelBuilder("selectionPanel") {{

                        childLayoutAbsoluteInside();
                        alignLeft();
                        marginLeft("20px");
                        width("90%");
                        height("100%");

                        panel(new PanelBuilder("groundSelection") {{

                            childLayoutVertical();
                            visible(false);

                            image(new ImageBuilder() {{
                                filename("assets/icons/button_pathLR.png");
                                marginTop("30px");
                                height("32px");
                                width("64px");
                                visibleToMouse(true);
                                interactOnClick("selectTile(PathLeftToRight)");
                            }});

                            text(new TextBuilder() {{
                                text("Pfad von links nach rechts");
                                font("aurulent-sans-16.fnt");
                                color("#000");
                                width("64px");
                                height("20px");
                            }});
                        }});

                        panel(new PanelBuilder("buildingSelection") {{

                            childLayoutVertical();
                            visible(false);

                            image(new ImageBuilder() {{
                                filename("assets/icons/button_menu_building.png");
                                marginTop("30px");
                                height("64px");
                                width("64px");
                                visibleToMouse(true);
                                interactOnClick("selectTile(\"Building\")");
                            }});

                            text(new TextBuilder() {{
                                text("Irgendein Gebäude...");
                                font("aurulent-sans-16.fnt");
                                color("#000");
                                width("64px");
                                height("20px");
                            }});
                        }});
                    }});
                }});
            }});
        }});
    }
}
