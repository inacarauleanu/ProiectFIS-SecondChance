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

public class ClientCumparaAlegeSH implements Initializable{

    @FXML
    private Button closeButton1;
    @FXML
    private TableView<SH> SHTable;
    @FXML
    private TableColumn<SH, SH> idCol;
    @FXML
    private TableColumn<SH, SH> usernameCol;
    @FXML
    private TableColumn<SH, SH> emailCol;
    @FXML
    private TextField usernameSH;
    @FXML
    private Button goBack;
    @FXML
    private Button gotosh;
    @FXML
    private Label warningLabel;


    String query1 = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;

    ObservableList<SH> SHList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
      loadDate();
    }

    private void loadDate() {
        connection = DatabaseConnection.getConnection();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_admin"));
        //lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        //firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));}

    private void refreshTable() {
        try {
            SHList.clear();

            query1 = "select * from admin_sh";
            preparedStatement = connection.prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                SHList.add(new SH(resultSet.getInt("id_admin"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("username"),
                        resultSet.getString("email")));
                SHTable.setItems(SHList);

            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }

    public void closeButton1OnAction(ActionEvent event ){
        Stage stage1 = (Stage) closeButton1.getScene().getWindow();
        stage1.close();
        Platform.exit();
    }

    public void goBackOnAction(ActionEvent event){
        Stage stage1 = (Stage) closeButton1.getScene().getWindow();
        stage1.close();
    }

    public void print(MouseEvent mouseEvent) {
    }

    public void gotoshOnAction(ActionEvent event) throws NoSuchAlgorithmException{
        if( usernameSH.getText().isBlank()==false ){
            buyfromSH();
        }
        else {
            warningLabel.setText("Please complete the field!");
        }
    }

    public void buyfromSH() throws NoSuchAlgorithmException{
        DatabaseConnection connection2 = new DatabaseConnection();
        Connection connectionDB = connection2.getConnection();

        String usernamesh = usernameSH.getText();
        String verifySH = "SELECT count(1) FROM admin_sh WHERE username = '" + usernamesh + "' ";


        int ok=0;

        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifySH);
            while(queryResult.next()){
                if(queryResult.getInt(1)==0)
                {warningLabel.setText("Please insert a valid username!");   }
                else ok=1;
            }
            if(ok==1)
            {
                }

        } catch (Exception e) { e.printStackTrace();   e.getCause();}}
}
