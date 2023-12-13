package br.edu.telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaAjuda {

    @FXML
    private Button sairAjuda;
    @FXML
    private Button sobreAjuda;
    @FXML
    private ImageView logoImageView;


    @FXML
    private void initialize() {

        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
    }
    @FXML
    private void handleSobre() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-sobre.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sobre");

            Stage loginStage = (Stage) sobreAjuda.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSair() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");

            Stage loginStage = (Stage) sairAjuda.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
