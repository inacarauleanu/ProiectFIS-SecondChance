package com.example.demo;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.ResourceBundle;
import java.net.URL;

public class addProdusSHController implements Initializable {
    @FXML
    private TextField denumireTextField;
    @FXML
    private TextField sizeTextField;
    @FXML
    private TextField colorTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cleanButton;
     @FXML
     private Button cancelButton;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;
    int produsID;
    private boolean update;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        /*loadDate();

        File brandingFile = new File("images/add.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        addImage.setImage(brandingImage);

        File brandingFile1 = new File("images/print.png");
        Image brandingImage1 = new Image(brandingFile1.toURI().toString());
        printImage.setImage(brandingImage1);

        File brandingFile2 = new File("images/refresh.png");
        Image brandingImage2 = new Image(brandingFile2.toURI().toString());
        refreshImage.setImage(brandingImage2);*/


    }


    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


    public void save(MouseEvent mouseEvent) {
        connection = DatabaseConnection.getConnection();
        String id = idTextField.getText();
        String denumire = denumireTextField.getText();
        String color = colorTextField.getText();
        String size = sizeTextField.getText();
        String pret = priceTextField.getText();

        if(id.isBlank()||denumire.isBlank() || color.isBlank() || size.isBlank() || pret.isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill all data");
            alert.showAndWait();
        }else{
           // getQuery();
            insert();
            clean();

        }


    }

    @FXML
    private void clean() {
        idTextField.setText(null);
        denumireTextField.setText(null);
        colorTextField.setText(null);
        sizeTextField.setText(null);
        priceTextField.setText(null);

    }

    public void insert()
    {
        try{

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idTextField.getText());
            preparedStatement.setString(2, denumireTextField.getText());
            preparedStatement.setString(3, priceTextField.getText());
            preparedStatement.setString(4, sizeTextField.getText());
            preparedStatement.setString(5, colorTextField.getText());


        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

   /* private void getQuery() {
        if(update==false) {
            query = "INSERT INTO produse_sh (ID, Denumire, Pret, Marime, Culoare) VALUES(?, ?, ?, ?, ?)";
        }else
        {
            query = "UPDATE produse_sh SET \"\n" +
                    "                    + \"`Denumire`=?,\"\n" +
                    "                    + \"`Pret=?,\"\n" +
                    "                    + \"`Marime`=?,\"\n" +
                    "                    + \"`Culoare`= ? WHERE id = '\"+produsId+\"'";
        }
    }*/
    public void setTextField(int id, String denumire, String color, String size, String price) {
        produsID = id;
        denumireTextField.setText(denumire);
        colorTextField.setText(color);
        sizeTextField.setText(size);
        priceTextField.setText(price);
    }


    public void setUpdate(boolean b) {
        this.update = b;
    }

}
