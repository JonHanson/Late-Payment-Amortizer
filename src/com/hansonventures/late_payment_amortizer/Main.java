package com.hansonventures.late_payment_amortizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = new FXMLLoader().load(getClass().getResource("Window.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Late Payment Amortizer");
        primaryStage.show();
    }

    public static void main(String[] arguments)
    {
        Application.launch(arguments);
    }
}
