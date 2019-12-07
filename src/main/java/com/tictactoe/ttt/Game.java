package com.tictactoe.ttt;

import javafx.scene.layout.GridPane;

public class Game {

    private GridPane grid;
    private GameController gameController = new GameController(new Board());

    public Game(GridPane gridPane) {
        this.grid = gridPane;
    }

    public void start() {
        grid.add(gameController.getBoard().getButtons().get(0), 0, 1);
        grid.add(gameController.getBoard().getButtons().get(1), 0, 2);
        grid.add(gameController.getBoard().getButtons().get(2), 0, 3);
        grid.add(gameController.getBoard().getButtons().get(3), 1, 1);
        grid.add(gameController.getBoard().getButtons().get(4), 1, 2);
        grid.add(gameController.getBoard().getButtons().get(5), 1, 3);
        grid.add(gameController.getBoard().getButtons().get(6),2, 1);
        grid.add(gameController.getBoard().getButtons().get(7),2 ,2);
        grid.add(gameController.getBoard().getButtons().get(8), 2, 3);
    }
}
