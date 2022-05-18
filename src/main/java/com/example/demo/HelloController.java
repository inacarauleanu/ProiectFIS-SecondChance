package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
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
   public void signupButtonOnAction(ActionEvent event)
   {

       //if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
           //loginMessageLabel.setText("You try to login");
          // validateSignUp();
       //}else{
          // loginMessageLabel.setText("Please enter username and password!");
           createAccountForm();
       //}
   }
    public void loginButtonOnAction(ActionEvent event) throws NoSuchAlgorithmException, NoSuchProviderException
    {

        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
            loginMessageLabel.setText("You try to login");
            validateLogin();
        }else{
            loginMessageLabel.setText("Please enter username and password!");
        }
    }
   public void CancelButtonOnAction(ActionEvent event)
   {
       Stage stage = (Stage) cancelButton.getScene().getWindow();
       stage.close();
    }
    /*private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();

    }*/

    public void validateLogin() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String password = enterPasswordField.getText();
        /*String salt = getSalt();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hasedPassword = sb.toString();*/
        //final String secretKey = "ssshhhhhhhhhhh!!!!";
        //String encryptedString = AES.encrypt(password, secretKey);
        //String decryptedString = AES.decrypt(encryptedString, secretKey);

        String verifyLogin = "SELECT count(1) FROM account_user WHERE username = '" + usernameTextField.getText() + "'" + "AND password ='" +enterPasswordField.getText() + "'";
        String verifyPassword = "SELECT password from account_user where username = '"+usernameTextField.getText()+"'";

        String decryptedString = AES.decrypt(verifyPassword);
        //System.out.println(decryptedString);
        //String verifyRole = "SELECT email from account_user where username = '" + usernameTextField.getText() + "'";
       // String verifyLogin = "SELECT count(1) FROM account_user WHERE (username = '" + usernameTextField.getText() + "'" + "AND STRCMP('" +enterPasswordField.getText() + "'" +  "," + "'" + decryptedString + "'" + ")" +"=0" +")" ;
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                //System.out.println(verifyLogin);
                if (queryResult.getInt(1)==1) {

                    loginMessageLabel.setText("Congrats!");
                    //  createAccountForm();
                }else{
                    loginMessageLabel.setText("This account does not not exist! Please sign up if you want to create one!");
                    //System.out.println(verifyLogin);
                    //createAccountForm();

                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

    public void createAccountForm()
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 524, 564);
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}