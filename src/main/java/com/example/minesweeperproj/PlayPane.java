package com.example.minesweeperproj;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

public class PlayPane extends Button {

    boolean hasBomb = false;
    private boolean revealed = false;
    boolean isFlagged = false;
    private int bombCountNearby;

    int x, y;
    int rowsTotalx, columnsTotaly;
    PlayPane[][] playPane;

    public PlayPane(int bombCountNearby, int x, int y, int rowsTotalx, int columnsTotaly, PlayPane[][] playPane) {
        super();
        this.bombCountNearby = bombCountNearby;
        this.x = x;
        this.y = y;
        this.rowsTotalx = rowsTotalx;
        this.columnsTotaly = columnsTotaly;
        this.playPane = playPane;
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
            System.exit(0);
        } else {
            this.setStyle("-fx-background-color: White");
            this.setText(String.valueOf(bombCountNearby));
            System.out.println(bombCountNearby);
            revealConnectedTiles(bombCountNearby, x, y);

        }
    }

    public void flag(){
        isFlagged = true;
        this.setStyle("-fx-background-color: Blue");
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public void revealConnectedTiles(int bombs, int x, int y) {
        System.out.println(bombs);
        if(!(bombs == 0)){
            return;
        }

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && ny >= 0 && nx < rowsTotalx && ny < columnsTotaly) {
                    PlayPane nextTile = playPane[nx][ny];
                    if (!nextTile.isRevealed() && !nextTile.hasBomb) {
                        nextTile.reveal();

                    }
                }
            }
        }
    }


}
