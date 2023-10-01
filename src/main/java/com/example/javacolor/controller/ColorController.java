package com.example.javacolor.controller;

import com.example.javacolor.model.ColorModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ColorController implements Initializable {

    @FXML
    private Rectangle colorRectangle;
    @FXML
    private HBox colorProposal;
    private ColorModel colorModel;

    @FXML
    private TextField textFieldRed;
    @FXML
    private TextField textFieldGreen;
    @FXML
    private TextField textFieldBlue;
    @FXML
    private TextField textFieldHex;
    @FXML
    private Label errorLabel;
    @FXML
    private Slider sliderRed;
    @FXML
    private Slider sliderGreen;
    @FXML
    private Slider sliderBlue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sliderRed.setMax(255);
        sliderGreen.setMax(255);
        sliderBlue.setMax(255);

        textFieldRed.setText("0");
        textFieldGreen.setText("0");
        textFieldBlue.setText("0");
        textFieldHex.setText("#000000");

        colorRectangle.setFill(Color.BLACK);

        colorModel = new ColorModel(1, 1, 1);

        textFieldRed.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSliderValue(textFieldRed, sliderRed);
            updateHexColor();
        });

        textFieldGreen.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSliderValue(textFieldGreen, sliderGreen);
            updateHexColor();
        });

        textFieldBlue.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSliderValue(textFieldBlue, sliderBlue);
            updateHexColor();
        });

        sliderRed.valueProperty().addListener((observable, oldValue, newValue) -> {
            textFieldRed.setText(String.valueOf(newValue.intValue()));
            updateHexColor();
        });

        sliderGreen.valueProperty().addListener((observable, oldValue, newValue) -> {
            textFieldGreen.setText(String.valueOf(newValue.intValue()));
            updateHexColor();
        });

        sliderBlue.valueProperty().addListener((observable, oldValue, newValue) -> {
            textFieldBlue.setText(String.valueOf(newValue.intValue()));
            updateHexColor();
        });

        textFieldHex.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                colorModel.setHexValue(newValue);
                textFieldRed.setText(String.valueOf(colorModel.getRed()));
                textFieldGreen.setText(String.valueOf(colorModel.getGreen()));
                textFieldBlue.setText(String.valueOf(colorModel.getBlue()));
                sliderRed.setValue(colorModel.getRed());
                sliderGreen.setValue(colorModel.getGreen());
                sliderBlue.setValue(colorModel.getBlue());
                errorLabel.setText("");
            } catch (IllegalArgumentException e){
                errorLabel.setText("Format hexadécimal invalide");
            }
        });


    }

    private void updateSliderValue(TextField textField, Slider slider) {
        try {
            int value = Integer.parseInt(textField.getText());
            slider.setValue(value);
            errorLabel.setText("");
        } catch (NumberFormatException e) {
            errorLabel.setText("Veuillez entrer un entier valide");
        }
    }

    private void updateHexColor() {
        int red, green, blue;
        try {
            red = Integer.parseInt(textFieldRed.getText());
            green = Integer.parseInt(textFieldGreen.getText());
            blue = Integer.parseInt(textFieldBlue.getText());
            colorModel.setRed(red);
            colorModel.setGreen(green);
            colorModel.setBlue(blue);
        } catch (NumberFormatException e) {
            errorLabel.setText("Veuillez entrer des valeurs RGB valides");
            return;
        }

        textFieldHex.setText(colorModel.getHexValue());

        sliderRed.setValue(red);
        sliderGreen.setValue(green);
        sliderBlue.setValue(blue);

        Color fxColor = Color.rgb(red, green, blue);
        colorRectangle.setFill(fxColor);
    }
}
