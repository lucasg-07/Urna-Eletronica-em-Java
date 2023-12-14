package br.edu.telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class TelaCadastro {
    @FXML
    private PasswordField newPassword;
    @FXML
    private TextField newRegistration;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private TextField newName;
    @FXML
    private Button newRegister;
    @FXML
    private Button voltaLogin;
    @FXML
    private Button ajudaCadastro;
    @FXML
    private Button sobreCadastro;
    @FXML
    private Label unvalidRegister;
    @FXML
    private CheckBox showPasswordCheckBox;
    @FXML
    private ImageView logoImageView;

    private String matricula;  // Adicionado para armazenar a matrícula

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
    private void handleTabKey(javafx.scene.input.KeyEvent event, javafx.scene.control.Control nextControl) {
        if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
            event.consume(); // Consumir o evento para evitar movimento de foco padrão

            // Mover o foco para o próximo controle
            nextControl.requestFocus();
        }
    }

    @FXML
    private void handleAjuda(ActionEvent click) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-ajuda.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajuda");

            Stage loginStage = (Stage) ajudaCadastro.getScene().getWindow();
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
            stage.setTitle("Sobre");

            Stage loginStage = (Stage) sobreCadastro.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void togglePasswordField(boolean usePasswordField) {
        if (usePasswordField) {
            newPasswordTextField.setVisible(false);
            newPassword.setVisible(true);
            newPassword.setText(newPasswordTextField.getText());
        } else {
            newPassword.setVisible(false);
            newPasswordTextField.setVisible(true);
            newPasswordTextField.setText(newPassword.getText());
        }
    }

    @FXML
    private void initialize() {
        showPasswordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            togglePasswordField(!newValue);
        });
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
        newName.setOnKeyPressed(event -> handleTabKey(event, newRegistration));
        newRegistration.setOnKeyPressed(event -> handleTabKey(event, newPassword));
        newPassword.setOnKeyPressed(event -> handleTabKey(event, showPasswordCheckBox));
        showPasswordCheckBox.setOnKeyPressed(event -> handleTabKey(event, newRegister));

    }

    @FXML
    private void handleShowPassword(ActionEvent click) {
        String password = newPassword.getText();

        if (showPasswordCheckBox.isSelected()) {
            newPasswordTextField.setText(password);
        } else {
            newPasswordTextField.setText("");
        }
    }

    @FXML
    private void handleCadastrar(ActionEvent click) {
        String name = this.newName.getText();
        String password = this.newPassword.getText();
        String registration = this.newRegistration.getText();

        boolean shouldCreate = checkNewUser(name, password, registration);

        if (shouldCreate) {
            String userRegistration = createNewUser(name, password, registration);

            if (userRegistration != null) {
                this.matricula = userRegistration;

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-login.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Login");

                    Stage loginStage = (Stage) newRegister.getScene().getWindow();
                    loginStage.close();

                    stage.show();

                    // Passe a matrícula para o controlador principal
                    TelaVotacao controladorVotacao = loader.getController();
                    controladorVotacao.setMatricula(matricula);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            this.unvalidRegister.setText("* Nenhum campo deve estar vazio!");
        }
    }

    private String createNewUser(String name, String password, String registration) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "INSERT INTO usuarios(usuario, senha, registration) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, registration);
            stmt.setString(2, password);
            stmt.setString(3, name);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean checkNewUser(String name, String password, String registration) {
        return !name.trim().isEmpty() && !password.isEmpty() && !registration.trim().isEmpty();
    }

    // Adicione este método para obter a matrícula
    public String getMatricula() {
        return matricula;
    }
}
