package com.example.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.demo.ClientCumparaAlegeSH.usernamesh1;

public class ClientCumparaAlegeProduse implements Initializable {

    @FXML
    private Button closeButton1;
    @FXML
    private TableView<Produs> ProductTable;
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
    @FXML
    private Button gotocartButton;
    @FXML
    private Button addButton;
    @FXML
    private Label warningLabel;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;


    ObservableList<Produs> prList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadDate();
    }

    private void loadDate() {
        connection = DatabaseConnection.getConnection();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("denumire"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));}

    private void refreshTable() {
        try {
            prList.clear();
            query = "select * from produse_sh WHERE usernameSH = '" + usernamesh1 + "'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                prList.add(new Produs(resultSet.getString("usernameSH"),
                        resultSet.getInt("ID"),
                        resultSet.getString("Denumire"),
                        resultSet.getString("Marime"),
                        resultSet.getString("Culoare"),
                        resultSet.getInt("Pret")));
                ProductTable.setItems(prList);

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

    public void addButtonOnAction(ActionEvent event) throws NoSuchAlgorithmException {
        if(productID.getText().isBlank()==false  ){
            addComand();
        }
        else {
            warningLabel.setText("Please complete the empty field!");
        }
    }

    public void addComand() throws NoSuchAlgorithmException {
        DatabaseConnection connection3 = new DatabaseConnection();
        Connection connectionDB = connection3.getConnection();

        String IDprodus = productID.getText();

        String random = "abc";
        String random1 = "abc";
        String insertFieldsComanda = "INSERT INTO cos_cumparaturi (idprodus, numeprodus,culoareprodus,marimeprodus,pretprodus,username ) SELECT ID, Denumire,Culoare,Marime,Pret,usernameSH FROM produse_sh WHERE ID = ' "+ IDprodus + "'";
       //String insertvaluesD = IDprodus + "','" + produs.getDenumire() + "','" + produs.getColor() + "','" + produs.getSize() +"','"+ produs.getPrice() + "')";
        String verifyproduct = "SELECT count(1) FROM produse_sh WHERE ID = '" + IDprodus + "' ";
        String insertintable = insertFieldsComanda ;
        String insert =  insertFieldsComanda ;
        String insertFieldsComanda1 = "INSERT INTO comenzi_sh (idprodus, numeprodus,culoareprodus,marimeprodus,pretprodus,username ) SELECT ID, Denumire,Culoare,Marime,Pret,usernameSH FROM produse_sh WHERE ID = ' "+ IDprodus + "'";

        int ok=0;

        try {
            Statement statement = connectionDB.createStatement();
            Statement statement1= connectionDB.createStatement();

            ResultSet queryResult = statement.executeQuery(verifyproduct);
            while(queryResult.next()){
                if(queryResult.getInt(1)==0)
                {warningLabel.setText("Please insert a valid id!");   }
                else ok=1;
            }
            if(ok==1)
            {
                statement.executeUpdate(insert);
                statement1.executeUpdate(insertFieldsComanda1);
                warningLabel.setText("Thank you ! :)");}

        } catch (Exception e) { e.printStackTrace();   e.getCause();}}

    public void gotocartButton(ActionEvent event) {

        try{

            Parent root4 = FXMLLoader.load(getClass().getResource("CosCumparaturi.fxml"));
            Stage clientStage2 = new Stage();
            clientStage2.initStyle(StageStyle.UNDECORATED);
            clientStage2.setScene(new Scene(root4,723,400));
            clientStage2.show();

        }  catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
