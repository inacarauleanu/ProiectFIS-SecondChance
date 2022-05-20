package com.example.demo;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDonatieONGController implements Initializable
{
    @FXML
    private Button closeButton2;
    @FXML
    private TableView<ONG> ONGTable;
    @FXML
    private TableColumn<ONG, ONG> idCol;
    @FXML
    private TableColumn<ONG, ONG> usernameCol;
    @FXML
    private TableColumn<ONG, ONG> emailCol;
    @FXML
    private TextField usernameONG;
    @FXML
    private Button goBack;
    @FXML
    private TextField nameP;
    @FXML
    private TextField colorP;
    @FXML
    private TextField sizeP;
    @FXML
    private DatePicker dateP;
    @FXML
    private Button donateButton;
    @FXML
    private Label warningLabel;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;

    ObservableList<ONG> ONGList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadDate();
    }

    private void loadDate() {
        connection = DatabaseConnection.getConnection();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_ong"));
        //lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        //firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));}

    private void refreshTable() {
        try {
            ONGList.clear();

            query = "select * from admin_ong";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                ONGList.add(new ONG(resultSet.getInt("id_ong"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("username"),
                        resultSet.getString("email")));
                ONGTable.setItems(ONGList);

            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }

    public void closeButton2OnAction(ActionEvent event ){
        Stage stage1 = (Stage) closeButton2.getScene().getWindow();
        stage1.close();
        Platform.exit();
    }

    public void goBackOnAction(ActionEvent event){
        Stage stage1 = (Stage) closeButton2.getScene().getWindow();
        stage1.close();
    }

    public void print(MouseEvent mouseEvent) {
    }

    public void donateButtonOnAction(ActionEvent event) throws NoSuchAlgorithmException{
        if(nameP.getText().isBlank()==false && usernameONG.getText().isBlank()==false && colorP.getText().isBlank()==false && sizeP.getText().isBlank()==false && dateP.getEditor().getText().isBlank()==false){
            donateToONG();
        }
        else {
            warningLabel.setText("Please complete all the empty fields!");
        }
    }

    public void donateToONG() throws NoSuchAlgorithmException {
        DatabaseConnection connection3 = new DatabaseConnection();
        Connection connectionDB = connection3.getConnection();

        String usernameong = usernameONG.getText();
        String nameproduct= nameP.getText();
        String colorproduct = colorP.getText();
        String sizeproduct = sizeP.getText();
        String datedonation = dateP.getEditor().getText();

        String insertFieldsDonatieONG = "INSERT INTO donatii_ong (usernameONG, productName, productColor, productSize, date) VALUES ('";
        String insertvaluesD = usernameong + "','" + nameproduct + "','" + colorproduct + "','" + sizeproduct + "','" + datedonation + "')";
        String verifyONG = "SELECT count(1) FROM admin_ong WHERE username = '" + usernameong + "' ";
        String insertintable = insertFieldsDonatieONG + insertvaluesD;


        int ok=0;

        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyONG);
            while(queryResult.next()){
                if(queryResult.getInt(1)==0)
                {warningLabel.setText("Please insert a valid username!");   }
                else ok=1;
            }
            if(ok==1)
            { statement.executeUpdate(insertintable);
                warningLabel.setText("Thank you for you donation! :)");}

        } catch (Exception e) { e.printStackTrace();   e.getCause();}}
}


