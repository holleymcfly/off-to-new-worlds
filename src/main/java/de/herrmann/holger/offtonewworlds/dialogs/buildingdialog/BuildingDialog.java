package de.herrmann.holger.offtonewworlds.dialogs.buildingdialog;

import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.herrmann.holger.offtonewworlds.dialogs.MyScrollPanelControl;
import de.herrmann.holger.offtonewworlds.model.ground.*;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.*;
import de.lessvoid.nifty.controls.scrollpanel.builder.ScrollPanelBuilder;

import javax.annotation.Nonnull;

public class BuildingDialog extends ScreenBuilder {

    public BuildingDialog(@Nonnull String id, OffToNewWorlds application, Nifty nifty) {
        super(id);
        controller(new BuildingDialogController(application));
        buildUserInterface(nifty);
    }

    private void buildUserInterface(Nifty nifty) {

        nifty.loadControlFile("nifty-default-controls.xml");
        nifty.loadStyleFile("nifty-default-styles.xml");

        nifty.loadStyleFile("assets/nifty-gui/nifty-custom-styles.xml");

        layer(new LayerBuilder("foreground") {{
            childLayoutCenter();

            panel(new PanelBuilder("buildingDialog") {{
                width("1000px").height("400px");
                childLayoutAbsoluteInside();

                // *************************************************************************************
                // Selection of category on the left hand side
                // *************************************************************************************
                panel(new PanelBuilder("categorySelection") {{
                    width("150px").height("400px").backgroundColor("#5E2F00");
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
                control(new ScrollPanelBuilder("groundSelectionPanel") {{

                    controller(new MyScrollPanelControl());

                    set("vertical", "true").set("horizontal", "false");
                    x("150px").width("850px").height("400px");

                    childLayoutVertical();

                    panel(new PanelBuilder() {{

                        width("850px").backgroundColor("#954A00");
                        childLayoutVertical();

                        // Path from East to West
                        // ************************************************
                        panel(new PanelBuilder("pathWestToEastPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
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
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathWestToEastInfo.getDescription());
                                    width("600px").height("52px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});

                        // Path from North to South
                        // ************************************************
                        panel(new PanelBuilder("pathNorthToSouthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
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
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathNorthToSouthInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});

                        // Path from North to East
                        // ************************************************
                        panel(new PanelBuilder("pathNorthToEastPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
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
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathNorthToEastInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});

                        // Path from West to North
                        // ************************************************
                        panel(new PanelBuilder("pathWestToNorthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
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
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathWestToNorthInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});

                        // Path from West to South
                        // ************************************************
                        panel(new PanelBuilder("pathWestToSouthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWestSouth.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse(true).interactOnClick("selectTile(PathWestToSouth)");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWestToSouthInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text(PathWestToSouthInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                }});
                            }});
                        }});
                    }});
                }});

                control(new ScrollPanelBuilder("buildingSelectionPanel") {{

                    controller(new MyScrollPanelControl());

                    visible(false);

                    set("vertical", "true").set("horizontal", "false");
                    x("150px").width("850px").height("400px");

                    childLayoutVertical();

                    panel(new PanelBuilder() {{

                        width("850px").backgroundColor("#954A00");
                        childLayoutVertical();

                        // Building 1
                        // ************************************************
                        panel(new PanelBuilder("building1Panel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
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
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();

                                text(new TextBuilder() {{
                                    text("Building 1");
                                    width("600px").alignLeft().valignCenter();
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
