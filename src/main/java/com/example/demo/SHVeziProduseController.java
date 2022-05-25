package com.example.demo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demo.HelloController.username;

public class SHVeziProduseController implements Initializable{
    @FXML
    private TableView<Produs> produseSHTable;
    @FXML
    private Button cancelButton;
    @FXML
    private TableColumn<Produs, Produs> idCol;
    @FXML
    private TableColumn<Produs, Produs> denumireCol;
    @FXML
    private TableColumn<Produs, Produs> colorCol;
    @FXML
    private TableColumn<Produs, Produs> sizeCol;
    @FXML
    private TableColumn<Produs, Produs> priceCol;

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
    private Label checkIDLabel;
    @FXML
    private Button goBack;




    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;

    ObservableList<Produs> produsList = FXCollections.observableArrayList();
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SHVeziProduseController.class.getResource("shVeziProduse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760, 590);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadDate();

    }

    @FXML
    private void refreshTable() {
        try {
            produsList.clear();

            query = "select * from produse_sh WHERE usernameSH = '" + username + "'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                produsList.add(new Produs(username, resultSet.getInt("id"),
                        resultSet.getString("Denumire"),
                        resultSet.getString("Marime"),
                        resultSet.getString("Culoare"),
                        resultSet.getInt("Pret")));
                produseSHTable.setItems(produsList);

            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }

    private void loadDate() {
        connection = DatabaseConnection.getConnection();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        denumireCol.setCellValueFactory(new PropertyValueFactory<>("denumire"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


    int check_data()
    {
        if(idTextField.getText().isEmpty() || denumireTextField.getText().isEmpty() || priceTextField.getText().isEmpty() ||colorTextField.getText().isEmpty()||sizeTextField.getText().isEmpty())
        {
            checkIDLabel.setText("Please fill in all fields!");
            return 0;
        }else return 1;
    }
    public void addProductSH() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectionDB = connection.getConnection();

        String denumire = denumireTextField.getText();
        String id = idTextField.getText();
        String color = colorTextField.getText();
        String size = sizeTextField.getText();
        String price = priceTextField.getText();
        String insertFields = "INSERT INTO produse_sh (usernameSH, ID, Denumire, Pret, Marime, Culoare) VALUES('";
        String insertValues = username+ "','" + id + "','" + denumire + "','" + price + "','" + size + "','" + color + "')";
        //String continuare = "WHERE usernameSH='" + username +"'";
        String insertToRegister = insertFields + insertValues; //+ continuare;
        String searchID = "SELECT count(1) FROM produse_sh WHERE ID='"+id+"'";
        try {
            Statement statement = connectionDB.createStatement();
            Statement statement1 = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(searchID);
            while(queryResult.next())
            {
                if(queryResult.getInt(1)==1)
                {
                    checkIDLabel.setText("This ID already exists in our system!");
                }else{
                    if(check_data()==1)
                    {statement1.executeUpdate(insertToRegister);
                    refreshTable();
                    checkIDLabel.setText("Operation succeeded!");}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void deleteProductSH()
    {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectionDB = connection.getConnection();
        String id = idTextField.getText();
        String deleteField = "DELETE FROM produse_sh WHERE ID='" + id + "'" + "AND usernameSH='" + username + "'";
        String searchID = "SELECT count(1) FROM produse_sh WHERE ID='"+id+"'" + "AND usernameSH='" + username +"'";

        try {
            Statement statement = connectionDB.createStatement();
            Statement statement1 = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(searchID);
            while(queryResult.next())
            {
                if(queryResult.getInt(1)==1)
                {
                    if(check_data()==1){
                    checkIDLabel.setText("Operation succeeded!");
                    statement1.executeUpdate(deleteField);
                    refreshTable();}
                }else{
                    checkIDLabel.setText("This ID does not exist!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }



    }

    public void print(MouseEvent mouseEvent) {
    }

    public void updateProductSH() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectionDB = connection.getConnection();
        String id = idTextField.getText();
        String denumire = denumireTextField.getText();
        String color = colorTextField.getText();
        String size = sizeTextField.getText();
        String price = priceTextField.getText();
        String updateField = "UPDATE produse_sh SET Denumire='" + denumire + "'" + ","
                + "Pret='"+price+"'" + ","
                +"Marime='"+size+"'"+","
                +"Culoare='"+color+"'"
                +"WHERE ID='" + id + "'" + "AND usernameSH='"+username+"'";
        String searchID = "SELECT count(1) FROM produse_sh WHERE ID='"+id+"'" + "AND usernameSH='"+username+"'";
        try {
            Statement statement = connectionDB.createStatement();
            Statement statement1 = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(searchID);
            while(queryResult.next())
            {
                if(queryResult.getInt(1)==1)
                {
                    //
                    if(check_data()==1){
                    checkIDLabel.setText("Operation succeeded!");
                    statement1.executeUpdate(updateField);
                    refreshTable();}
                }
                else{
                    checkIDLabel.setText("This ID does not exist!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void goBackOnAction(ActionEvent event){
        Stage stage1 = (Stage) cancelButton.getScene().getWindow();
        stage1.close();
    }
}
