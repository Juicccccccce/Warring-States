package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A very simple viewer for card layouts in the Warring States game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various card placements.
 */
public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */



    void makePlacement(String placement) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setHgap(10);
        for (int i = 0; i < placement.length() - 1; i += 3) {
            int a = Viewer.determineCoordinate(placement.charAt(i+2))[0];
            int b = Viewer.determineCoordinate(placement.charAt(i+2))[1];
            if (placement.charAt(i) == 'a') {
                if (placement.charAt(i+1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '5') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a5.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '6') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a6.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '7') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a7.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                }
            } else if (placement.charAt(i) == 'b') {
                if (placement.charAt(i+1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '5') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b5.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '6') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b6.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);}}
            else if (placement.charAt(i) == 'c') {
                if (placement.charAt(i+1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview,b,a);
                } else if (placement.charAt(i+1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i +1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '5') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c5.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }} else if (placement.charAt(i) == 'd') {
                if (placement.charAt(i+1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }} else if (placement.charAt(i) == 'e') {
                if (placement.charAt(i+1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }} else if (placement.charAt(i) == 'f') {
                if (placement.charAt(i+1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }} else if (placement.charAt(i) == 'g') {
                if (placement.charAt(i+1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/g0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i+1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }} else if (placement.charAt(i) == 'z') {
                Image image = new Image("comp1110/ass2/gui/assets/Character/z9.png");
                ImageView imageview = new ImageView(image);
                imageview.setFitHeight(100);
                imageview.setFitWidth(100);
                grid.add(imageview, b, a);
            }
        }
        controls.getChildren().addAll(grid);
    }

        // Return the coordinate for a locationChar
    public static int[] determineCoordinate(char locationChar) {
        int[] xy = new int[2];
        //xy[0] is the row
        if (locationChar == '4' || locationChar == 'Y' || locationChar == 'S' || locationChar == 'M' || locationChar == 'G' || locationChar == 'A') {
            xy[0] = 0;
        } else {
            if (locationChar == '5' || locationChar == 'Z' || locationChar == 'T' || locationChar == 'N' || locationChar == 'H' || locationChar == 'B') {
                xy[0] = 1;
            } else {
                if (locationChar == '6' || locationChar == '0' || locationChar == 'U' || locationChar == 'O' || locationChar == 'I' || locationChar == 'C') {
                    xy[0] = 2;
                } else {
                    if (locationChar == '7' || locationChar == '1' || locationChar == 'V' || locationChar == 'P' || locationChar == 'J' || locationChar == 'D') {
                        xy[0] = 3;
                    } else {
                        if (locationChar == '8' || locationChar == '2' || locationChar == 'W' || locationChar == 'Q' || locationChar == 'K' || locationChar == 'E') {
                            xy[0] = 4;
                        } else {
                            if (locationChar == '9' || locationChar == '3' || locationChar == 'X' || locationChar == 'R' || locationChar == 'L' || locationChar == 'F') {
                                xy[0] = 5;
                            }
                        }
                    }
                }
            }
        }
        //xy[1] is the column
        if (locationChar >= '4' && locationChar <= '9') {
            xy[1] = 0;
        } else {
            if (locationChar == 'Y' || locationChar == 'Z' || locationChar >= '0' && locationChar <= '3') {
                xy[1] = 1;
            } else {
                if (locationChar >= 'S' && locationChar <= 'X') {
                    xy[1] = 2;
                } else {
                    if (locationChar >= 'M' && locationChar <= 'R') {
                        xy[1] = 3;
                    } else {
                        if (locationChar >= 'G' && locationChar <= 'L') {
                            xy[1] = 4;
                        } else {
                            if (locationChar >= 'A' && locationChar <= 'F') {
                                xy[1] = 5;
                            }
                        }
                    }
                }
            }
        }
        return xy;
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
