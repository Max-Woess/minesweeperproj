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




        final int easyBombCount = 8 ;


        Random rand = new Random();
        int bombCount = easyBombCount;

        HashSet<Integer> bombFields = new HashSet<>();

        while(bombFields.size() < bombCount){
            int randomInt = rand.nextInt(64);
            bombFields.add(randomInt);
        }

        for(int qwe : bombFields){
            System.out.println(qwe);
        }




        int columnsTotaly = 8;
        int rowsTotalx = 8;
        int ind = 0;
        for (int x = 0; x < rowsTotalx; x++) {
            for (int y = 0; y < columnsTotaly; y++) {

                PlayPane playPane = new PlayPane();
                playPane.setMinSize(50, 50);
                playPane.setStyle("-fx-font-size:16");
                GridPane.setRowIndex(playPane, x);
                GridPane.setColumnIndex(playPane, y);
                gridPane.getChildren().add(playPane);
                playPane.setText(x + " " + y);
                if(bombFields.contains(ind)){
                    setBomb(playPane);
                }
                ind++;

            }
        }


    }



    @FXML
    private void setBomb(PlayPane pp){
        pp.setText("x");
        pp.setStyle("-fx-background-color: Red");
        pp.hasBomb = true;
    }

    @FXML
    private boolean checkForBomb(PlayPane ppane){
        return ppane.hasBomb;
    }


    @FXML
    private int bombsNearby(){
        //future implementation
        return 0;
    }
}