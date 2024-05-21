package de.herrmann.holger.offtonewworlds.dialogs.buildingdialog;

import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.herrmann.holger.offtonewworlds.model.ground.PathNorthToEastInfo;
import de.herrmann.holger.offtonewworlds.model.ground.PathNorthToSouthInfo;
import de.herrmann.holger.offtonewworlds.model.ground.PathWestToEastInfo;
import de.herrmann.holger.offtonewworlds.model.ground.PathWestToNorthInfo;
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
                width("50%").height("40%");
                childLayoutHorizontal();

                // *************************************************************************************
                // Selection of category on the left hand side
                // *************************************************************************************
                panel(new PanelBuilder("categorySelection") {{
                    width("20%").height("100%").backgroundColor("#5E2F00");
                    childLayoutVertical();

                    image(new ImageBuilder() {{
                        filename("assets/icons/button_grass.png");
                        width("64px").height("32px").marginTop("30px").alignCenter();
                        visibleToMouse(true).interactOnClick("openGroundSelection()");
                    }});

                    text(new TextBuilder() {{
                        text("Boden");
                        width("64px").height("20px").alignCenter();
                        font("aurulent-sans-16.fnt").color("#000");
                        interactOnClick("openGroundSelection()");
                    }});

                    image(new ImageBuilder() {{
                        filename("assets/icons/button_menu_building.png");
                        width("64px").height("64px").marginTop("30px").alignCenter();
                        visibleToMouse(true).interactOnClick("openBuildingSelection()");
                    }});

                    text(new TextBuilder() {{
                        text("Wohngebäude");
                        width("64px").height("20px").alignCenter();
                        font("aurulent-sans-16.fnt").color("#000");
                        interactOnClick("openBuildingSelection()");
                    }});
                }});

                // *************************************************************************************
                // Scroll panel with tiles on the right hand side.
                // *************************************************************************************
                panel(new PanelBuilder("tileSelectionPanel") {{
                    width("80%").height("100%").backgroundColor("#954A00");
                    childLayoutAbsoluteInside();

                    panel(new PanelBuilder() {{

                        width("100%");
                        childLayoutVertical();

                        // Path from East to West
                        // ************************************************
                        panel(new PanelBuilder("pathWestToEastPanel") {{
                            width("100%").height("80px").marginTop("20px").paddingRight("20px").visible(false);
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("30%").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWestEast.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse(true).interactOnClick("selectTile(PathWestToEast)");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWestToEastInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("70%").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathWestToEastInfo.getDescription());
                                    width("100%").height("52px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});

                        // Path from North to South
                        // ************************************************
                        panel(new PanelBuilder("pathNorthToSouthPanel") {{
                            width("100%").height("80px").marginTop("20px").paddingRight("20px").visible(false);
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("30%").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathNorthSouth.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse(true).interactOnClick("selectTile(PathNorthToSouth)");
                                }});
                                text(new TextBuilder() {{
                                    text(PathNorthToSouthInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("70%").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathNorthToSouthInfo.getDescription());
                                    width("100%").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});

                        // Path from North to East
                        // ************************************************
                        panel(new PanelBuilder("pathNorthToEastPanel") {{
                            width("100%").height("80px").marginTop("20px").paddingRight("20px").visible(false);
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("30%").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathNorthEast.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse(true).interactOnClick("selectTile(PathNorthToEast)");
                                }});
                                text(new TextBuilder() {{
                                    text(PathNorthToEastInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("70%").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathNorthToEastInfo.getDescription());
                                    width("100%").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});

                        // Path from West to North
                        // ************************************************
                        panel(new PanelBuilder("pathWestToNorthPanel") {{
                            width("100%").height("80px").marginTop("20px").paddingRight("20px").visible(false);
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("30%").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWestNorth.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse(true).interactOnClick("selectTile(PathWestToNorth)");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWestToNorthInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("70%").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathWestToNorthInfo.getDescription());
                                    width("100%").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});
                    }});

                    panel(new PanelBuilder() {{

                        width("100%");
                        childLayoutVertical();

                        // Building 1
                        // ************************************************
                        panel(new PanelBuilder("building1Panel") {{
                            width("100%").height("80px").marginTop("20px").paddingRight("20px").visible(false);
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("30%").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_menu_building.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse(true).interactOnClick("selectTile(Building)");
                                }});
                                text(new TextBuilder() {{
                                    text("Building 1");
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("70%").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text("Building 1");
                                    width("100%").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});
                    }});
                }});
            }});
        }});
    }
}
