package comp1110.ass2.gui;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
        TilePane tile = new TilePane();//idea from Chaahat Jain
        tile.setLayoutX(145);
        tile.setLayoutY(5);
        tile.setHgap(50);
        tile.setVgap(50);
        tile.setPrefColumns(6);

        for (int i = 0; i < placement.length() - 1; i += 3) {
            int a = determineCoordinate(placement.charAt(i+2))[0];
            int b = determineCoordinate(placement.charAt(i+2))[1];
            Rectangle rct = new Rectangle(60*b,60*a,60,60);
            rct.setFill(getColor(placement.charAt(i)));
            String str = determineKingdomName(placement.charAt(i)) +  placement.charAt(i+1);
            Text label = new Text(str);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(rct,label);
            tile.getChildren().addAll(stackPane);
        }
        controls.getChildren().addAll(tile);
    }


    //Determine which kingdom the char refers to
    public static String determineKingdomName(char a) {
        String b = " ";
        switch (a) {
            case 'a':
                b = "Qin";
                break;
            case 'b':
                b = "Qi";
                break;
            case 'c':
                b = "Chu";
                break;
            case 'd':
                b= "Zhao";
                break;
            case 'e':
                b = "Han";
                break;
            case 'f':
                b = "Wei";
                break;
            case 'g':
                b ="Yan";
                break;
            case 'z':
                b = "ZhangYi";
                break;
        } return b;}

        // Return the coordinate for a locationChar
    public static int[] determineCoordinate(char locationChar) {
        int[] xy = new int[2];
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

    //Set the color for different kingdom
    public static Color getColor(char kingdom) {
        if (kingdom == 'a') {
            return Color.PINK;
        } else {
            if (kingdom == 'b') {
                return Color.YELLOW;
            } else {
                if (kingdom == 'c') {
                    return Color.GREEN;
                } else {
                    if (kingdom == 'd') {
                        return Color.BLUE;
                    } else {
                        if (kingdom == 'e') {
                            return Color.ORANGE;
                        } else {
                            if (kingdom == 'f') {
                                return Color.PURPLE;
                            } else {
                                if (kingdom == 'z') {
                                    return Color.RED;
                                }
                                else {
                                    if (kingdom == 'g') {
                                        return Color.GRAY;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return Color.WHITE;
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
