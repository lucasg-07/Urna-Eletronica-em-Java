package br.edu.telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaSobre {

    @FXML
    private Button ajudaSobre;
    @FXML
    private Button sairSobre;
    @FXML
    private ImageView logoImageView;
    @FXML
    private Text sobreTexto;


    @FXML
    private void initialize() {
        String sobreTextoContent = "SOBRE\n" +
                "URNA ELETRONICA EM JAVA \n" +
                "Build on 1.0.0, built on December 14, 2023.\n" +
                "Runtime Version: 20.0.1+9 – 19.\n" +
                "VM: OpenJDK 20 64-Bit\n" +
                "Powered by InovaTech Solutions\n" +
                "Copyright  2023 InovaTech Solutions LTDA.";

        sobreTexto.setText(sobreTextoContent);

        // Aplicar negrito ao título "SOBRE"
        sobreTexto.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
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

            Stage loginStage = (Stage) sairSobre.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAjuda() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-ajuda.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajuda");

            Stage loginStage = (Stage) ajudaSobre.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
