package comp1110.ass2.gui;

import com.sun.org.apache.xpath.internal.FoundIndex;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

import java.util.Scanner;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    public static String placement = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent

    // FIXME Task 12: Integrate a more advanced opponent into your game
    int count = 0;
    int x = 0;
    int y = 0;
    String moveSequence = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States");
        Group root = new Group();
        Group test = new Group();
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
//                    if (array[0] >=0 && array[0] <= 5 && array[1] >= 0 && array[1] <= 5) {
//                        grid.getChildren().remove(getNodeByRowColumnIndex(Zhang[0],Zhang[1],grid));
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
                            moveSequence += returnLocationChar(array[0],array[1]);
                        }
                        for (int j = 0; j < supportors.length(); j +=2) {
                              if (count % 2 == 0) {
                            Image image3 = new Image("comp1110/ass2/gui/assets/Character/" + supportors.charAt(j) + supportors.charAt(j + 1) + ".png");
                            ImageView imageView = new ImageView(image3);
                            imageView.setX(680);
                            imageView.setY(580-30*x);
                            root.getChildren().add(imageView);
                            x += 1;
                        } else { Image image3 = new Image("comp1110/ass2/gui/assets/Character/" + supportors.charAt(j) + supportors.charAt(j + 1) + ".png");
                                  ImageView imageView = new ImageView(image3);
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
                    System.out.println(WarringStatesGame.generateMove(placement));
                    if (WarringStatesGame.generateMove(placement) != '\0') {
                        count = count + 1;
                    }
                    else {
                        String initial = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";
//                        WarringStatesGame.getFlags(initial,moveSequence,2);
                        int num1 = 0;
                        int num2 = 0;
                        int cal1 = 0;
                        int cal2 = 0;
                        String str = "";
                        int[] array1 = WarringStatesGame.getFlags(initial,moveSequence,2);
                        for (int i = 0; i < 7; i ++) {
                            if (array1[i] == 0) {
                                num1 += 1;
                                cal1 += i;
                            } else if (array1[i] == 1) {
                                num2 += 1;
                                cal2 += i;
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
                        System.out.println(str);
                        Text text = new Text(str);
                        text.setFill(Color.RED);
                        text.setFont(Font.font(null, FontWeight.BOLD,100));
                        text.setStrokeWidth(20);
                        text.setX(50);
                        text.setY(300);
                        root.getChildren().add(text);
                    }
                }

            });

        });

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

}

//    void makePlacement(String placement) {
//        GridPane grid = new GridPane();
//        grid.setVgap(10);
//        grid.setHgap(10);
//        for (int i = 0; i < placement.length() - 1; i += 3) {
//            int a = Viewer.determineCoordinate(placement.charAt(i+2))[0];
//            int b = Viewer.determineCoordinate(placement.charAt(i+2))[1];
//            Rectangle rct = new Rectangle(60,60);
//            rct.setFill(Viewer.getColor(placement.charAt(i)));
//            String str = Viewer.determineKingdomName(placement.charAt(i)) + placement.charAt(i+1);
//            Text label = new Text(str);
//            rct.setAccessibleText(str);
//            StackPane stackPane = new StackPane();
//            stackPane.getChildren().addAll(rct,label);
//            grid.add(stackPane,b,a);
//        }
////        scene.getChildren().addAll(grid);
//    }


