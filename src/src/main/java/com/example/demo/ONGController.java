package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ONGController implements Initializable{
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView iconDonations;
    @FXML
    private ImageView iconImageView;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File iconFile = new File("images/donate.png");
        Image iconImage = new Image(iconFile.toURI().toString());
        iconDonations.setImage(iconImage);

        File iconFile1 = new File("images/welcome.png");
        Image iconImage1 = new Image(iconFile1.toURI().toString());
        iconImageView.setImage(iconImage1);
    }

    public void CancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void veziDonatiiONG(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SHVeziProduseController.class.getResource("ongVeziDonatii.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage clientStage = new Stage();
            clientStage.initStyle(StageStyle.UNDECORATED);
            clientStage.setScene(scene);
            clientStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
