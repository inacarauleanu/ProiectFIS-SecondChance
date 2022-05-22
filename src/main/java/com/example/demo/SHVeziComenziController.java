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

import static com.example.demo.ClientCumparaAlegeSH.usernamesh1;
import static com.example.demo.HelloController.username;


public class SHVeziComenziController implements Initializable {
    @FXML
    private Button closeButton1;
    @FXML
    private TableView<Produs> OrderTable;
    @FXML
    private TableColumn<Produs, Produs> idCol;
    @FXML
    private TableColumn<Produs, Produs> usernameCol;
    @FXML
    private TableColumn<Produs, Produs> colorCol;
    @FXML
    private TableColumn<Produs, Produs> priceCol;
    @FXML
    private TableColumn<Produs, Produs> sizeCol;
    @FXML
    private TextField productID;
    @FXML
    private Button goback;


    String query1 = null;
    Connection connection1 = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;


    ObservableList<Produs> prList1 = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadDate();
    }

    private void loadDate() {
        connection1 = DatabaseConnection.getConnection();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("denumire"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));}

    private void refreshTable() {
        try {
            prList1.clear();
            query1 = "select * from comenzi_sh where username = '" + username + "'";
            preparedStatement = connection1.prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                prList1.add(new Produs(resultSet.getString("username"),
                        resultSet.getInt("idprodus"),
                        resultSet.getString("numeprodus"),
                        resultSet.getString("marimeprodus"),
                        resultSet.getString("culoareprodus"),
                        resultSet.getInt("pretprodus")));
                OrderTable.setItems(prList1);

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

}
