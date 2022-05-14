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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

import static com.example.demo.RegistrationController.ROLE;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        File brandingFile = new File("images/poza.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
        myChoiceBox1.getItems().addAll(role1);

    }

    @FXML
    private void initialize() {
        myChoiceBox1.getItems().addAll(role1);
        myChoiceBox1.setOnAction(this::getRole);
    }

    public void getRole(ActionEvent event) {
      String myRole = myChoiceBox1.getSelectionModel().getSelectedItem();
        myLabel.setText(myRole);
        // myRole1=myRole;
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
    public void loginButtonOnAction(ActionEvent event)
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

    public void validateLogin()
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyRole = "SELECT role FROM account_user WHERE username ='" + usernameTextField.getText() + " '" ;
        String verifyLogin = "SELECT count(1) FROM account_user WHERE username = '" + usernameTextField.getText() + "'" + " AND password ='" + enterPasswordField.getText() +"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            Statement statement3 = connectDB.createStatement();
            ResultSet queryResult2 = statement3.executeQuery(verifyRole);

            while(queryResult.next()) {

                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Congrats!");
                          if (myChoiceBox1.getSelectionModel().getSelectedItem()=="CLIENT")
                                createClientForm();
                            }
                        //  createAccountForm();
                    else{
                        loginMessageLabel.setText("This account does not not exist! Please sign up if you want to create one!");
                        //createAccountForm();

                    }
                }



           /* while(queryResult2.next()){
                if (queryResult2.getInt(1)==1) {

                }

            }*/
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


    //incercare pt client
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
}