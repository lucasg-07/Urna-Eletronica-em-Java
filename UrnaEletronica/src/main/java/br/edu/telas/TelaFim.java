package br.edu.telas;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaFim {

    @FXML
    private ImageView logoImageView;

    private final AudioClip sound; // Variável para armazenar o som

    public TelaFim() {
        // Carregar o som durante a criação da tela
        sound = new AudioClip(getClass().getResource("/Sons/SomUrna.wav").toExternalForm());
    }

    @FXML
    private void initialize() {
        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);

        // Reproduzir o som imediatamente ao iniciar a tela
        Platform.runLater(() -> sound.play());

        // Criar uma tarefa (Task) para esperar por 3 segundos (3000 milissegundos)
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2000); // Espera por 2 segundos
                } catch (InterruptedException e) {
                    // Lidar com a exceção, se necessário
                }
                return null;
            }
        };

        // Configurar o que fazer após a tarefa ser concluída (abrir outra tela)
        sleeper.setOnSucceeded(event -> {
            // Abrir a próxima tela na thread da interface gráfica (JavaFX)
            Platform.runLater(() -> {
                try {
                    abrirTela();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        // Iniciar a tarefa em uma nova thread
        new Thread(sleeper).start();
    }

    private void abrirTela() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Voto Computado");

        Stage loginStage = (Stage) logoImageView.getScene().getWindow();
        loginStage.close();

        stage.show();
    }
}
