package com.example.minesweeperproj;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;

import java.util.HashSet;
import java.util.Random;

public class HelloController {

    @FXML
    private GridPane gridPane;
    Popup popup = new Popup();
    boolean darkgreen;


    //  bomb count, amount of tiles
    //  0           1
    int[] difficulty= { };
    int[] difficulty1 = { };
    int[] difficulty2 = {64, };

    @FXML
    private void initialize() {

        final int easyBombCount = 8;
        final int mediumBombCount = 32;


        Random rand = new Random();
        int bombCount = easyBombCount;

        HashSet<Integer> bombFields = new HashSet<>();


        while (bombFields.size() < bombCount) {
            int randomInt = rand.nextInt(64);
            bombFields.add(randomInt);
        }

        for (int qwe : bombFields) {
            System.out.println(qwe);
        }


        int columnsTotaly = 8;
        int rowsTotalx = 8;
        PlayPane[][] pPane = new PlayPane[rowsTotalx][columnsTotaly];
        int ind = 0;
        for (int x = 0; x < rowsTotalx; x++) {
            darkgreen = !darkgreen;
            for (int y = 0; y < columnsTotaly; y++) {

                int bombCountnearby = 0;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = x + dx;
                        int ny = y + dy;
                        if (nx >= 0 && ny >= 0 && nx < rowsTotalx && ny < columnsTotaly) {
                            if (bombFields.contains(nx * columnsTotaly + ny)) {
                                bombCountnearby++;
                            }


                        }
                    }
                }



                PlayPane playPane = new PlayPane(bombCountnearby, x, y, rowsTotalx, columnsTotaly, pPane, darkgreen);
                pPane[x][y] = playPane;
                playPane.setMinSize(50, 50);
                playPane.setStyle("-fx-font-size:16");
                if(darkgreen){
                    playPane.setStyle("-fx-background-color: darkGreen");
                }else {
                    playPane.setStyle("-fx-background-color: lime");
                }
                darkgreen = !darkgreen;

                GridPane.setRowIndex(playPane, x);
                GridPane.setColumnIndex(playPane, y);
                gridPane.getChildren().add(playPane);

                if (bombFields.contains(ind)) {
                    setBomb(playPane);
                }

                ind++;
            }
        }
    }


    @FXML
    private void setBomb(PlayPane pp) {
        pp.hasBomb = true;
    }


}