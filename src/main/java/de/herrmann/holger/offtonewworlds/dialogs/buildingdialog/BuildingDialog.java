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

            panel(new PanelBuilder("innerPanelBuildingDialog") {{

                width("50%");
                height("30%");

                alignCenter();
                valignCenter();
                backgroundColor("#954A00");

                childLayoutHorizontal();

                panel(new PanelBuilder("categorySelectionPanel") {{

                    width("10%");
                    height("100%");

                    marginLeft("20px");

                    alignLeft();

                    childLayoutVertical();

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

                    width("20%");
                    height("100%");

                    marginLeft("20px");

                    alignLeft();

                    childLayoutAbsoluteInside();

                    panel(new PanelBuilder("groundSelection") {{

                        width("100%");

                        visible(false);

                        childLayoutVertical();

                        image(new ImageBuilder() {{
                            alignCenter();
                            filename("assets/icons/button_pathLR.png");
                            marginTop("30px");
                            height("32px");
                            width("64px");
                            visibleToMouse(true);
                            interactOnClick("selectTile(PathLeftToRight)");
                        }});

                        text(new TextBuilder() {{
                            alignCenter();
                            text("Pfad von links nach rechts");
                            font("aurulent-sans-16.fnt");
                            color("#000");
                            width("64px");
                            height("20px");
                        }});
                    }});

                    panel(new PanelBuilder("buildingSelection") {{

                        width("100%");

                        visible(false);

                        childLayoutVertical();

                        image(new ImageBuilder() {{
                            alignCenter();
                            filename("assets/icons/button_menu_building.png");
                            marginTop("30px");
                            height("64px");
                            width("64px");
                            visibleToMouse(true);
                            interactOnClick("selectTile(\"Building\")");
                        }});

                        text(new TextBuilder() {{
                            alignCenter();
                            text("Irgendein Gebäude...");
                            font("aurulent-sans-16.fnt");
                            color("#000");
                            width("64px");
                            height("20px");
                        }});

                        image(new ImageBuilder() {{
                            alignCenter();
                            filename("assets/icons/button_menu_building.png");
                            marginTop("30px");
                            height("64px");
                            width("64px");
                            visibleToMouse(true);
                            interactOnClick("selectTile(\"Building\")");
                        }});

                        text(new TextBuilder() {{
                            alignCenter();
                            text("Irgendein Gebäude...");
                            font("aurulent-sans-16.fnt");
                            color("#000");
                            width("64px");
                            height("20px");
                        }});
                    }});
                }});

                panel(new PanelBuilder("descriptionPanel") {{

                    width("70%");
                    height("100%");

                    alignLeft();
                    childLayoutAbsoluteInside();

                    panel(new PanelBuilder("groundDescriptionParent") {{

                        width("100%");
                        childLayoutVertical();

                        panel(new PanelBuilder("groundDescription") {{

                            width("100%");

                            visible(false);

                            childLayoutVertical();
                            valignCenter();

                            text(new TextBuilder() {{
                                alignLeft();
                                marginLeft("20px");
                                text("Beschreibung: Pfad von links nach rechts");
                                font("aurulent-sans-16.fnt");
                                color("#000");
                                height("84px");
                            }});
                        }});
                    }});

                    panel(new PanelBuilder("buildingDescriptionParent") {{

                        width("100%");
                        childLayoutVertical();

                        panel(new PanelBuilder("buildingDescription") {{

                            width("100%");

                            visible(false);

                            childLayoutVertical();
                            valignCenter();

                            text(new TextBuilder() {{
                                alignLeft();
                                marginLeft("20px");
                                text("Beschreibung: Irgendein Gebäude...");
                                font("aurulent-sans-16.fnt");
                                color("#000");
                                height("84px");
                            }});
                        }});

                        panel(new PanelBuilder("buildingDescription2") {{

                            width("100%");

                            visible(false);

                            childLayoutVertical();
                            valignCenter();

                            text(new TextBuilder() {{
                                alignLeft();
                                marginLeft("20px");
                                text("Beschreibung2: Irgendein Gebäude...");
                                font("aurulent-sans-16.fnt");
                                color("#000");
                                height("84px");
                            }});
                        }});
                    }});
                }});
            }});
        }});
    }
}
