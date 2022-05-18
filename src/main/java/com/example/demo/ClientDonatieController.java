package com.example.demo;


import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class ClientDonatieController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button backButton;
    @FXML
    private Button shButton;
    @FXML
    private Button ongButton;
  /*  @FXML
    private TableView<SH> shTable;
    @FXML
    private TableColumn<SH, String> idSH;
    @FXML
    private TableColumn<SH, String>  firstnameSH;
    @FXML
    private TableColumn<SH, String> lastnameSH;
    @FXML
    private TableColumn<SH, String> emailSH;
    @FXML
    private TableColumn<SH, String> roleSH;

    String shViewQuery = null;*/
    Connection connection = null;
    PreparedStatement preparedStatement =null;
    ResultSet resultSet = null;
    //SH sh = null;

   // ObservableList<SH> SHList= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //File brandingFile = new File("images/poza.jpg");
        //Image brandingImage = new Image(brandingFile.toURI().toString());
        //brandingImageView.setImage(brandingImage);
        //myChoiceBox1.getItems().addAll(role1);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String shViewQuery = "SELECT idsh_user, firstname, lastname, email, role FROM sh_user ";

    }

    /*private void loadDate() {
        String shViewQuery = "SELECT idsh_user, firstname, lastname, email, role FROM sh_user ";

        try {
            PreparedStatement statement = connection.prepareStatement(shViewQuery);
            ResultSet queryOutput = statement.executeQuery(shViewQuery);

            while (queryOutput.next()) {
                Integer querySHID = queryOutput.getInt("idsh_user");
                String queryFirstname = queryOutput.getString("firstname");
                String queryLastname = queryOutput.getString("lastname");
                String queryEmail = queryOutput.getString("email");
                String queryRole = queryOutput.getString("role");


                SHList.add(new SH(querySHID, queryFirstname, queryLastname, queryEmail, queryRole));
            }

            idSH.setCellFactory(new PropertyValueFactory("idsh_user"));
            lastnameSH.setCellFactory(new PropertyValueFactory("lastname"));
            firstnameSH.setCellFactory(new PropertyValueFactory("firstname"));
            emailSH.setCellFactory(new PropertyValueFactory("email"));
            roleSH.setCellFactory(new PropertyValueFactory("role"));

            shTable.setItems(SHList);


        } catch (SQLException e) {
            Logger.getLogger(ClientDonatieController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();

        }
    }
*/

    public void closeButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) closeButton.getScene().getWindow();
        stage1.close();
        Platform.exit();
    }

    public void backButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
    }

    public void  shButtonOnAction(ActionEvent event){
        //  loadDate();
        createDonatieForm();

    }

    public void ongButtonOnAction(ActionEvent event){


    }

    public  void createDonatieForm(){
        try{

            Parent root3 = FXMLLoader.load(getClass().getResource("DonatieSH.fxml"));
            Stage clientStage1 = new Stage();
            clientStage1.initStyle(StageStyle.UNDECORATED);
            clientStage1.setScene(new Scene(root3,700,400));
            clientStage1.show();

        }  catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
