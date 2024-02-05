package com.example.minesweeperproj;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

import java.util.HashSet;

public class PlayPane extends Button {

    boolean hasBomb = false;
    private boolean revealed = false;
    boolean isFlagged = false;
    private int bombCountNearby;

    int x, y;
    int rowsTotalx, columnsTotaly;
    PlayPane[][] playPane;
    boolean dark;

    public PlayPane(int bombCountNearby, int x, int y, int rowsTotalx, int columnsTotaly, PlayPane[][] playPane, boolean dark) {
        super();
        this.bombCountNearby = bombCountNearby;
        this.x = x;
        this.y = y;
        this.rowsTotalx = rowsTotalx;
        this.columnsTotaly = columnsTotaly;
        this.playPane = playPane;
        this.dark = dark;
        this.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            this.reveal();
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            if (this.isFlagged) {
                unfflag();
            } else {
                this.flag();
            }
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

            this.setStyle("-fx-opacity: 1");
            this.setText(String.valueOf(bombCountNearby));
            System.out.println(bombCountNearby);
            revealConnectedTiles(bombCountNearby, x, y);

        }
    }

    public void flag() {
        isFlagged = true;
        if (checkAllBombsFlagged()) {
            System.out.println("You Win!");
        }
        this.setStyle("-fx-graphic: url(Untitled-1.png)");
    }

    public void unfflag() {
        isFlagged = false;
        this.setStyle("-fx-graphic: none");
        if (dark) {
            this.setStyle("-fx-background-color: darkgreen;");
        } else {
            this.setStyle("-fx-background-color: lime");
        }
    }

    public boolean isRevealed() {
        return this.revealed;
    }


    public void revealConnectedTiles(int bombs, int x, int y) {
        System.out.println(bombs);
        if (!(bombs == 0)) {
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


    public boolean checkAllBombsFlagged() {
        for (int x = 0; x < rowsTotalx; x++) {
            for (int y = 0; y < columnsTotaly; y++) {
                PlayPane currentPane = playPane[x][y];
                if (currentPane.hasBomb && !currentPane.isFlagged) {
                    return false;
                }
            }
        }
        return true;
    }


}
