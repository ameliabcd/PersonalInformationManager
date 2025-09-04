package com.example.personalinformationmanager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


// Starter template to help you begin:

public class HelloApplication extends Application {

    // Instance variables for your UI components
    private TextField nameField;
    private TextField emailField;
    private TextField phoneField;
    private List<String> contacts=new ArrayList<>();

    private Label state;


    @Override
    public void start(Stage primaryStage) {//primary stage display
        // Your implementation here
        Scene scene = new Scene(createContent(), 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        scene.setOnKeyPressed(event -> {//enter key shortcut, directly add contact
            if (event.getCode() == KeyCode.ENTER) {
                addContact();
            }
        });

        primaryStage.show();
    }

    private VBox createContent() {
        // Build your UI here
        Label title = new Label();//title
        title.setText("Personal Information Manager");
        title.setStyle("-fx-font-size: 25px; -fx-padding: 8px; " +
                "-fx-background-color: white; -fx-text-fill: black; " +
                "-fx-font-family: 'Courier New', monospace;" + "-fx-font-weight: bold");

        state=new Label();
        state.setText("");//indicate add contact successful
        state.setStyle("-fx-font-size: 27px; -fx-padding: 8px; " +
                "-fx-background-color: white; -fx-text-fill: black; " +
                "-fx-font-family: 'Courier New', monospace;"+"-fx-font-weight: bold");

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #fcfdff;");
        mainLayout.getChildren().addAll(title, textFields(), button(), state);

        return mainLayout;
    }

    private GridPane textFields() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        Label name = new Label();
        Label email = new Label();
        Label phone = new Label();

        name.setText("Name: ");
        email.setText("Email: ");
        phone.setText("Phone: ");
        name.setStyle("-fx-font-size: 24px; -fx-padding: 10px; " +
                "-fx-background-color: #fcfdff; -fx-text-fill: black; " +
                "-fx-font-family: 'Avenir';" + "-fx-font-weight: bold");
        email.setStyle("-fx-font-size: 24px; -fx-padding: 10px; " +
                "-fx-background-color: #fcfdff; -fx-text-fill: black; " +
                "-fx-font-family: 'Avenir';" + "-fx-font-weight: bold");
        phone.setStyle("-fx-font-size: 24px; -fx-padding: 10px; " +
                "-fx-background-color: #fcfdff; -fx-text-fill: black; " +
                "-fx-font-family: 'Avenir';" + "-fx-font-weight: bold");

        nameField = new TextField("");
        emailField = new TextField("");
        phoneField = new TextField("");
        nameField.setPromptText("Enter full name");
        emailField.setPromptText("name@email.com");
        phoneField.setPromptText("555-123-4567");
        nameField.setAlignment(Pos.BASELINE_LEFT);
        emailField.setAlignment(Pos.BASELINE_LEFT);
        phoneField.setAlignment(Pos.BASELINE_LEFT);
        Label[] lab = {name, email, phone};
        TextField[] text = {nameField, emailField, phoneField};


        for (int row = 0; row < 3; row++) {
            grid.add(lab[row], 0, row);
            grid.add(text[row], 1, row);
        }
        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    private HBox button() { //three buttons
        HBox hBox = new HBox(10);

        Button contact=new Button("Add Contact");
        Button clear=new Button("Clear Fields");
        Button display=new Button("Display All");

        contact.setStyle("-fx-background-color: #8293e0; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 10px; " +
                "-fx-border-radius: 8px;");
        clear.setStyle("-fx-background-color: #e36646; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 10px; " +
                "-fx-border-radius: 8px;");
        display.setStyle("-fx-background-color:#4af0a8 ; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 10px; " +
                "-fx-border-radius: 8px;");

        //events for three buttons
        clear.setOnAction(this::clearFields);
        contact.setOnAction(actionEvent -> addContact());
        display.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Show all contacts
                Stage stage2 = new Stage();
                Scene scene2 = new Scene(displayContact(), 800, 500);
                stage2.setScene(scene2);
                stage2.setResizable(false);
                stage2.show();

            }
        });

        hBox.getChildren().addAll(contact, clear, display);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        return hBox;
    }

    private VBox displayContact() {//another stage for displaying ocntacts
        VBox vBox = new VBox(10);
        String content = "";
        Label contact = new Label();
        Label title = new Label();
        title.setText("All My Contacts");
        title.setStyle("-fx-font-size: 27px; -fx-padding: 8px; " +
                " -fx-text-fill: black; " + "-fx-font-family: 'Courier New', monospace;" + "-fx-font-weight: bold");
        title.setAlignment(Pos.CENTER);

        int num=0;
        for (int i = 0; i < contacts.size(); i++) {
            num=i+1;
            content = content +"Contact "+num+" "+ contacts.get(i) + "\n";
        }
        contact.setText(content);
        contact.setStyle("-fx-font-size: 15px; -fx-padding: 7px; " +
                " -fx-text-fill: black; " + "-fx-font-family: 'Courier New', monospace;" + "-fx-font-weight: bold");
        vBox.getChildren().addAll(title, contact);
        return vBox;
    }

    private void addContact() {//method for adding contact
        // Handle adding a contact
        boolean isSuccessful = true;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> state.setText("")));//add successful
        timeline.play();

        if (phoneField.getText().length() >= 10) {//ensure phone number has correct format
            String phone = phoneField.getText(0, 3) + phoneField.getText(4, 7) + phoneField.getText(8, phoneField.getText().length());
        try{
            long x=Long.parseLong(phone);

        } catch(NumberFormatException error)
        {
            state.setText("Phone number invalid");
            isSuccessful=false;
        }
            if (isSuccessful) {
                state.setText("Add Contact Successful");
                contacts.add("Name: " + nameField.getText() + " " + "Email: " + emailField.getText() + " " + "Phone: " + phoneField.getText());
            }
        }
        else
        {
            state.setText("Invalid Phone number");
        }
    }

    private void clearFields(ActionEvent event) {//clear text fields
        state.setText("");
        nameField.clear();
        emailField.clear();
        phoneField.clear();
    }

}