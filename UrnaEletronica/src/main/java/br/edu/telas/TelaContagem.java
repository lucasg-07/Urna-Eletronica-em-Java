package br.edu.telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaContagem {
    @FXML
    private ImageView logoImageView;

    @FXML
    private Label candidato24;
    @FXML
    private Label candidato69;
    @FXML
    private Button voltaLogin;
    @FXML
    private Button ajudaContagem;
    @FXML
    private Button sobreContagem;




    @FXML
    private void handleAjuda(ActionEvent click) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-ajuda.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajuda");

            Stage loginStage = (Stage) ajudaContagem.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSobre(ActionEvent click) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-sobre.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajuda");

            Stage loginStage = (Stage) sobreContagem.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleVoltar(ActionEvent click) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");

            Stage loginStage = (Stage) voltaLogin.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleLabel24(){

        String query = "SELECT COUNT(*) AS quantidade_de_usuarios FROM usuarios WHERE voto = 24";
        this.candidato24.setText(query);
    }
    private void handleLabel69(){

        String query = "SELECT COUNT(*) AS quantidade_de_usuarios FROM usuarios WHERE voto = 24";
        this.candidato69.setText(query);
    }

    @FXML
    private void initialize() {

        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
    }
}
