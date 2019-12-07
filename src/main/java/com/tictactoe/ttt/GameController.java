package com.tictactoe.ttt;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class GameController {

    private Board board;
    private boolean isPlayerMove = true;

    public GameController(Board board) {
        this.board = board;
        board.getButtons().forEach(button -> button.setOnAction(event -> playerMovement(button)));
    }

    public void playerMovement(Button btn) {

        if (isPlayerMove) {
            ImageView img = new ImageView(board.getCircleImage());
            btn.setGraphic(img);
            System.out.println("Gracz wykonał ruch, kolej przeciwnika.");
            checkIsGameEnd();
            computerMove();
        }
        isPlayerMove = !isPlayerMove;

    }

    private void checkIsGameEnd() {
        Button[][] buttons = new Button[3][3];
        for (int i = 0, j = -1, k = 0; i < board.getButtons().size(); i++, k++) {
            if (i % 3 == 0) {
                j++;
                k = 0;
            }
            buttons[j][k] = board.getButtons().get(i);
        }

        Winner winnerRow = checkRow(buttons);
        Winner winnerColumn = checkColumn(buttons);
        Winner winnerAcross = checkAcross(buttons);
        boolean isDraw = checkIsDraw(buttons);

        if (!Winner.NONE.equals(winnerRow)) {
            System.out.println("Gre wygrał: " + winnerRow);
            showAlertEndGame(winnerRow);
        }
        if (!Winner.NONE.equals(winnerColumn)) {
            System.out.println("Gre wygrał: " + winnerColumn);
            showAlertEndGame(winnerColumn);
        }
        if (!Winner.NONE.equals(winnerAcross)) {
            System.out.println("Gre wygrał: " + winnerAcross);
            showAlertEndGame(winnerAcross);
        }
        if (isDraw) {
            showAlertEndGame(Winner.DRAW);
        }
    }

    private boolean checkIsDraw(Button[][] buttons) {
        int counter = 0;
        for (Button[] row : buttons) {
            for (Button btn : row) {
                if (btn.getGraphic() != null) {
                    // TODO: porównanie i podbicie
                    ImageView imageView = (ImageView) btn.getGraphic();
                    Image image = imageView.getImage();
                    if (board.getCircleImage().equals(image)) {
                        counter++;
                    }
                    if (board.getCrossImage().equals(image)) {
                        counter++;
                    }
                }
            }
        }
        return counter == 9;
    }

    private void showAlertEndGame(Winner winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec gry");
        alert.setHeaderText(null);
        if (!Winner.DRAW.equals(winner)) {
            alert.setContentText("Grę wygrał: " + winner + ". Gratulacje!");
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) board.getButtons().get(0).getScene().getWindow();
            stage.close();
            Platform.runLater(() -> {
                try {
                    new TicTacToe2().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private Winner checkAcross(Button[][] buttons) {
        int acrossCross = 0;
        int acrossCircle = 0;
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i][i].getGraphic() != null) {
                // TODO: porównanie i podbicie
                ImageView imageView = (ImageView) buttons[i][i].getGraphic();
                Image image = imageView.getImage();
                if (board.getCircleImage().equals(image)) {
                    acrossCircle++;
                }
                if (board.getCrossImage().equals(image)) {
                    acrossCross++;
                }
            }
        }
        if (acrossCross == 3) {
            return Winner.COMPUTER;
        }
        if (acrossCircle == 3) {
            return Winner.PLAYER;
        }
        acrossCross = 0;
        acrossCircle = 0;
        for (int i = buttons.length - 1; i > 0; i--) {
            for (int j = 0; j < buttons.length; j++) {
                System.out.println( i + " " + j);
                if (buttons[i][j].getGraphic() != null) {
                    // TODO: porównanie i podbicie
                    ImageView imageView = (ImageView) buttons[i][j].getGraphic();
                    Image image = imageView.getImage();
                    if (board.getCircleImage().equals(image)) {
                        acrossCircle++;
                    }
                    if (board.getCrossImage().equals(image)) {
                        acrossCross++;
                    }
                }
                System.out.println("acrossCross" +acrossCross);
                System.out.println("acrossCircle" +acrossCircle);
            }

            if (acrossCross == 3) {
                return Winner.COMPUTER;
            }
            if (acrossCircle == 3) {
                return Winner.PLAYER;
            }
        }
        return Winner.NONE;
    }

    private Winner checkColumn(Button[][] buttons) {
        for (Button[] value : buttons) {
            int columnCross = 0;
            int columnCircle = 0;
            for (Button button : value) {
                if (button.getGraphic() != null) {
                    // TODO: porównanie i podbicie
                    ImageView imageView = (ImageView) button.getGraphic();
                    Image image = imageView.getImage();
                    if (board.getCircleImage().equals(image)) {
                        columnCircle++;
                    }
                    if (board.getCrossImage().equals(image)) {
                        columnCross++;
                    }
                } else {
                    break;
                }
            }
            if (columnCross == 3) {
                return Winner.COMPUTER;
            }
            if (columnCircle == 3) {
                return Winner.PLAYER;
            }
        }
        return Winner.NONE;
    }

    private Winner checkRow(Button[][] buttons) {
//        int rowCross = 0;
//        int rowCircle = 0;
//        for (int i = 0; buttons[i] < buttons.length; i++) {
//            if (buttons[i][j].getGraphic() != null) {
//                ImageView imageView = (ImageView) buttons[i][i].getGraphic();
//                Image image = imageView.getImage();
//                if (board.getCircleImage().equals(image)) {
//                    rowCircle++;
//                }
//                if (board.getCrossImage().equals(image)) {
//                    rowCross++;
//                }
//            }
//        }
//        if (rowCross == 3) {
//            return Winner.COMPUTER;
//        }
//        if (rowCircle == 3) {
//            return Winner.PLAYER;
//        }
        return  Winner.NONE;
    }

    private void computerMove() {
        Random random = new Random();
        boolean isEmpty = false;
        do {
            int computerButtonNumber = random.nextInt(9);
            Button button = board.getButtons().get(computerButtonNumber);
            if (button.getGraphic() == null) {
                ImageView img = new ImageView(board.getCrossImage());
                button.setGraphic(img);
                isEmpty = true;
            }
        } while (!isEmpty);
    }


    public Board getBoard() {
        return board;
    }
}
