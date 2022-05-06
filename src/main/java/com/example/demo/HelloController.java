package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;
public class HelloController implements Initializable {
   /* @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");*/
   @FXML
   private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        File brandingFile = new File("images/poza.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

    }
   public void loginButtonOnAction(ActionEvent event)
   {

       if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
           loginMessageLabel.setText("You try to login");
           //validateLogin();
       }else{
           loginMessageLabel.setText("Please enter username and password");
       }
   }
   public void CancelButtonOnAction(ActionEvent event)
   {
       Stage stage = (Stage) cancelButton.getScene().getWindow();
       stage.close();
    }

   /* public void validateLogin()
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT * FROM user_account WHERE username = '" + usernameTextField.getText() + "AND password='" + enterPasswordField.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    loginMessageLabel.setText("Congrats!");
                }else{
                    loginMessageLabel.setText("Invalid login. Try again!");
                }
            }
        }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }

    }*/

}