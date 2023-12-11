package br.edu.principal;


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
    private TextField newPasswordTextField;

    @FXML
    private CheckBox showPasswordCheckBox;

    private void togglePasswordField(boolean usePasswordField) {
        if (usePasswordField) {
            // Se usePasswordField for true, configura o campo como PasswordField
            newPasswordTextField.setVisible(false);
            passwordField.setVisible(true);
            passwordField.setText(newPasswordTextField.getText());
        } else {
            // Se usePasswordField for false, configura o campo como TextField
            passwordField.setVisible(false);
            newPasswordTextField.setVisible(true);
            newPasswordTextField.setText(passwordField.getText());
        }
    }

    @FXML
    private void initialize() {
        // Adiciona um listener ao CheckBox para detectar alterações de estado
        showPasswordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Chama o método para alternar entre PasswordField e TextField
            togglePasswordField(!newValue);
        });
    }

    @FXML
    private void handleShowPassword(ActionEvent click) {
        String password = passwordField.getText();

        if (showPasswordCheckBox.isSelected()) {
            // Se o CheckBox estiver marcado, mostra a senha como TextField
            newPasswordTextField.setText(password);
        } else {
            newPasswordTextField.setText("");
        }
    }

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