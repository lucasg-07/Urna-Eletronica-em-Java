package br.edu.telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaLogin {

    @FXML
    private Button entrarLogin;
    @FXML
    private Button ajudaLogin;
    @FXML
    private Button sobreLogin;
    @FXML
    private Button contagemLogin;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label unvalidLogin;

    @FXML
    private Hyperlink linkCadastro;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private ImageView logoImageView;

    private void togglePasswordField(boolean usePasswordField) {
        if (usePasswordField) {
            newPasswordTextField.setVisible(false);
            passwordField.setVisible(true);
            passwordField.setText(newPasswordTextField.getText());
        } else {
            passwordField.setVisible(false);
            newPasswordTextField.setVisible(true);
            newPasswordTextField.setText(passwordField.getText());
        }
    }

    @FXML
    private void initialize() {
        togglePasswordField(true);

        showPasswordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            togglePasswordField(!newValue);
        });

        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
    }

    @FXML
    private void handleShowPassword(ActionEvent click) {
        String password = passwordField.getText();

        if (showPasswordCheckBox.isSelected()) {
            newPasswordTextField.setText(password);
        } else {
            newPasswordTextField.setText("");
        }
    }

    @FXML
    private void handleCadastro(ActionEvent click) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-cadastro.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cadastro");

            Stage loginStage = (Stage) linkCadastro.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEntrar(ActionEvent click) {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();

        try {
            if (this.authenticate(username, password)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-votacao.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votação");

                // Passe a matrícula para o controlador de votação
                TelaVotacao controladorVotacao = loader.getController();
                controladorVotacao.setMatricula(username);


                // Verifique se o usuário já votou
                if (controladorVotacao.verificarStatusVoto()) {
                    this.unvalidLogin.setText("Você já votou, aguarde outra votação");
                } else {
                    // Se o usuário ainda não votou, continue com a exibição da tela de votação
                    Stage loginStage = (Stage) entrarLogin.getScene().getWindow();
                    loginStage.close();
                    stage.show();
                }
            } else {
                this.unvalidLogin.setText("Falha na autenticação. Verifique as credenciais.");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleContagem() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-contagem.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sobre");

            Stage loginStage = (Stage) contagemLogin.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSobre() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-sobre.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sobre");

            Stage loginStage = (Stage) sobreLogin.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAjuda() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-ajuda.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajuda");

            Stage loginStage = (Stage) ajudaLogin.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticate(String usuario, String senha) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            // Trate a exceção adequadamente (mostre uma mensagem ao usuário ou registre-a)
            e.printStackTrace();
            return false;
        }
    }
}