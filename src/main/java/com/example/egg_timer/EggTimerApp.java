package com.example.eggtimer;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalTime;

public class EggTimerApp extends Application {
    private int minutes;
    private int seconds;
    Label timerLabel = new Label(); // Timer label to display countdown

    @Override
    public void start(Stage stage) {
        // Create root container
        BorderPane root = new BorderPane();

        // Load background image correctly
        Image backgroundImage = new Image("file:src/main/resources/images/photo_2025-02-06_18-00-04.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true) // Covers entire window
        );
        root.setBackground(new Background(background));

        // Center content
        VBox centerContent = new VBox(10);
        centerContent.setAlignment(Pos.CENTER);

        Text text1 = new Text("Let's Time");
        text1.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
        text1.setStyle("-fx-stroke: black; fx-stroke-width:2px;");
        text1.setFill(Color.WHITE);

        Text text2 = new Text("Your Egg!");
        text2.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
        text2.setStyle("-fx-stroke: black; fx-stroke-width:2px;");
        text2.setFill(Color.WHITE);

        // Create Start Button
        Button startButton = new Button("Start");
        styleButton(startButton);

        // Add click event to open second window and close current one
        startButton.setOnAction(e -> {
            openEggSelectionWindow();
            Stage currentStage = (Stage) startButton.getScene().getWindow();
            currentStage.close();
        });

        centerContent.getChildren().addAll(text1, text2, startButton);
        root.setCenter(centerContent);

        // Create scene
        Scene scene = new Scene(root, 450, 450);
        stage.setScene(scene);
        stage.setTitle("Egg Timer <3");
        stage.setResizable(false);
        stage.show();
    }

