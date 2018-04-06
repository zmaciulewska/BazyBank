package com.pabwoopj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;


public class InformationWindow {

    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //to okno bedzie zawsze na wierzchu
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(250);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Ok");

        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void display(String title, List<Object> list) {

        ListView listView = new ListView();

        for( Object o : list) {
            listView.getItems().add(o.toString());
        }

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //to okno bedzie zawsze na wierzchu
        window.setTitle(title);
        window.setMinWidth(600);
        window.setMinHeight(300);

        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(listView);
        layout.getChildren().addAll( closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}