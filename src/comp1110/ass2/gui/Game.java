package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import comp1110.ass2.WarringStatesGame;
import comp1110.ass2.gui.Viewer;

import javafx.event.Event;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    int idx = new Random().nextInt(PLACEMENTS.length);
    public String placement = (PLACEMENTS[idx]);
    String moveSequence = "";
    int x = 0;
    int y = 0;
//    public static String placement = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent

    // FIXME Task 12: Integrate a more advanced opponent into your game
    int count = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States");
        String txt = "Warring States";
        Text text1 = new Text(txt);
        text1.setX(100);
        text1.setY(300);
        text1.setFill(Color.BLACK);
        text1.setFont(Font.font(null, FontWeight.BOLD,100));
        Button button1 = new Button("Enter Normal Game");
        button1.setLayoutX(240);
        button1.setLayoutY(535);
        Button button2 = new Button("Enter AI Game");
        button2.setLayoutX(440);
        button2.setLayoutY(535);
        Button button3 = new Button("Help");
        button3.setLayoutX(600);
        button3.setLayoutY(535);
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Group root = startPlay();
                Scene scene = new Scene(root, 935, 732);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        Group root = new Group();
        root.getChildren().addAll(button1,button2,button3,text1);
        Scene scene = new Scene(root, 935, 732);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //set border pane
    public BorderPane setBorder(GridPane grid) {
        BorderPane border = new BorderPane();
        border.setLeft(grid);
        return border;
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Group startPlay() {
        Group root = new Group();
        GridPane grid = setup(placement);
        BorderPane border = setBorder(grid);
        root.getChildren().add(border);
        grid.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int[] array = mouseEntered(event);
                    if (WarringStatesGame.isMoveLegal(placement, returnLocationChar(array[0], array[1]))) {
                        char zhang = WarringStatesGame.findZhangPosition(placement);
                        int[] Zhang = Viewer.determineCoordinate(zhang);
                        Image empty = new Image("comp1110/ass2/gui/assets/Character/empty.png"); //Remove previous ZhangYi's card
                        ImageView imageView1 = new ImageView(empty);
                        imageView1.setFitWidth(100);
                        imageView1.setFitHeight(100);
                        grid.getChildren().remove(getNodeByRowColumnIndex(array[1], array[0], grid));
                        grid.add(imageView1, Zhang[1], Zhang[0]);    //Replace the selcted position card with ZhangYi's card
                        String supportors = WarringStatesGame.allPosition(returnLocationChar(array[0], array[1]), placement);  //Get all cards which are needed to collect
                        for (int i = 0; i < supportors.length(); i += 2) {                                //replace all cards need to be emptied
                            int[] a = Viewer.determineCoordinate(returnLocation(placement, supportors.substring(i, i + 2)));
                            ImageView imageView2 = new ImageView(empty);
                            imageView2.setFitHeight(100);
                            imageView2.setFitWidth(100);
                            grid.getChildren().remove(getNodeByRowColumnIndex(a[0], a[1], grid));
                            grid.add(imageView2, a[1], a[0]);
                        }
                        moveSequence += returnLocationChar(array[0],array[1]);
                        for (int j = 0; j < supportors.length(); j +=2) {
                            if (count % 2 == 0) {
                                Image image3 = new Image("comp1110/ass2/gui/assets/Character/" + supportors.charAt(j) + supportors.charAt(j + 1) + ".png");
                                ImageView imageView = new ImageView(image3);
                                imageView.setFitHeight(100);
                                imageView.setFitWidth(100);
                                imageView.setX(680);
                                imageView.setY(580-30*x);
                                root.getChildren().add(imageView);
                                x += 1;
                            } else { Image image3 = new Image("comp1110/ass2/gui/assets/Character/" + supportors.charAt(j) + supportors.charAt(j + 1) + ".png");
                                ImageView imageView = new ImageView(image3);
                                imageView.setFitWidth(100);
                                imageView.setFitHeight(100);
                                imageView.setX(790);
                                imageView.setY(580-30*y);
                                root.getChildren().add(imageView);
                                y += 1;
                            }
                        }
                        Image ZhangYi = new Image("comp1110/ass2/gui/assets/Character/z9.png");     //replace the destination to ZhangYi
                        ImageView imageview = new ImageView(ZhangYi);
                        imageview.setFitHeight(100);
                        imageview.setFitWidth(100);
                        grid.add(imageview, array[0], array[1]);
                        placement = WarringStatesGame.deleteEmptyLocation(placement, returnLocationChar(array[0], array[1]));  //update the set up information
                        placement += "z9" + returnLocationChar(array[0], array[1]);
                    }
                    if (WarringStatesGame.generateMove(placement) != '\0') {
                        count = count + 1;
                    }
                    else {
                        String initial = (PLACEMENTS[idx]);
                        int num1 = 0;
                        int num2 = 0;
                        int cal1 = 0;
                        int cal2 = 0;
                        String str = "";
                        int[] array1 = WarringStatesGame.getFlags(initial,moveSequence,2);
                        for (int i = 0; i < 7; i ++) {
                            if (array1[i] == 0) {
                                num1 += 1;
                                cal1 += 8 - i;
                            } else if (array1[i] == 1) {
                                num2 += 1;
                                cal2 += 8 - i;
                            }
                        }
                        if (num1 > num2) {
                            str = "Player1 is WIN" ;
                        } else if (num1 < num2) {
                            str = "PLayer2 is WIN";
                        } else if (num1 == num2) {
                            if(cal1 > cal2) {
                                str = "Player1 is WIN";
                            } else {
                                str = "Player2 is WIN";
                            }
                        }
                        Text text = new Text(str);
                        text.setFill(Color.RED);
                        text.setFont(Font.font(null, FontWeight.BOLD,100));
                        text.setStrokeWidth(20);
                        text.setX(50);
                        text.setY(300);
                        root.getChildren().add(text);
                        System.out.println(moveSequence);
                    }
                }

            });

        });
        return root;
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    //return the locationchar for where the support is in the placement
    public static char returnLocation(String placement, String supporter) {
        char re = 'x';
        for (int i = 0; i < placement.length(); i += 3) {
            if (placement.charAt(i) == supporter.charAt(0) && placement.charAt(i + 1) == supporter.charAt(1)) {
                re = placement.charAt(i + 2);
            }
        }
        return re;
    }

    static String  locations = "456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";
    //return the locationchar for a certian column and row
    public static char returnLocationChar(int col, int row) {
        return locations.charAt(6*col + row);
    }

    //set the initial board
    public GridPane setup(String placement) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        for (int i = 0; i < placement.length() - 1; i += 3) {
            int a = Viewer.determineCoordinate(placement.charAt(i + 2))[0];
            int b = Viewer.determineCoordinate(placement.charAt(i + 2))[1];
            Image image = new Image("comp1110/ass2/gui/assets/Character/" + placement.charAt(i) + placement.charAt(i + 1) + ".png");
            ImageView imageview = new ImageView(image);
            imageview.setFitHeight(100);
            imageview.setFitWidth(100);
            grid.add(imageview, b, a);

        }
        return grid;
    }

    //determine which index the mouse is reffered to
    public int[] mouseEntered(MouseEvent e) {
        int[] array = new int[2];
        Node source = (Node) e.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        array[0] = colIndex;
        array[1] = rowIndex;
        return array;
    }

    static final String[] PLACEMENTS = {
            "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"
    };

}


