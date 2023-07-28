package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller implements Initializable {

    @FXML
    private Pane timerView;

    @FXML
    private ImageView close;

    @FXML
    private Text timerText;

    @FXML
    private Button hMinusBtn;

    @FXML
    private Button hPlusBtn;

    @FXML
    private Button mMinusBtn;

    @FXML
    private Button mPlusBtn;

    @FXML
    private Button sMinusBtn;

    @FXML
    private Button sPlusBtn;

     @FXML
    private Button startBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button stopBtn;

    @FXML
    void shutdown(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ItemEvent itemEvent = new ItemEvent(timerText);

        hPlusBtn.setOnAction(itemEvent);
        mPlusBtn.setOnAction(itemEvent);
        sPlusBtn.setOnAction(itemEvent);

        hMinusBtn.setOnAction(itemEvent);
        mMinusBtn.setOnAction(itemEvent);
        sMinusBtn.setOnAction(itemEvent);

        startBtn.setOnAction(itemEvent);
        stopBtn.setOnAction(itemEvent);
        clearBtn.setOnAction(itemEvent);

    }
}
