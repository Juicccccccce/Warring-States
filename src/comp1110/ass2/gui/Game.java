package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States");
        Group root = new Group();
        GridPane grid = setup(placement);
        root.getChildren().add(grid);
        grid.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int[] array = mouseEntered(event);
                    char zhang = WarringStatesGame.findZhangPosition(placement);
                    int[] Zhang = Viewer.determineCoordinate(zhang);
//                    if (array[0] >=0 && array[0] <= 5 && array[1] >= 0 && array[1] <= 5) {
//                        grid.getChildren().remove(getNodeByRowColumnIndex(Zhang[0],Zhang[1],grid));
                    Image empty = new Image("comp1110/ass2/gui/assets/Character/empty.png"); //Remove previous ZhangYi's card
                    ImageView imageView1 = new ImageView(empty);
                    imageView1.setFitWidth(100);
                    imageView1.setFitHeight(100);
                    grid.getChildren().remove(getNodeByRowColumnIndex(array[1],array[0],grid));
                    grid.add(imageView1,Zhang[1],Zhang[0]);    //Replace the selcted position card with ZhangYi's card
                        String supportors = WarringStatesGame.allPosition(returnLocationChar(array[0],array[1]),placement);  //Get all cards which are needed to collect
                        for (int i =0; i < supportors.length(); i+=2) {                                //replace all cards need to be emptied
                            int[] a = Viewer.determineCoordinate(returnLocation(placement,supportors.substring(i,i+2)));
                            ImageView imageView2 = new ImageView(empty);
                            imageView2.setFitHeight(100);
                            imageView2.setFitWidth(100);
                            grid.getChildren().remove(getNodeByRowColumnIndex(a[0],a[1],grid));
                            grid.add(imageView2,a[1],a[0]);
                        }
                        Image ZhangYi = new Image("comp1110/ass2/gui/assets/Character/z9.png");     //replace the destination to ZhangYi
                        ImageView imageview = new ImageView(ZhangYi);
                        imageview.setFitHeight(100);
                        imageview.setFitWidth(100);
                        grid.add(imageview,array[0],array[1]);
                        placement = WarringStatesGame.deleteEmptyLocation(placement,returnLocationChar(array[0],array[1]));  //update the set up information
                        placement += "z9" + returnLocationChar(array[0],array[1]);
                }
            });
        });
        Scene scene = new Scene(root, 935, 732);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    //return the locationchar for where the support is in the placement
    public static char returnLocation(String placement, String supporter) {
        char re = 'x';
        for (int i = 0; i < placement.length(); i+=3) {
            if (placement.charAt(i) == supporter.charAt(0) && placement.charAt(i+1) == supporter.charAt(1)) {
                re = placement.charAt(i+2);
            }
        }
        return re;
    }


    //return the locationchar for a certian column and row
    public static char returnLocationChar(int col, int row) {
        char a = '=';
        if (col == 0) {
            if (row == 0) {
                a = '4';
            } else if (row == 1) {
                a = '5';
            } else if (row == 2) {
                a = '6';
            } else if (row == 3) {
                a = '7';
            } else if (row == 4) {
                a = '8';
            } else if (row == 5) {
                a = '9';
            }
        } else if (col == 1) {
            if (row == 0) {
                a = 'Y';
            } else if (row == 1) {
                a = 'Z';
            } else if (row == 2) {
                a = '0';
            } else if (row == 3) {
                a = '1';
            } else if (row == 4) {
                a = '2';
            } else if (row == 5) {
                a = '3';
            }
        } else if (col == 2) {
            if (row == 0) {
                a = 'S';
            } else if (row == 1) {
                a = 'T';
            } else if (row == 2) {
                a = 'U';
            } else if (row == 3) {
                a = 'V';
            } else if (row == 4) {
                a = 'W';
            } else if (row == 5) {
                a = 'X';
            }
        } else if (col == 3) {
            if (row == 0) {
                a = 'M';
            } else if (row == 1) {
                a = 'N';
            } else if (row == 2) {
                a = 'O';
            } else if (row == 3) {
                a = 'P';
            } else if (row == 4) {
                a = 'Q';
            } else if (row == 5) {
                a = 'R';
            }
        } else if (col == 4) {
            if (row == 0) {
                a = 'G';
            } else if (row == 1) {
                a = 'H';
            } else if (row == 2) {
                a = 'I';
            } else if (row == 3) {
                a = 'J';
            } else if (row == 4) {
                a = 'K';
            } else if (row == 5) {
                a = 'L';
            }
        } else if (col == 5) {
            if (row == 0) {
                a = 'A';
            } else if (row == 1) {
                a = 'B';
            } else if (row == 2) {
                a = 'C';
            } else if (row == 3) {
                a = 'D';
            } else if (row == 4) {
                a = 'E';
            } else if (row == 5) {
                a = 'F';
            }
        }
      return a;
    }

    //set the initial board
    public GridPane setup(String placement) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        for (int i = 0; i < placement.length() - 1; i += 3) {
            int a = Viewer.determineCoordinate(placement.charAt(i + 2))[0];
            int b = Viewer.determineCoordinate(placement.charAt(i + 2))[1];
            if (placement.charAt(i) == 'a') {
                if (placement.charAt(i + 1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '5') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a5.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '6') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a6.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '7') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/a7.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }
            } else if (placement.charAt(i) == 'b') {
                if (placement.charAt(i + 1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '5') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b5.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '6') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/b6.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }
            } else if (placement.charAt(i) == 'c') {
                if (placement.charAt(i + 1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '5') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/c5.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }
            } else if (placement.charAt(i) == 'd') {
                if (placement.charAt(i + 1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '4') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/d4.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }
            } else if (placement.charAt(i) == 'e') {
                if (placement.charAt(i + 1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '3') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/e3.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }
            } else if (placement.charAt(i) == 'f') {
                if (placement.charAt(i + 1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '2') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f2.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }
            } else if (placement.charAt(i) == 'g') {
                if (placement.charAt(i + 1) == '0') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/g0.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                } else if (placement.charAt(i + 1) == '1') {
                    Image image = new Image("comp1110/ass2/gui/assets/Character/f1.png");
                    ImageView imageview = new ImageView(image);
                    imageview.setFitHeight(100);
                    imageview.setFitWidth(100);
                    grid.add(imageview, b, a);
                }
            } else if (placement.charAt(i) == 'z') {
                Image image = new Image("comp1110/ass2/gui/assets/Character/z9.png");
                ImageView imageview = new ImageView(image);
                imageview.setFitHeight(100);
                imageview.setFitWidth(100);
                grid.add(imageview, b, a);
            }}
            return grid;
        }

    //determine which index the mouse is reffered to
        public int[] mouseEntered(MouseEvent e) {
            int[] array = new int[2];
            Node source = (Node)e.getSource() ;
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


