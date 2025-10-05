package com.example.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TicTacToe tt = new TicTacToe();
        Scene scene = new Scene(tt.getRoot(), 340,350);
        stage.setTitle("Tic Tac Toe"); //name
        //set the scene to stage
        stage.setScene(scene);
        //show the scene
        stage.show();
    }

    public static void main(String[] args) {
        launch ();
    }
}