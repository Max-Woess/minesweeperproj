package com.example.minesweeperproj;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {

    @FXML
    private GridPane gridPane;
    boolean darkgreen;



    static int[] setDifficult = gSetDifficulty();

    public static int[] gSetDifficulty() {
        int[] difficulty = {10, 8, 8, 64, 417, 417};
        int[] difficulty1 = {40, 16, 16, 256, 817, 817};
        int[] difficulty2 = {99, 30, 16, 480, 1517, 817};
        int[] choice = difficulty;
        AtomicInteger caserich = new AtomicInteger();
        List<String> choices = Arrays.asList("easy", "medium", "hard");

        System.out.println("getting difficulty in method");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("easy", choices);
        dialog.setTitle("select difficulty");
        dialog.setHeaderText("Please select a difficulty");
        dialog.setContentText("Difficulty:");
        dialog.showAndWait()
                .ifPresent(response -> {
                    if (response.equals("easy")) {
                        caserich.set(1);
                    } else if (response.equals("medium")) {
                        caserich.set(2);
                    } else if (response.equals("hard")) {
                        caserich.set(3);
                    }
                });

        switch (caserich.get()) {
            case 1:
                choice = difficulty;
                break;
            case 2:
                choice = difficulty1;
                break;
            case 3:
                choice = difficulty2;
                break;
        }

        return choice;

    }


    @FXML
    private void initialize() {




        Random rand = new Random();
        int bombCount = setDifficult[0];

        HashSet<Integer> bombFields = new HashSet<>();


        while (bombFields.size() < bombCount) {
            int randomInt = rand.nextInt(setDifficult[3]);
            bombFields.add(randomInt);
        }

        for (int qwe : bombFields) {
            System.out.println(qwe);
        }

        int columnsTotaly = setDifficult[1];
        int rowsTotalx = setDifficult[2];
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
                playPane.setMaxSize(50, 50);
                playPane.setStyle("-fx-font-size:16");
                if (darkgreen) {
                    playPane.setStyle("-fx-background-color: darkGreen");
                } else {
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