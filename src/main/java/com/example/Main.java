package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller.fxml"));

        Parent root = loader.load();

        // // CSSファイルを読み込む
        // String css = this.getClass().getResource("style.css").toExternalForm();

        // // rootにCSSを適用
        // root.getStylesheets().add(css);

        // 外部ファイル取り込み時 任意のclass名
        // OtherController otherController = loader.getController();

        Scene scene = new Scene(root);

        
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Template fxml");
        stage.show();

        // 装飾されていないウィンドウ(ステージ)をドラッグする方法
        root.setOnMousePressed(pressEvent -> {
            root.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

   public static void main(String[] args){
        launch();
    }
}
