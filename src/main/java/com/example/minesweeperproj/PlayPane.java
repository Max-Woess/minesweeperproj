package com.example.minesweeperproj;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

import java.text.Collator;

public class PlayPane extends Button {

    boolean hasBomb = false;
    private boolean revealed = false;
    boolean isFlagged = false;
    private int bombCountNearby;

    public PlayPane(int bombCountNearby) {
        super();
        this.bombCountNearby = bombCountNearby;
        this.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            this.reveal();
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            this.flag();
        }
    }

    public void reveal() {
        this.revealed = true;
        this.setDisable(true);
        if (this.hasBomb) {
            this.setStyle("-fx-background-color: Red");
            this.setText("X");
        }else{
            this.setStyle("-fx-background-color: White");
            this.setText(String.valueOf(bombCountNearby));
        }
    }

    public void flag(){
        isFlagged = true;
        this.setStyle("-fx-background-color: Blue");
    }

    public boolean isRevealed() {
        return this.revealed;
    }
}
