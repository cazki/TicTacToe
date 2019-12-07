package com.tictactoe.ttt;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Image backgroundImage = new Image("tlo.png");
    private Image circleImage = new Image("o.png");
    private Image crossImage = new Image("x.png");
    private List<Button> buttons = new ArrayList<>();

    public Board() {
        createButtons();
    }

    private void createButtons() {
        buttons = generateButtons(9);
    }

    private List<Button> generateButtons(int count) {
        List<Button> buttons = new ArrayList<>();
        for (int i =0; i < count; i++) {
            buttons.add(generateButton());
        }
        return buttons;
    }

    private Button generateButton() {
        Button button = new Button();
        button.setStyle("-fx-border-color:rgba(0%, 0%, 100%, 0); -fx-background-color: rgba(0%, 0%, 100%, 0)");
//        button.setText("@");
        button.setMinSize(50, 50);
        button.setMaxSize(50, 50);
        return button;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public Image getCircleImage() {
        return circleImage;
    }

    public Image getCrossImage() {
        return crossImage;
    }

    public List<Button> getButtons() {
        return buttons;
    }
}
