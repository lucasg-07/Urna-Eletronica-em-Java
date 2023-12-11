package br.edu.telas;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
// Importações omitidas por brevidade

public class TelaLogin {

    // Adicione constantes para mensagens
    private static final String AUTHENTICATION_FAILURE_MSG = "Falha na autenticação. Verifique as credenciais.";

    @FXML
    private Button entrarLogin;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label unvalidLogin;

    @FXML
    private Hyperlink linkCadastro;


    @FXML
    private void handleCadastro(ActionEvent click)  {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-cadastro.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Cadastro");

        Stage loginStage = (Stage) linkCadastro.getScene().getWindow();
        loginStage.close();

        stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEntrar(ActionEvent click) {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();

        try {
            if (this.authenticate(username, password)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-votacao.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votação");

                Stage loginStage = (Stage) entrarLogin.getScene().getWindow();
                loginStage.close();

                stage.show();
            } else {
                this.unvalidLogin.setText(AUTHENTICATION_FAILURE_MSG);
            }
        } catch (IOException e) {
            // Trate a exceção adequadamente (mostre uma mensagem ao usuário ou registre-a)
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