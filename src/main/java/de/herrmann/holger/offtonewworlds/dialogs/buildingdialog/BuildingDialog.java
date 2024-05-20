package de.herrmann.holger.offtonewworlds.dialogs.buildingdialog;

import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.herrmann.holger.offtonewworlds.model.ground.PathEastToWestInfo;
import de.herrmann.holger.offtonewworlds.model.ground.PathNorthToSouthInfo;
import de.lessvoid.nifty.builder.*;

import javax.annotation.Nonnull;

public class BuildingDialog extends ScreenBuilder {

    public BuildingDialog(@Nonnull String id, OffToNewWorlds application) {
        super(id);
        controller(new BuildingDialogController(application));
        buildUserInterface();
    }

    private void buildUserInterface() {

        layer(new LayerBuilder("foreground") {{

            childLayoutCenter();

            panel(new PanelBuilder("buildingDialog") {{

                width("50%");
                height("30%");

                alignCenter();
                valignCenter();

                childLayoutHorizontal();

                panel(new PanelBuilder("categorySelectionPanel") {{

                    width("20%");
                    height("100%");

                    alignCenter();
                    backgroundColor("#954A00");

                    childLayoutVertical();

                    image(new ImageBuilder() {{
                        filename("assets/icons/button_grass.png");

                        width("64px");
                        height("32px");
                        marginTop("30px");

                        alignCenter();

                        visibleToMouse(true);
                        interactOnClick("openGroundSelection()");
                    }});

                    text(new TextBuilder() {{
                        text("Boden");

                        width("64px");
                        height("20px");

                        alignCenter();
                        color("#000");
                        font("aurulent-sans-16.fnt");

                        interactOnClick("openGroundSelection()");
                    }});

                    image(new ImageBuilder() {{
                        filename("assets/icons/button_menu_building.png");

                        width("64px");
                        height("64px");
                        marginTop("30px");

                        alignCenter();

                        visibleToMouse(true);
                        interactOnClick("openBuildingSelection()");
                    }});

                    text(new TextBuilder() {{
                        text("Wohngebäude");

                        width("64px");
                        height("20px");

                        alignCenter();
                        color("#000");
                        font("aurulent-sans-16.fnt");

                        interactOnClick("openBuildingSelection()");
                    }});
                }});

                panel(new PanelBuilder("selectionPanel") {{

                    width("20%");
                    height("100%");

                    backgroundColor("#FDCE2D");
                    alignLeft();

                    childLayoutAbsoluteInside();

                    panel(new PanelBuilder("groundSelection") {{

                        width("100%");

                        visible(false);
                        childLayoutVertical();

                        image(new ImageBuilder() {{
                            filename("assets/icons/button_pathEastWest.png");

                            width("64px");
                            height("32px");
                            marginTop("30px");
                            alignCenter();

                            visibleToMouse(true);
                            interactOnClick("selectTile(PathEastToWest)");
                        }});

                        text(new TextBuilder() {{
                            text("Pfad von West nach Ost");

                            width("64px");
                            height("20px");
                            alignCenter();

                            font("aurulent-sans-16.fnt");
                            color("#000");
                        }});

                        image(new ImageBuilder() {{
                            filename("assets/icons/button_pathNorthSouth.png");

                            width("64px");
                            height("32px");
                            marginTop("30px");
                            alignCenter();

                            visibleToMouse(true);
                            interactOnClick("selectTile(PathNorthToSouth)");
                        }});

                        text(new TextBuilder() {{
                            text("Pfad von Nord nach Süd");

                            width("64px");
                            height("20px");
                            alignCenter();

                            font("aurulent-sans-16.fnt");
                            color("#000");
                        }});
                    }});

                    panel(new PanelBuilder("buildingSelection") {{

                        width("100%");

                        visible(false);

                        childLayoutVertical();

                        image(new ImageBuilder() {{
                            filename("assets/icons/button_menu_building.png");

                            width("64px");
                            height("64px");
                            marginTop("30px");

                            alignCenter();

                            visibleToMouse(true);
                            interactOnClick("selectTile(\"Building\")");
                        }});

                        text(new TextBuilder() {{
                            text("Irgendein Gebäude...");

                            width("64px");
                            height("20px");

                            alignCenter();
                            font("aurulent-sans-16.fnt");
                            color("#000");
                        }});

                        image(new ImageBuilder() {{
                            filename("assets/icons/button_menu_building.png");

                            width("64px");
                            height("64px");
                            marginTop("30px");

                            alignCenter();

                            visibleToMouse(true);
                            interactOnClick("selectTile(\"Building\")");
                        }});

                        text(new TextBuilder() {{
                            text("Irgendein Gebäude...");

                            width("64px");
                            height("20px");

                            alignCenter();
                            font("aurulent-sans-16.fnt");
                            color("#000");
                        }});
                    }});
                }});

                panel(new PanelBuilder("descriptionPanel") {{

                    width("60%");
                    height("100%");

                    alignLeft();
                    paddingLeft("20px");
                    backgroundColor("#FEE17E");

                    childLayoutAbsoluteInside();

                    panel(new PanelBuilder("groundDescriptionParent") {{

                        width("90%");
                        childLayoutVertical();

                        panel(new PanelBuilder("pathEastToWestDescription") {{

                            width("100%");

                            visible(false);
                            childLayoutVertical();
                            valignCenter();

                            text(new TextBuilder() {{
                                text(PathEastToWestInfo.getDescription());

                                width("100%");
                                height("84px");
                                marginTop("20px");

                                alignLeft();
                                valignCenter();
                                wrap(true);
                                font("aurulent-sans-16.fnt");
                                color("#000");
                            }});
                        }});

                        panel(new PanelBuilder("pathNorthToSouthDescription") {{

                            width("100%");

                            visible(false);
                            childLayoutVertical();
                            valignCenter();

                            text(new TextBuilder() {{
                                text(PathNorthToSouthInfo.getDescription());

                                width("100%");
                                height("84px");
                                marginTop("20px");

                                alignLeft();
                                valignCenter();
                                wrap(true);
                                font("aurulent-sans-16.fnt");
                                color("#000");
                            }});
                        }});
                    }});

                    panel(new PanelBuilder("buildingDescriptionParent") {{

                        width("90%");
                        childLayoutVertical();

                        panel(new PanelBuilder("buildingDescription") {{

                            width("100%");

                            visible(false);

                            childLayoutVertical();
                            valignCenter();

                            text(new TextBuilder() {{
                                text("Beschreibung: Irgendein Gebäude...");

                                width("100%");
                                height("84px");
                                marginTop("20px");

                                alignLeft();
                                font("aurulent-sans-16.fnt");
                                color("#000");
                            }});
                        }});

                        panel(new PanelBuilder("buildingDescription2") {{

                            width("100%");

                            visible(false);

                            childLayoutVertical();
                            valignCenter();

                            text(new TextBuilder() {{
                                text("Beschreibung2: Irgendein Gebäude...");

                                width("100%");
                                height("84px");
                                marginTop("20px");

                                alignLeft();
                                font("aurulent-sans-16.fnt");
                                color("#000");
                            }});
                        }});
                    }});
                }});
            }});
        }});
    }
}
