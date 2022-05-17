package com.example.demo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView iconImageView;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label checkUsername;
    @FXML
    private Label checkEmail;
    @FXML
    private Label myLabel;
    @FXML
    private ChoiceBox<String> myChoiceBox;

    private String[] role = {"CLIENT", "ONG", "SH"};


    public void initialize(URL url, ResourceBundle resourceBundle) {
        File iconFile = new File("images/4068309-200.png");
        Image iconImage = new Image(iconFile.toURI().toString());
        iconImageView.setImage(iconImage);
        myChoiceBox.getItems().addAll(role);
    }

    @FXML
    private void initialize() {
        myChoiceBox.getItems().addAll(role);
        myChoiceBox.setOnAction(this::getRole);
    }

    public void getRole(ActionEvent event) {
        String myRole = myChoiceBox.getSelectionModel().getSelectedItem();
        myLabel.setText(myRole);
    }

    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event) throws NoSuchAlgorithmException, NoSuchProviderException {
        if (firstnameTextField.getText().isBlank() == false && lastnameTextField.getText().isBlank() == false && usernameTextField.getText().isBlank() == false && setPasswordField.getText().isBlank() == false && confirmPasswordField.getText().isBlank() == false && emailTextField.getText().isBlank() == false) {
            if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
                if (myChoiceBox.getSelectionModel().getSelectedItem() == "CLIENT") registerUser();
                if (myChoiceBox.getSelectionModel().getSelectedItem() == "SH") registerSH();
                if (myChoiceBox.getSelectionModel().getSelectedItem() == "ONG") registerONG();
                confirmPasswordLabel.setText(" ");

            } else {
                confirmPasswordLabel.setText("Password does not match!");
            }
        } else {
            registrationMessageLabel.setText("Please complete all empty fields!");
            confirmPasswordLabel.setText(" ");
        }
    }
