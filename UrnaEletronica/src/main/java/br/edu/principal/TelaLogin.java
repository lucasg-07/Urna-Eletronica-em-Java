package br.edu.principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class TelaLogin {

    @FXML
    private TextField matriculaLogin;

    @FXML
    private TextField senhaLogin;

    @FXML
    private Button cadastrarLogin;

    @FXML
    private void handleCadastrar(ActionEvent click) {
        // Carregar a nova tela
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-votacao.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Votação");

            Stage loginStage = (Stage) cadastrarLogin.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
