package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.stage.StageStyle;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientController implements Initializable {

    @FXML
    private ImageView iconImageView;
    @FXML
    private Button  closeButton1;
    @FXML
    private Button donateButton1;
    @FXML
    private Button buyButton1;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File iconFile = new File("images/welcome.png");
        Image iconImage = new Image(iconFile.toURI().toString());
        iconImageView.setImage(iconImage);
    }

    public void closeButton1OnAction(ActionEvent event){
    Stage stage = (Stage) closeButton1.getScene().getWindow();
    stage.close();
    Platform.exit();}

    public  void createDonatieForm(){
        try{

            Parent root2 = FXMLLoader.load(getClass().getResource("clientDonatie.fxml"));
            Stage clientStage1 = new Stage();
            clientStage1.initStyle(StageStyle.UNDECORATED);
            clientStage1.setScene(new Scene(root2,600,400));
            clientStage1.show();

        }  catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void donateButton1OnAction(ActionEvent event){
        createDonatieForm();
    }


    public void buyButton1OnAction(ActionEvent event){}

}

