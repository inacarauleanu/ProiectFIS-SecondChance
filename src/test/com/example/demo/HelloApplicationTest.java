package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import com.sun.javafx.*;


import static org.junit.Assert.*;

public class HelloApplicationTest extends ApplicationTest {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        //super.start(stage);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    @Test
    public void  testClickData2() {
        clickOn("#usernameTextField").write("client11");
        clickOn("#enterPasswordField").write("client");
        clickOn("#myChoiceBox1").clickOn("CLIENT");
        clickOn("#loginButton");


    }


    @Test
    public void  testClickData3() {
        clickOn("#signupButton");
        clickOn("#firstnameTextField").write("ana1");
        clickOn("#lastnameTextField").write("maria1");
        clickOn("#usernameTextField").write("clientSH");
        clickOn("#emailTextField").write("ana.maria12@yahoo.com");
        clickOn("#setPasswordField").write("client1");
        clickOn("#confirmPasswordField").write("client1");
        clickOn("#myChoiceBox").clickOn("CLIENT");
        clickOn("#registerButton");
    }



    @Test
    public void  testClickData5() {
        clickOn("#usernameTextField").write("client");
        clickOn("#enterPasswordField").write("client");
        clickOn("#myChoiceBox1").clickOn("CLIENT");
        clickOn("#loginButton");
        clickOn("#buyButton1");
        clickOn("#usernameSH").write("sh");
        clickOn("#gotosh");

    }

    @Test
    public void  testClickData6() {
        clickOn("#usernameTextField").write("ong");
        clickOn("#enterPasswordField").write("ong");
        clickOn("#myChoiceBox1").clickOn("ONG");
        clickOn("#loginButton");
        clickOn("#SeeDonationsONG");
    }

    @Test
    public void  testClickData4() {
        clickOn("#usernameTextField").write("sh");
        clickOn("#enterPasswordField").write("sh");
        clickOn("#myChoiceBox1").clickOn("SH");
        clickOn("#loginButton");
        clickOn("#SeeProducts");
    }
}