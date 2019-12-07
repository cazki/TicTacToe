package com.tictactoe.ttt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTacToe2 extends Application {

    private static final Image imageback = new Image("tlo.png");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(700, 700, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(70, 100, 50, 140));
        grid.setHgap(90);
        grid.setVgap(100);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 700, 700, Color.BLACK);

        primaryStage.setTitle("TicTacToe - Cazki Edition");
        primaryStage.setScene(scene);
        primaryStage.show();

        Game game = new Game(grid);
        game.start();
    }
}
