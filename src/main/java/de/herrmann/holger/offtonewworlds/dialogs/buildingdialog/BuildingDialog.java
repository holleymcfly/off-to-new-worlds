package de.herrmann.holger.offtonewworlds.dialogs.buildingdialog;

import de.herrmann.holger.offtonewworlds.core.OffToNewWorlds;
import de.herrmann.holger.offtonewworlds.dialogs.MyScreenBuilder;
import de.herrmann.holger.offtonewworlds.dialogs.MyScrollPanelControl;
import de.herrmann.holger.offtonewworlds.model.ground.*;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.scrollpanel.builder.ScrollPanelBuilder;

import javax.annotation.Nonnull;

public class BuildingDialog extends MyScreenBuilder {

    public BuildingDialog(@Nonnull String id, OffToNewWorlds application) {
        super(id);
        controller(new BuildingDialogController(application));
    }

    @Override
    public void create(Nifty nifty) {
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

                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                // *************************************************************************************
                // Selection of category on the left hand side
                // *************************************************************************************
                panel(new PanelBuilder("categorySelection") {{
                    width("150px").height("400px").backgroundColor("#5E2F00");
                    childLayoutVertical();
                    visibleToMouse().interactOnSecondaryClick("closeDialog()");

                    image(new ImageBuilder() {{
                        filename("assets/icons/button_grass.png");
                        width("64px").height("32px").marginTop("30px").alignCenter();
                        visibleToMouse().interactOnClick("openGroundSelection()").interactOnSecondaryClick("closeDialog()");
                    }});

                    text(new TextBuilder() {{
                        text("Boden");
                        width("64px").height("20px").alignCenter();
                        font("aurulent-sans-16.fnt").color("#000");
                        visibleToMouse().interactOnClick("openGroundSelection()").interactOnSecondaryClick("closeDialog()");
                    }});

                    image(new ImageBuilder() {{
                        filename("assets/icons/button_menu_building.png");
                        width("64px").height("64px").marginTop("30px").alignCenter();
                        visibleToMouse().interactOnClick("openBuildingSelection()").interactOnSecondaryClick("closeDialog()");
                    }});

                    text(new TextBuilder() {{
                        text("Wohngebäude");
                        width("64px").height("20px").alignCenter();
                        font("aurulent-sans-16.fnt").color("#000");
                        visibleToMouse().interactOnClick("openBuildingSelection()").interactOnSecondaryClick("closeDialog()");
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
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWE.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathWE)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWEInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathWEInfo.getDescription());
                                    width("600px").height("52px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from North to South
                        // ************************************************
                        panel(new PanelBuilder("pathNorthToSouthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathNS.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathNS)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathNSInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathNSInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from North to East
                        // ************************************************
                        panel(new PanelBuilder("pathNorthToEastPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathNE.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathNE)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathNEInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathNEInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from West to North
                        // ************************************************
                        panel(new PanelBuilder("pathWestToNorthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWN.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathWN)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWNInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathWNInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from West to South
                        // ************************************************
                        panel(new PanelBuilder("pathWestToSouthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWS.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathWS)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWSInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathWSInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from South to East
                        // ************************************************
                        panel(new PanelBuilder("pathSouthToEastPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathSE.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathSE)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathSEInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathSEInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from North to East and South
                        // ************************************************
                        panel(new PanelBuilder("pathNorthToEastAndSouthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathNES.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathNES)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathNESInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathNESInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from West to North and East
                        // ************************************************
                        panel(new PanelBuilder("pathWestToNorthAndEastPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWNE.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathWNE)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWNEInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathWNEInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});

                        // Path from West to North and South
                        // ************************************************
                        panel(new PanelBuilder("pathWestToNorthAndSouthPanel") {{
                            width("850px").height("80px").marginTop("20px").paddingRight("20px");
                            childLayoutHorizontal();
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_pathWNS.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(PathWNS)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text(PathWNSInfo.getShortDescription());
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text(PathWNSInfo.getDescription());
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
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
                            visibleToMouse().interactOnSecondaryClick("closeDialog()");

                            panel(new PanelBuilder() {{
                                width("200px").height("52px").alignLeft().valignCenter();
                                childLayoutVertical();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                image(new ImageBuilder() {{
                                    filename("assets/icons/button_menu_building.png");
                                    width("64px").height("32px").alignCenter().valignTop();
                                    visibleToMouse().interactOnClick("selectTile(Building)").interactOnSecondaryClick("closeDialog()");
                                }});
                                text(new TextBuilder() {{
                                    text("Building 1");
                                    width("64px").height("20px").alignCenter().valignTop();
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});

                            panel(new PanelBuilder() {{
                                width("650px").height("80px").alignRight();
                                childLayoutHorizontal();
                                visibleToMouse().interactOnSecondaryClick("closeDialog()");

                                text(new TextBuilder() {{
                                    text("Building 1");
                                    width("600px").alignLeft().valignCenter();
                                    wrap(true);
                                    font("aurulent-sans-16.fnt").color("#000");
                                    visibleToMouse().interactOnSecondaryClick("closeDialog()");
                                }});
                            }});
                        }});
                    }});
                }});
            }});
        }});
    }
}
