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

public class SHVeziDonatiiController implements Initializable{
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<DonatieSH> donatiiSHTable;
    @FXML
    private TableColumn<DonatieSH, DonatieSH> id_donatieCol;
    @FXML
    private TableColumn<DonatieSH, DonatieSH> productNameCol;
    @FXML
    private TableColumn<DonatieSH, DonatieSH> productSizeCol;
    @FXML
    private TableColumn<DonatieSH, DonatieSH> productColorCol;
    @FXML
    private TableColumn<DonatieSH, DonatieSH> dateCol;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;

    ObservableList<DonatieSH> DonatiiSHList = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadDate();
    }

    private void loadDate() {
        connection = DatabaseConnection.getConnection();
        refreshTable();
        id_donatieCol.setCellValueFactory(new PropertyValueFactory<>("id_donatie"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productColorCol.setCellValueFactory(new PropertyValueFactory<>("productColor"));
        productSizeCol.setCellValueFactory(new PropertyValueFactory<>("productSize"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    private void refreshTable() {
        try {
            DonatiiSHList.clear();
            //CLIENT client = new();
            query = "select * from donatii_sh WHERE usernameSH = '" + username  + "'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                DonatiiSHList.add(new DonatieSH(resultSet.getInt("id_donatie"),
                        resultSet.getString("productName"),
                        resultSet.getString("productSize"),
                        resultSet.getString("productColor"),
                        resultSet.getString("date")));
                donatiiSHTable.setItems(DonatiiSHList);

            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }

    public void CancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}
