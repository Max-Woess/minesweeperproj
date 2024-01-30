package com.example.minesweeperproj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.HashSet;
import java.util.Random;

public class HelloController {

    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {


        final int easyBombCount = 8;


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
        int ind = 0;
        for (int x = 0; x < rowsTotalx; x++) {
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



                PlayPane playPane = new PlayPane(bombCountnearby);
                playPane.setMinSize(50, 50);
                playPane.setStyle("-fx-font-size:16");
                GridPane.setRowIndex(playPane, x);
                GridPane.setColumnIndex(playPane, y);
                gridPane.getChildren().add(playPane);

                if (bombFields.contains(ind)) {
                    setBomb(playPane);
                } else {
                    if (bombCountnearby == 0) {
                        playPane.setText("0");
                    } else {
                        playPane.setText(Integer.toString(bombCountnearby));
                    }
                }

                ind++;
            }
        }
    }


    @FXML
    private void setBomb(PlayPane pp) {
        pp.hasBomb = true;
    }

    @FXML
    private boolean checkForBomb(PlayPane ppane) {
        return ppane.hasBomb;
    }

}