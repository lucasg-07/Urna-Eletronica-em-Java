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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaContagem {
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView lucasFoto;

    @FXML
    private Label candidato24;
    @FXML
    private Label candidato69;
    @FXML
    private Label nulo;
    @FXML
    private Label branco;
    @FXML
    private Button voltaLogin;
    @FXML
    private Button ajudaContagem;
    @FXML
    private Button sobreContagem;
    @FXML
    private ImageView enzoFoto;




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

    private int contarVotos(int numeroCandidato) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "SELECT COUNT(*) AS quantidade_de_usuarios FROM usuarios WHERE voto = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, numeroCandidato);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantidade_de_usuarios");
                } else {
                    return 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @FXML
    private void initialize() {
        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
        Image lucasImage = new Image(getClass().getResource("/imagens/lucas.jpg").toExternalForm());
        lucasFoto.setImage(lucasImage);
        Image enzoImage = new Image(getClass().getResource("/imagens/enzo.jpg").toExternalForm());
        enzoFoto.setImage(enzoImage);

        // Atualizar as labels com a contagem de votos
        int votosCandidato24 = contarVotos(24);
        int votosCandidato69 = contarVotos(69);
        int votosNulo = contarVotos(100);
        int votosBranco = contarVotos(101);

        candidato24.setText(votosCandidato24 + " votos");
        candidato69.setText(votosCandidato69 + " votos");
        nulo.setText("Nulo : " + votosNulo + " votos");
        branco.setText("Branco : " +votosBranco + " votos");

    }
}
