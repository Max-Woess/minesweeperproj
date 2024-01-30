package com.example.minesweeperproj;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PlayPane extends Button {

    boolean hasBomb = false;
    private boolean revealed = false;
    private int bombCountNearby;

    public PlayPane(int bombCountNearby) {
        super();
        this.bombCountNearby = bombCountNearby;
        this.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        this.reveal();
    }

    public void reveal() {
        this.revealed = true;
        this.setDisable(true);
        if (this.hasBomb) {
            this.setStyle("-fx-background-color: Red");
            this.setText("X");
        }else{
            this.setText(String.valueOf(bombCountNearby));
        }
    }

    public boolean isRevealed() {
        return this.revealed;
    }
}
