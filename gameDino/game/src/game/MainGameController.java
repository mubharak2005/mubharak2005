package game;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;

public class MainGameController {
   @FXML
    private AnchorPane root;

    @FXML
    private Pane gameArea;

    @FXML
    private ImageView background;

    @FXML
    private ImageView dino;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button restartButton;

    @FXML
    private Button balikMenu;
    
    private int highScore=0;
    
    //mengatur gerak rintangan kaktus dan batu
    private ImageView kaktus, batu;
    private int score = 0;
    private double obstacleSpeed = 5.0;
    private boolean isGameRunning = true;
    boolean isDown = false;

    private double velocityY = 0;
    private final double GRAVITY = 0.1;
    
    //mengambil gambar kaktus dan batu dari file resource
    private final Image kaktusImage = new Image(getClass().getResource("/resource/kaktus.png").toExternalForm());
    private final Image batuImage = new Image(getClass().getResource("/resource/batu.png").toExternalForm());
    private final Random random = new Random();

    private long lastUpdateTime = 0;
    private long lastSpawnTime = 0;
    private static final long SPEED_INCREASE_INTERVAL = 1000000000L;
    private static final long OBSTACLE_SPAWN_INTERVAL = 1500000000L;

    Dir dir = Dir.botbot;
    Message message = new Message();
    AlertAlert alertalert = new AlertAlert();

    enum Dir{
        upup,botbot;
    }

    @FXML
    private void backMenu(MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("beranda.fxml"));
            Parent mainMenuRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Dashboard Dinorun !");

            Scene scene = new Scene(mainMenuRoot);
            stage.setScene(scene);

            stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    private void setupBackground() {
    // Buat objek Image dengan path gambar background
    Image backgroundImage = new Image(getClass().getResource("/resource/background.png").toExternalForm());
    
    // Buat ImageView untuk menampilkan gambar
    background = new ImageView(backgroundImage);
    
    // Atur properti ImageView
    background.setFitWidth(gameArea.getPrefWidth()); // Sesuaikan lebar dengan gameArea
    background.setFitHeight(gameArea.getPrefHeight()); // Sesuaikan tinggi dengan gameArea
    background.setPreserveRatio(false); // Nonaktifkan preserve ratio agar sesuai dengan ukuran gameArea

    // Tambahkan ke game area sebagai elemen paling belakang
    gameArea.getChildren().add(0, background);
}
