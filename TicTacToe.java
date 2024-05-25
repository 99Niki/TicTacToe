package TicTacToe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Optional;

public class TicTacToe implements EventHandler<ActionEvent> {
    private  int[][] T;
    private VBox root;
    private HBox H1,H2,H3;
    private Button[][] b;
    private boolean player; //true-playerX-1, false-playerO-0
    private Image imageX;
    private Image imageO;
    private Alert alert; // Game Over Scene
    public TicTacToe()
    {
        //initialize T to -1
        T = new int[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                T[i][j]=-1;
            }
        }
        root = new VBox();
        H1 = H2 = H3 = new HBox();
        //create button and set the size
        b = new Button[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                b[i][j]= new Button(" ");
                b[i][j].setMinSize(100,100);
                b[i][j].setId("b"+i+j);
                b[i][j].setOnAction(this);
            }
        }
        H1.getChildren().addAll(b[0][0],b[0][1],b[0][2]);
        H2.getChildren().addAll(b[1][0],b[1][1],b[1][2]);
        H3.getChildren().addAll(b[2][0],b[2][1],b[2][2]);
        root.getChildren().addAll(H1,H2,H3);
        player = true;
        imageX = new Image("x.jpg");
        imageO = new Image("0.jpg");
        alert = new Alert(Alert.AlertType.INFORMATION);
    }

    public Pane getRoot()
    {
        return root;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        //click the button and get the location
        Button clickButton =(Button)actionEvent.getSource();
        int raw = GridPane.getRowIndex(clickButton);
        int col = GridPane.getColumnIndex(clickButton);
        //check the button and the player
        if(clickButton.getText().isEmpty())
        {
            if(player==true){
                clickButton.setBackground(new Background(new BackgroundImage(imageX,null,null,null,null)));
                T[raw][col]=1;
                player=false;
            }else
            {
                clickButton.setBackground(new Background(new BackgroundImage(imageO,null,null,null,null)));
                T[raw][col]=0;
                player=true;
            }
            check();
        }
    }

    //check winner
    public void check()
    {
        if(T[0][0]+T[0][1]+T[0][2]==3
                ||T[1][0]+T[1][1]+T[1][2]==3
                ||T[2][0]+T[2][1]+T[2][2]==3
                ||T[0][0]+T[1][0]+T[2][0]==3
                ||T[0][1]+T[1][1]+T[2][1]==3
                ||T[0][2]+T[1][2]+T[2][2]==3
                ||T[0][0]+T[1][1]+T[2][2]==3
                ||T[0][2]+T[1][1]+T[2][0]==3
        ){
            alert.setHeaderText("X WIN!!");
            Optional<ButtonType> result =alert.showAndWait();
        }if(T[0][0]+T[0][1]+T[0][2]==0
            ||T[1][0]+T[1][1]+T[1][2]==0
            ||T[2][0]+T[2][1]+T[2][2]==0
            ||T[0][0]+T[1][0]+T[2][0]==0
            ||T[0][1]+T[1][1]+T[2][1]==0
            ||T[0][2]+T[1][2]+T[2][2]==0
            ||T[0][0]+T[1][1]+T[2][2]==0
            ||T[0][2]+T[1][1]+T[2][0]==0
            ){
                alert.setHeaderText("O WIN!");
                Optional<ButtonType> result =alert.showAndWait();
            }if(T[0][0]!=-1&&T[0][1]!=-1&&T[0][2]!=-1
            &&T[1][0]!=-1&&T[1][1]!=-1&&T[1][2]!=-1
            &&T[2][0]!=-1&&T[2][1]!=-1&&T[2][2]!=-1)
            {
                alert.setHeaderText("Oop! No Winner and Game Over!");
                Optional<ButtonType> result =alert.showAndWait();
            }
    }
}
