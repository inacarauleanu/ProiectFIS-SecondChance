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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demo.HelloController.username;


public class CosCumparaturiController implements Initializable{

    @FXML
    private Button closeButton1;
    @FXML
    private Button orderButton;
    @FXML
    private TableView<Produs> ProductTable;
    @FXML
    private TableColumn<Produs, Produs> idCol;
    @FXML
    private TableColumn<Produs, Produs> usernameCol;
    @FXML
    private TableColumn<Produs, Produs> priceCol;
    @FXML
    private TableColumn<Produs, Produs> idComandaCol;
    @FXML
    private TableColumn<Produs, Produs> colorCol;
    @FXML
    private TableColumn<Produs, Produs> sizeCol;
    @FXML
    private TextField adress;
    @FXML
    private TextField number;
    @FXML
    private Button goBack;
    @FXML
    private Button gotosh;
    @FXML
    private Label warningLabel;
    @FXML
            private Label totalLabel;
    @FXML
    private Label textLabel;

   String query1 = null;
   String query2 = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;
    static String usernamesh1 = "";
    private int total=0;

    ObservableList<Produs> PrList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        //cleanTable();
       loadDate();
    }

   private void loadDate() {
        connection = DatabaseConnection.getConnection();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("denumire"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
       // idComandaCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    private void refreshTable() {
        try {
            PrList.clear();
            query1 = "select * from cos_cumparaturi";
            preparedStatement = connection.prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                PrList.add(new Produs((usernamesh1),
                        resultSet.getInt("idprodus"),
                        resultSet.getString("numeprodus"),
                        resultSet.getString("culoareprodus"),
                        resultSet.getString("marimeprodus"),
                        resultSet.getInt("pretprodus")));
                ProductTable.setItems(PrList);

                total=total+resultSet.getInt("pretprodus");
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }

    public void closeButton1OnAction(ActionEvent event ){
        //cleanTable();
        Stage stage1 = (Stage) closeButton1.getScene().getWindow();
        stage1.close();
        Platform.exit();
    }

    public void goBackOnAction(ActionEvent event){
        Stage stage1 = (Stage) closeButton1.getScene().getWindow();
        stage1.close();
    }

    public void orderButtonOnAction(ActionEvent event){
        if(adress.getText().isBlank()==false && number.getText().isBlank()==false){
            warningLabel.setText("Thank you!" +
                    "The SH administrator will contact you!");
            textLabel.setText("Total price of the order is : ");
            totalLabel.setText(String.valueOf(total));
            //cleanTable();
            //refreshTable();
            cleanTable();
        }
        else {
            warningLabel.setText("Please complete all the empty fields!");
        }
    }

    public void cleanTable(){
        DatabaseConnection connection1 = new DatabaseConnection();
        Connection connectionDB = connection1.getConnection();
        String query2 = "DELETE FROM cos_cumparaturi";
        String searchUsername = "SELECT count(1) FROM cos_cumparaturi WHERE username='"+usernamesh1+"'";
;        try {
           // PrList.clear();
            Statement statement= connectionDB.createStatement();
            Statement statement1 = connectionDB.createStatement();
            /*ResultSet queryResult = statement.executeQuery(searchUsername);
                while(queryResult.next())
                {
                    if(queryResult.getInt(1)==1)
                    {*/
                            statement1.executeUpdate(query2);
                           // refreshTable();
                   // }
                    //}


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }


        }

    }

