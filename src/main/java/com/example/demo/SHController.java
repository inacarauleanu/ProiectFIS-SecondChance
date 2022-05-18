package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SHController implements Initializable {
@FXML
private ImageView iconImageView;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView iconProductsImage;
    @FXML
    private ImageView iconOrdersImage;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File iconFile = new File("images/welcome.png");
        Image iconImage = new Image(iconFile.toURI().toString());
        iconImageView.setImage(iconImage);

        File iconFile1 = new File("images/img_569713.png");
        Image iconImage1 = new Image(iconFile1.toURI().toString());
        iconProductsImage.setImage(iconImage1);

        File iconFile2 = new File("images/img_355401.png");
        Image iconImage2 = new Image(iconFile2.toURI().toString());
        iconOrdersImage.setImage(iconImage2);

    }
    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


}
