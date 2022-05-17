package com.example.demo;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

public class SHVeziProduseController extends Application implements Initializable{
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
    private TableColumn<Produs, String> editCol;
    @FXML
    private ImageView addImage;
    @FXML
    private ImageView refreshImage;
    @FXML
    private ImageView printImage;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produs produs = null;

    ObservableList<Produs> produsList = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SHVeziProduseController.class.getResource("shVeziProduse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadDate();

        File brandingFile = new File("images/add.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        addImage.setImage(brandingImage);

        File brandingFile1 = new File("images/print.png");
        Image brandingImage1 = new Image(brandingFile1.toURI().toString());
        printImage.setImage(brandingImage1);

        File brandingFile2 = new File("images/refresh.png");
        Image brandingImage2 = new Image(brandingFile2.toURI().toString());
        refreshImage.setImage(brandingImage2);




    }

    @FXML
    private void refreshTable() {
        try {
            produsList.clear();

            query = "select * from produse_sh";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                produsList.add(new Produs(resultSet.getInt("id"),
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
/*
        Callback<TableColumn<Produs, String>, TableCell<Produs, String>> cellFoctory = (TableColumn<Produs, String> param) -> {
            // make cell containing buttons
            final TableCell<Produs, String> cell = new TableCell<Produs, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );


                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                produs = produseSHTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM produse_sh WHERE id  =" + produs.getId();
                                connection = DatabaseConnection.getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(SHVeziProduseController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            produs = produseSHTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("addProdusSH.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(SHVeziProduseController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            addProdusSHController addProdusController = loader.getController();
                            addProdusController.setUpdate(true);
                            addProdusController.setTextField(produs.getId(), produs.getDenumire(),
                                    produs.getColor(), produs.getSize(), produs.getPrice());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();


                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
       produseSHTable.setItems(produsList);

*/
    }

    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


    public void getAddView(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SHVeziProduseController.class.getResource("addProdusSH.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 311, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void print(MouseEvent mouseEvent) {
    }
}
