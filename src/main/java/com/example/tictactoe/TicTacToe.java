package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Optional;

public class TicTacToe implements EventHandler<ActionEvent> {
    private VBox root;
    private HBox H1,H2,H3;
   // private Button b00,b01,b02,b10,b11,b12,b20,b21,b22;
    private Button[][] B;
    private int[][]T;
    private boolean player;

    public TicTacToe()
    {
        root = new VBox();
        H1 = new HBox();
        H2 = new HBox();
        H3 = new HBox();

        B = new Button[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                B[i][j]= new Button(" ");
                B[i][j].setMinSize(100,100);
                B[i][j].setOnAction(this);
            }
        }

        T = new int[3][3];
        //initialize all Ts to -1
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                T[i][j]=-1;
            }
        }
        //create 3*3
        H1.getChildren().addAll(B[0][0],B[0][1],B[0][2]);
        H1.setSpacing(10);
        H2.getChildren().addAll(B[1][0],B[1][1],B[1][2]);
        H2.setSpacing(10);
        H3.getChildren().addAll(B[2][0],B[2][1],B[2][2]);
        H3.setSpacing(10);
        root.getChildren().addAll(H1,H2,H3);
        root.setSpacing(20);
        player=true;
    }

    private int winner()
    {
        if((T[0][0]+T[0][1]+T[0][2]==3)
                ||T[1][0]+T[1][1]+T[1][2]==3
                ||T[2][0]+T[2][1]+T[2][2]==3
                ||T[0][0]+T[1][0]+T[2][0]==3
                ||T[0][1]+T[1][1]+T[2][1]==3
                ||T[0][2]+T[1][2]+T[2][2]==3
                ||T[0][0]+T[1][1]+T[2][2]==3
                ||T[0][2]+T[1][1]+T[2][0]==3
        ) {return 1;}
        else if(T[0][0]+T[0][1]+T[0][2]==30
                ||T[1][0]+T[1][1]+T[1][2]==30
                ||T[2][0]+T[2][1]+T[2][2]==30
                ||T[0][0]+T[1][0]+T[2][0]==30
                ||T[0][1]+T[1][1]+T[2][1]==30
                ||T[0][2]+T[1][2]+T[2][2]==30
                ||T[0][0]+T[1][1]+T[2][2]==30
                ||T[0][2]+T[1][1]+T[2][0]==30
        ) {return 0;}
        else if(T[0][0]!=-1&&T[0][1]!=-1&&T[0][2]!=-1&&
                T[1][0]!=-1&&T[1][1]!=-1&&T[1][2]!=-1&&
                T[2][0]!=-1&&T[2][1]!=-1&&T[2][2]!=-1)
        {
            return 2;
        }
    return -1;
    }

    //show the winner
    private void showWinner(int winner)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!");
        if(winner==1)
        {
            alert.setContentText("Congratulation,Player X Wins!");
        }else if(winner==0)
        {
            alert.setContentText("Congratulation,Player O Wins!");
        }else
        {
            alert.setContentText("Oop! No winner, it is a draw.");
        }

        //two button: start a new game or exit the game
        ButtonType newGame = new ButtonType("Start a new Game");
        ButtonType exitGame = new ButtonType("Exit");
        alert.getButtonTypes().setAll(newGame,exitGame);

        //return option
        Optional<ButtonType> re= alert.showAndWait();
        //isPresent() method of Optional returns true if there is a value present inside the Optional,
        //and false if it is empty.
        if(re.isPresent()&&re.get()==newGame) //reset the game
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    T[i][j]=-1;

                }
            }
            player=true;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    B[i][j].setText(" ");
                    B[i][j].setDisable(false);
                }
            }
            
        }else
        {
            System.exit(0);
        }

    }
    public Pane getRoot()
    {
        return root;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button click = (Button) actionEvent.getSource();
        //get the location of click
        int row=-1, col=-1;

        if (click == B[0][0]) {
            row = 0;
            col = 0;
        }
        if (click == B[0][1]) {
            row = 0;
            col = 1;
        }
        if (click == B[0][2]) {
            row = 0;
            col = 2;
        }
        if (click == B[1][0]) {
            row = 1;
            col = 0;
        }if (click == B[1][1]) {
            row = 1;
            col = 1;
        }if (click == B[1][2]) {
            row = 1;
            col = 2;
        }if (click == B[2][0]) {
            row = 2;
            col = 0;
        }if (click == B[2][1]) {
            row = 2;
            col = 1;
        }if (click == B[2][2]) {
            row = 2;
            col = 2;
        }

        //check the button is already occupied or not
        if(T[row][col]==-1)
        {
            if(player) //player true, X ,1
            {
                click.setText("X");
                click.setFont(Font.font(30));
                T[row][col]=1;
                player=false;
            }else //player false, O ,10
            {
                click.setText("O");
                click.setFont(Font.font(30));
                T[row][col]=10;
                player=true;
            }
        }

        //lock the button
        click.setDisable(true);

        //check winner
        int win= winner();
        if(win!=-1)
        {
        showWinner(win);
        }
    }
}