    // Separate method for button styling
    private void styleButton(Button button) {
        button.setStyle(
                "-fx-background-color: #efe993; " +
                        "-fx-font-size: 18px; " +
                        "-fx-text-fill: black; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-border-radius: 20px; " +
                        "-fx-border-color: black; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.2) , 10,0,3,3 );"
        );

        // Hover effect
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #fbf601; " +
                        "-fx-font-size: 20px; " +
                        "-fx-text-fill: black; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-border-radius: 20px; " +
                        "-fx-border-color: black; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 15,0,5,5 );"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #efe993; " +
                        "-fx-font-size: 18px; " +
                        "-fx-text-fill: black; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-border-radius: 20px; " +
                        "-fx-border-color: black; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.2) , 10,0,3,3 );"
        ));
    }

    // Method to open the second window
    private void openEggSelectionWindow() {
        Stage eggStage = new Stage();


        VBox eggLayout = new VBox(15);
        eggLayout.setAlignment(Pos.CENTER);
        Image backgroundImage = new Image("file:src/main/resources/images/photo_2025-02-06_18-00-04.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true) // Covers entire window
        );
        eggLayout.setBackground(new Background(background));

        // Title
        Text title = new Text("What are we making today?");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        title.setStyle("-fx-stroke: black; fx-stroke-width:2px;");
        title.setFill(Color.WHITE);

        // Load images
        Image egg1 = new Image("file:src/main/resources/images/25850716.png");
        Image egg2 = new Image("file:src/main/resources/images/23976986.png");
        Image egg3 = new Image("file:src/main/resources/images/25548664.png");
        Image egg4 = new Image("file:src/main/resources/images/pngegg.png");

        // Create ImageViews
        ImageView eggView1 = createEggImageView(egg1, "Soft Boiled");
        ImageView eggView2 = createEggImageView(egg2, "Medium Boiled");
        ImageView eggView3 = createEggImageView(egg3, "Hard Boiled");
        ImageView eggView4 = createEggImageView(egg4, "Poached");

        // Create a grid and add images with text below them
        GridPane grid = new GridPane();
        grid.setHgap(70);
        grid.setVgap(40);
        grid.setAlignment(Pos.CENTER);

        grid.add(createEggBox(eggView1, "Soft Boiled"), 0, 0);
        grid.add(createEggBox(eggView2, "Medium Boiled"), 1, 0);
        grid.add(createEggBox(eggView3, "Hard Boiled"), 0, 1);
        grid.add(createEggBox(eggView4, "Poached"), 1, 1);

        // Add everything to the layout
        eggLayout.getChildren().addAll(title, grid);

        // Create scene and stage
        Scene eggScene = new Scene(eggLayout, 470, 470);
        eggStage.setScene(eggScene);
        eggStage.setTitle("Egg Timer <3");
        eggStage.setResizable(false);
        eggStage.show();
    }

    // Method to create an ImageView with hover effects
    private ImageView createEggImageView(Image image, String eggType) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(130);
        imageView.setFitHeight(130);
        imageView.setStyle("-fx-stroke: black; -fx-stroke-width: 2px");

        // Add hover effects
        imageView.setOnMouseEntered(e -> {
            imageView.setScaleX(1.5);
            imageView.setScaleY(1.5);
            imageView.setEffect(new DropShadow(10, Color.GRAY));
        });

        imageView.setOnMouseExited(e -> {
            imageView.setScaleX(1.0);
            imageView.setScaleY(1.0);
            imageView.setEffect(null);
        });

        // Make image clickable
        imageView.setOnMouseClicked(e -> {handleEggSelection(eggType);
            Stage currentStage = (Stage) imageView.getScene().getWindow();
            currentStage.close();});

        return imageView;
    }

    // Method to wrap an image and its text
    private VBox createEggBox(ImageView imageView, String eggType) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        Text text = new Text(eggType);
        text.setFont(Font.font("Verdana", 14));
        text.setFill(Color.BLACK);
        box.getChildren().addAll(imageView, text);
        return box;
    }

    // Handle egg selection
    private void handleEggSelection(String eggType) {

        Stage selectionStage = new Stage();
        VBox selectionLayout = new VBox(10);
        selectionLayout.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image("file:src/main/resources/images/photo_2025-02-06_18-00-04.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true) // Covers entire window
        );
        selectionLayout.setBackground(new Background(background));
        Text selectedText = new Text("Let's Cook " + eggType + " Egg");
        selectedText.setFont(Font.font("Verdana",FontWeight.BOLD, 27));
        selectedText.setFill(Color.WHITE);
        selectedText.setStyle("-fx-stroke-width: 2 px; -fx-stroke:black;");

        Button turnOnButton = new Button("Turn On");
        if (eggType.equals("Soft Boiled")) {
            turnOnButton.setOnAction(e -> {

                minutes = 4;
                seconds = 10;
                selectionLayout.getChildren().remove(turnOnButton);
                startCountdown();



            });

            styleButton(turnOnButton);
        } else if (eggType.equals("Medium Boiled")) {
            turnOnButton.setOnAction(e -> {

                minutes = 6;
                seconds = 10;
                selectionLayout.getChildren().remove(turnOnButton);
                startCountdown();



            });

            styleButton(turnOnButton);

        } else if (eggType.equals("Hard Boiled")) {
            turnOnButton.setOnAction(e -> {

                minutes = 0;
                seconds = 10;
                selectionLayout.getChildren().remove(turnOnButton);
                startCountdown();



            });

            styleButton(turnOnButton);

        } else if (eggType.equals("Poached")) {
            turnOnButton.setOnAction(e -> {

                minutes = 3;
                seconds = 10;
                selectionLayout.getChildren().remove(turnOnButton);
                startCountdown();



            });

            styleButton(turnOnButton);

        }

        selectionLayout.getChildren().addAll(selectedText, turnOnButton, timerLabel);
        Scene selectionScene = new Scene(selectionLayout, 450, 450);
        selectionStage.setScene(selectionScene);
        selectionStage.setTitle("Egg Timer <3");
        selectionStage.show();
    }

    private void startCountdown() {

        timerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        timerLabel.setTextFill(Color.RED);
        LocalTime end = LocalTime.now().plusMinutes(minutes).plusSeconds(seconds);
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                Duration remaining = Duration.between(LocalTime.now(), end);
                if (remaining.isPositive()) {
                    timerLabel.setText(format(remaining));
                } else {
                    timerLabel.setText(format(Duration.ZERO));
                    stop();

                }
            }

            private String format(Duration remaining) {
                return String.format("%02d:%02d", remaining.toMinutesPart(), remaining.toSecondsPart());
            }
        };
        timer.start(); // Start the timer



    }


    public static void main(String[] args) {
        launch();
    }
}