private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
{
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt.toString();

}
    public void registerUser() throws NoSuchAlgorithmException, NoSuchProviderException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectionDB = connection.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();
        String email = emailTextField.getText();
        String role = myChoiceBox.getSelectionModel().getSelectedItem();

        String passwordToHash = password;
        String salt = getSalt();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hasedPassword = sb.toString();
        //final String secretKey = "ssshhhhhhhhhhh!!!!";
        //String encryptedPassword  = AES.encrypt(password);
        //System.out.println(Base64.getDecoder().decode(hasedPassword));
        String insertFieldsClient = "INSERT INTO account_user (lastname, firstname, username, password, email) VALUES('";
        String insertFieldsSH = "INSERT INTO admin_sh (lastname, firstname, username, parola, email) VALUES('";
        String insertFieldsONG = "INSERT INTO admin_ong (lastname, firstname, username, parola, email) VALUES('";
        String insertValues = lastname + "','" + firstname + "','" + username + "','" +password+ "','" + email +"')";
        String insertToRegisterClient = insertFieldsClient + insertValues;
        //verify username & email pentru fiecare tabel
        String verifyUsernameClient = "SELECT count(1) FROM account_user WHERE username = '" + usernameTextField.getText() + "'";
        String verifyEmailClient = "SELECT count(1) FROM account_user WHERE email = '" + emailTextField.getText() + "'";

        int ok=0;
        int ok1=0;
        try {

            Statement statement = connectionDB.createStatement();
            Statement statement1 = connectionDB.createStatement();
            Statement statement2 = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyUsernameClient);
           ResultSet queryResult1 = statement2.executeQuery(verifyEmailClient);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    checkUsername.setText("This username already exists!");
                    ok = 1;
                } else ok = 0;
                if (ok1 == 0) {
                    checkEmail.setText("");
                    registrationMessageLabel.setText("");
                }
            }
            while(queryResult1.next()){
                if(queryResult1.getInt(1)==1){
                    checkEmail.setText("This email already exists!");
                    ok1=1;} else ok1=0;
                if(ok==0) {checkUsername.setText("");
                    registrationMessageLabel.setText("");}}
            if(ok1==0 && ok==0) {
                if (myChoiceBox.getSelectionModel().getSelectedItem() == "CLIENT") {
                    statement1.executeUpdate(insertToRegisterClient);
                    checkUsername.setText("");
                    checkEmail.setText("");
                    registrationMessageLabel.setText("User has been registered successfully!");
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }}
        public void registerSH() throws NoSuchAlgorithmException, NoSuchProviderException {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectionDB = connection.getConnection();

            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            String username = usernameTextField.getText();
            String password = setPasswordField.getText();
            String email = emailTextField.getText();
            String role = myChoiceBox.getSelectionModel().getSelectedItem();

            String passwordToHash = password;
            String salt = getSalt();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String hasedPassword = sb.toString();
            //final String secretKey = "ssshhhhhhhhhhh!!!!";
            //String encryptedPassword  = AES.encrypt(password);
            //System.out.println(Base64.getDecoder().decode(hasedPassword));
            String insertFieldsClient = "INSERT INTO account_user (lastname, firstname, username, password, email) VALUES('";
            String insertFieldsSH = "INSERT INTO admin_sh (lastname, firstname, username, parola, email) VALUES('";
            String insertFieldsONG = "INSERT INTO admin_ong (lastname, firstname, username, parola, email) VALUES('";
            String insertValues = lastname + "','" + firstname + "','" + username + "','" + password + "','" + email + "')";
            String insertToRegisterSH = insertFieldsSH + insertValues;
            String verifyUsernameSH = "SELECT count(1) FROM admin_sh WHERE username = '" + usernameTextField.getText() + "'";
            String verifyEmailSH = "SELECT count(1) FROM admin_sh WHERE email = '" + emailTextField.getText() + "'";
            int ok = 0;
            int ok1 = 0;
            try {

                Statement statement = connectionDB.createStatement();
                Statement statement1 = connectionDB.createStatement();
                Statement statement2 = connectionDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyUsernameSH);
                ResultSet queryResult1 = statement2.executeQuery(verifyEmailSH);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        checkUsername.setText("This username already exists!");
                        ok = 1;
                    } else ok = 0;
                    if (ok1 == 0) {
                        checkEmail.setText("");
                        registrationMessageLabel.setText("");
                    }
                }
                while (queryResult1.next()) {
                    if (queryResult1.getInt(1) == 1) {
                        checkEmail.setText("This email already exists!");
                        ok1 = 1;
                    } else ok1 = 0;
                    if (ok == 0) {
                        checkUsername.setText("");
                        registrationMessageLabel.setText("");
                    }
                }
                if (ok1 == 0 && ok == 0) {
                    if (myChoiceBox.getSelectionModel().getSelectedItem() == "SH") {
                        statement1.executeUpdate(insertToRegisterSH);
                        checkUsername.setText("");
                        checkEmail.setText("");
                        registrationMessageLabel.setText("User has been registered successfully!");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

            public void registerONG() throws NoSuchAlgorithmException, NoSuchProviderException {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectionDB = connection.getConnection();

                String firstname = firstnameTextField.getText();
                String lastname = lastnameTextField.getText();
                String username = usernameTextField.getText();
                String password = setPasswordField.getText();
                String email = emailTextField.getText();
                String role = myChoiceBox.getSelectionModel().getSelectedItem();

                String passwordToHash = password;
                String salt = getSalt();
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(salt.getBytes());
                byte[] bytes = md.digest(passwordToHash.getBytes());
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<bytes.length;i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                String hasedPassword = sb.toString();
                //final String secretKey = "ssshhhhhhhhhhh!!!!";
                //String encryptedPassword  = AES.encrypt(password);
                //System.out.println(Base64.getDecoder().decode(hasedPassword));
                String insertFieldsONG = "INSERT INTO admin_ong (lastname, firstname, username, parola, email) VALUES('";
                String insertValues = lastname + "','" + firstname + "','" + username + "','" +password+ "','" + email +"')";
                String insertToRegisterONG= insertFieldsONG + insertValues;
                String verifyUsernameONG = "SELECT count(1) FROM admin_ong WHERE username = '" + usernameTextField.getText() + "'";
                String verifyEmailONG = "SELECT count(1) FROM admin_ong WHERE email = '" + emailTextField.getText() + "'";
                int ok=0;
                int ok1=0;
                try {

                    Statement statement = connectionDB.createStatement();
                    Statement statement1 = connectionDB.createStatement();
                    Statement statement2 = connectionDB.createStatement();
                    ResultSet queryResult = statement.executeQuery(verifyUsernameONG);
                    ResultSet queryResult1 = statement2.executeQuery(verifyEmailONG);
                    while (queryResult.next()) {
                        if (queryResult.getInt(1) == 1) {
                            checkUsername.setText("This username already exists!");
                            ok = 1;
                        } else ok = 0;
                        if (ok1 == 0) {
                            checkEmail.setText("");
                            registrationMessageLabel.setText("");
                        }
                    }
                    while(queryResult1.next()){
                        if(queryResult1.getInt(1)==1){
                            checkEmail.setText("This email already exists!");
                            ok1=1;} else ok1=0;
                        if(ok==0) {checkUsername.setText("");
                            registrationMessageLabel.setText("");}}
                    if(ok1==0 && ok==0) {
                        if (myChoiceBox.getSelectionModel().getSelectedItem() == "ONG") {
                            statement1.executeUpdate(insertToRegisterONG);
                            checkUsername.setText("");
                            checkEmail.setText("");
                            registrationMessageLabel.setText("User has been registered successfully!");
                        }

                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }

        }}

