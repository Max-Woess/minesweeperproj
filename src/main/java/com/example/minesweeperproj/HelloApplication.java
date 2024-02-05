package com.example.minesweeperproj;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {

    private int secondsElapsed = 0;
    private Timeline timer;

    @Override
    public void start(Stage stage) throws IOException{
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsElapsed++;
            stage.setTitle(String.valueOf(Converter(secondsElapsed)));
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        int[] difficulty123 = HelloController.setDifficult;
        System.out.println("nach");
        Scene scene = new Scene(fxmlLoader.load(), difficulty123[4], difficulty123[5]);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    static String Converter(int seconds){

        int sec = seconds;
        int min = 0;
        int hour = 0;
        String myString;
        while (sec >= 60){
            min++;
            sec = sec - 60;
        }
        while (min >= 60){
            hour++;
            min = min - 60;
        }
        myString = hour + ":" + min + ":" + sec;
        return myString;
    }
    public static void main(String[] args) {
        launch();
    }
}