package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private ChoiceBox<String> myChoiceBox1;
    @FXML
    private Label myLabel;
    private String[] role1 = {"CLIENT", "ONG", "SH"};
    /*<<<<<<< HEAD

    =======
    >>>>>>> origin/main*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/poza.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
        myChoiceBox1.getItems().addAll(role1);
        myChoiceBox1.setOnAction(this::getRole);

    }
/*<<<<<<< HEAD

    @FXML
    private void initialize() {
        myChoiceBox1.getItems().addAll(role1);
        myChoiceBox1.setOnAction(this::getRole);
}
=======*/

    public void getRole(ActionEvent event) {
        String myRole = myChoiceBox1.getSelectionModel().getSelectedItem();
        //myLabel.setText(myRole);
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
            if (myChoiceBox1.getSelectionModel().getSelectedItem()=="CLIENT") validateLoginUSER();
            if (myChoiceBox1.getSelectionModel().getSelectedItem()=="SH") {
                validateLoginSH();
            }
            if (myChoiceBox1.getSelectionModel().getSelectedItem()=="ONG") {
                validateLoginONG();
            }
        }else{
            loginMessageLabel.setText("Please enter username and password!");
        }
    }
    public void CancelButtonOnAction(ActionEvent event)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
//>>>>>>> origin/main
    }
    /*private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();

<<<<<<< HEAD
    public void getRole(ActionEvent event) {
        String myRole = myChoiceBox1.getSelectionModel().getSelectedItem();
        myLabel.setText(myRole);
    }

    public void signupButtonOnAction(ActionEvent event) {

        //if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
        //loginMessageLabel.setText("You try to login");
        // validateSignUp();
        //}else{
        // loginMessageLabel.setText("Please enter username and password!");
        createAccountForm();
        //}
    }

    public void loginButtonOnAction(ActionEvent event) throws NoSuchAlgorithmException, NoSuchProviderException {

        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            loginMessageLabel.setText("You try to login");
            if (myChoiceBox1.getSelectionModel().getSelectedItem() == "CLIENT") validateLoginUSER();
            if (myChoiceBox1.getSelectionModel().getSelectedItem() == "SH") {
                validateLoginSH();
=======
    }*/

    /*public void validateLoginUSER() throws NoSuchAlgorithmException, NoSuchProviderException
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

    // String verifyLogin = "SELECT count(1) FROM account_user WHERE username = '" + usernameTextField.getText() + "'" + "AND password ='" +enterPasswordField.getText() + "'";
    // String verifyPassword = "SELECT password from account_user where username = '"+usernameTextField.getText()+"'";

    //String decryptedString = AES.decrypt(verifyPassword);
    //System.out.println(decryptedString);
    //String verifyRole = "SELECT email from account_user where username = '" + usernameTextField.getText() + "'";
    // String verifyLogin = "SELECT count(1) FROM account_user WHERE (username = '" + usernameTextField.getText() + "'" + "AND STRCMP('" +enterPasswordField.getText() + "'" +  "," + "'" + decryptedString + "'" + ")" +"=0" +")" ;
       /* try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                //System.out.println(verifyLogin);
                if (queryResult.getInt(1)==1) {
                    if (myChoiceBox1.getSelectionModel().getSelectedItem()=="CLIENT")
                    {createClientForm();
                    loginMessageLabel.setText("Congrats!");}
                    //  createAccountForm();
                }else{
                    loginMessageLabel.setText("This account does not not exist! Please sign up if you want to create one!");
                    //System.out.println(verifyLogin);
                    //createAccountForm();

                }}}catch(Exception e) {e.printStackTrace();  e.getCause();}
/*>>>>>>> origin/main
            }
            if (myChoiceBox1.getSelectionModel().getSelectedItem() == "ONG") {
                validateLoginONG();
            }
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }
<<<<<<< HEAD*/
    //  }

    /*public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }*/
    /*private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }*/

    public void validateLoginUSER() throws NoSuchAlgorithmException, NoSuchProviderException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String password = enterPasswordField.getText();
        String salt = usernameTextField.getText();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hasedPassword = sb.toString();
        //final String secretKey = "ssshhhhhhhhhhh!!!!";
        //String encryptedString = AES.encrypt(password, secretKey);
        //String decryptedString = AES.decrypt(encryptedString, secretKey);

        /*String encodePass;
        String salt = usernameTextField.getText();
        MessageDigest md= MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hasedPass1 = md.digest(password.getBytes(StandardCharsets.UTF_8));
        encodePass=new String(hasedPass1, StandardCharsets.UTF_8).
                replace("\"","");*/

        String verifyLogin = "SELECT count(1) FROM account_user WHERE username = '" + usernameTextField.getText() + "'" + "AND password ='" + hasedPassword + "'";
        String verifyPassword = "SELECT password from account_user where username = '" + usernameTextField.getText() + "'";

        //String decryptedString = AES.decrypt(verifyPassword);
        //System.out.println(decryptedString);
        //String verifyRole = "SELECT email from account_user where username = '" + usernameTextField.getText() + "'";
        // String verifyLogin = "SELECT count(1) FROM account_user WHERE (username = '" + usernameTextField.getText() + "'" + "AND STRCMP('" +enterPasswordField.getText() + "'" +  "," + "'" + decryptedString + "'" + ")" +"=0" +")" ;
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                //System.out.println(verifyLogin);
                if (queryResult.getInt(1) == 1) {
                    if (myChoiceBox1.getSelectionModel().getSelectedItem() == "CLIENT") {
                        createClientForm();
                        loginMessageLabel.setText("Congrats!");
                    }
                    //  createAccountForm();
                } else {
                    loginMessageLabel.setText("This account does not not exist! Please sign up if you want to create one!");
                    //System.out.println(verifyLogin);
                    //createAccountForm();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public void validateLoginONG() throws NoSuchAlgorithmException, NoSuchProviderException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String password = enterPasswordField.getText();

        String salt = usernameTextField.getText();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hasedPassword = sb.toString();

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

        String verifyLogin = "SELECT count(1) FROM admin_ong WHERE username = '" + usernameTextField.getText() + "'" + "AND parola ='" + hasedPassword + "'";
        String verifyPassword = "SELECT password from account_user where username = '" + usernameTextField.getText() + "'";

        //String decryptedString = AES.decrypt(verifyPassword);
        //System.out.println(decryptedString);
        //String verifyRole = "SELECT email from account_user where username = '" + usernameTextField.getText() + "'";
        // String verifyLogin = "SELECT count(1) FROM account_user WHERE (username = '" + usernameTextField.getText() + "'" + "AND STRCMP('" +enterPasswordField.getText() + "'" +  "," + "'" + decryptedString + "'" + ")" +"=0" +")" ;
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                //System.out.println(verifyLogin);
                if (queryResult.getInt(1) == 1) {
                    if (myChoiceBox1.getSelectionModel().getSelectedItem() == "ONG") {
                        createONGForm();
                        loginMessageLabel.setText("Congrats!");
                    }
                    //  createAccountForm();
                } else {
                    loginMessageLabel.setText("This account does not not exist! Please sign up if you want to create one!");
                    //System.out.println(verifyLogin);
                    //createAccountForm();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    private void createONGForm() {
        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("ongFirstPage.fxml"));
            Stage clientStage = new Stage();
            clientStage.initStyle(StageStyle.UNDECORATED);
            clientStage.setScene(new Scene(root1, 598, 503));
            clientStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void validateLoginSH() throws NoSuchAlgorithmException, NoSuchProviderException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String password = enterPasswordField.getText();

        String salt = usernameTextField.getText();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hasedPassword = sb.toString();
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

        String verifyLogin = "SELECT count(1) FROM admin_sh WHERE username = '" + usernameTextField.getText() + "'" + "AND parola ='" + hasedPassword + "'";
        String verifyPassword = "SELECT password from account_user where username = '" + usernameTextField.getText() + "'";

        //String decryptedString = AES.decrypt(verifyPassword);
        //System.out.println(decryptedString);
        //String verifyRole = "SELECT email from account_user where username = '" + usernameTextField.getText() + "'";
        // String verifyLogin = "SELECT count(1) FROM account_user WHERE (username = '" + usernameTextField.getText() + "'" + "AND STRCMP('" +enterPasswordField.getText() + "'" +  "," + "'" + decryptedString + "'" + ")" +"=0" +")" ;
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                //System.out.println(verifyLogin);
                if (queryResult.getInt(1) == 1) {
                    if (myChoiceBox1.getSelectionModel().getSelectedItem() == "SH") {
                        createSHForm();
                        loginMessageLabel.setText("Congrats!");
                    }
                    //  createAccountForm();
                } else {
                    loginMessageLabel.setText("This account does not not exist! Please sign up if you want to create one!");
                    //System.out.println(verifyLogin);
                    //createAccountForm();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void createAccountForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 524, 564);
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createClientForm() {
        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("client1.fxml"));
            Stage clientStage = new Stage();
            clientStage.initStyle(StageStyle.UNDECORATED);
            clientStage.setScene(new Scene(root1, 520, 400));
            clientStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createSHForm() {
        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("shfirstpage.fxml"));
            Stage clientStage = new Stage();
            clientStage.initStyle(StageStyle.UNDECORATED);
            clientStage.setScene(new Scene(root1, 598, 503));
            clientStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}

/*=======
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }*/

  /*  public void validateLoginONG() throws NoSuchAlgorithmException, NoSuchProviderException
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

//  String verifyLogin = "SELECT count(1) FROM admin_ong WHERE username = '" + usernameTextField.getText() + "'" + "AND password ='" +enterPasswordField.getText() + "'";
// String verifyPassword = "SELECT password from account_user where username = '"+usernameTextField.getText()+"'";

//String decryptedString = AES.decrypt(verifyPassword);
//System.out.println(decryptedString);
//String verifyRole = "SELECT email from account_user where username = '" + usernameTextField.getText() + "'";
// String verifyLogin = "SELECT count(1) FROM account_user WHERE (username = '" + usernameTextField.getText() + "'" + "AND STRCMP('" +enterPasswordField.getText() + "'" +  "," + "'" + decryptedString + "'" + ")" +"=0" +")" ;
       /* try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                //System.out.println(verifyLogin);
                if (queryResult.getInt(1)==1) {
                    if (myChoiceBox1.getSelectionModel().getSelectedItem()=="ONG")
                    {
                        //createClientForm();
                        loginMessageLabel.setText("Congrats!");}
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


    public void validateLoginSH() throws NoSuchAlgorithmException, NoSuchProviderException
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

//  String verifyLogin = "SELECT count(1) FROM admin_sh WHERE username = '" + usernameTextField.getText() + "'" + "AND parola ='" +enterPasswordField.getText() + "'";
// String verifyPassword = "SELECT password from account_user where username = '"+usernameTextField.getText()+"'";

//String decryptedString = AES.decrypt(verifyPassword);
//System.out.println(decryptedString);
//String verifyRole = "SELECT email from account_user where username = '" + usernameTextField.getText() + "'";
// String verifyLogin = "SELECT count(1) FROM account_user WHERE (username = '" + usernameTextField.getText() + "'" + "AND STRCMP('" +enterPasswordField.getText() + "'" +  "," + "'" + decryptedString + "'" + ")" +"=0" +")" ;
       /* try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                //System.out.println(verifyLogin);
                if (queryResult.getInt(1)==1) {
                    if (myChoiceBox1.getSelectionModel().getSelectedItem()=="SH")
                    {createSHForm();
                        loginMessageLabel.setText("Congrats!");}
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
    public  void createClientForm(){
        try{

            Parent root1 = FXMLLoader.load(getClass().getResource("client1.fxml"));
            Stage clientStage = new Stage();
            clientStage.initStyle(StageStyle.UNDECORATED);
            clientStage.setScene(new Scene(root1,520,400));
            clientStage.show();

        }  catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public  void createSHForm(){
        try{

            Parent root1 = FXMLLoader.load(getClass().getResource("shfirstpage.fxml"));
            Stage clientStage = new Stage();
            clientStage.initStyle(StageStyle.UNDECORATED);
            clientStage.setScene(new Scene(root1,598,503));
            clientStage.show();

        }  catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}


>>>>>>> origin/main  */

