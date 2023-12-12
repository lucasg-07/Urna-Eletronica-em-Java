package br.edu.telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Button voltaCadastro;
    @FXML
    private Button ajudaCadastro;
    @FXML
    private Button sobreCadastro;
    @FXML
    private Label unvalidRegister;
    @FXML
    private CheckBox showPasswordCheckBox;



    @FXML
    private void handleVoltar(ActionEvent click)  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");

            Stage loginStage = (Stage) voltaCadastro.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAjuda(ActionEvent click)  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-ajuda.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");

            Stage loginStage = (Stage) ajudaCadastro.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSobre(ActionEvent click)  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-sobre.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");

            Stage loginStage = (Stage) sobreCadastro.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void togglePasswordField(boolean usePasswordField) {
        if (usePasswordField) {
            // Se usePasswordField for true, configura o campo como PasswordField
            newPasswordTextField.setVisible(false);
            newPassword.setVisible(true);
            newPassword.setText(newPasswordTextField.getText());
        } else {
            // Se usePasswordField for false, configura o campo como TextField
            newPassword.setVisible(false);
            newPasswordTextField.setVisible(true);
            newPasswordTextField.setText(newPassword.getText());
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
        String password = newPassword.getText();

        if (showPasswordCheckBox.isSelected()) {
            // Se o CheckBox estiver marcado, mostra a senha como TextField
            newPasswordTextField.setText(password);
        } else {
            newPasswordTextField.setText("");
        }
    }
    @FXML
    private void handleCadastrar(ActionEvent click){
        String name = this.newName.getText();
        String password = this.newPassword.getText();
        String registration = this.newRegistration.getText();

        boolean shouldCreate = checkNewUser(name,password,registration);

        if(shouldCreate){
            try {
                if (this.createNewUser(name,password,registration)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-login.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Login");

                    Stage loginStage = (Stage) newRegister.getScene().getWindow();
                    loginStage.close();

                    stage.show();
                } else {
                    System.out.println("Erro");
                }
            } catch (IOException e) {
                // Trate a exceção adequadamente (mostre uma mensagem ao usuário ou registre-a)
                e.printStackTrace();
            }
        }
        else {
            this.unvalidRegister.setText("* Nenhum campo deve estar vazio!");
        }

    }




    private boolean createNewUser(String name, String password, String registration){
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "INSERT INTO usuarios(usuario, senha, registration) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, registration);
            stmt.setString(2, password);
            stmt.setString(3, name);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;


        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
    private boolean checkNewUser(String name, String password, String registration){
      return !name.trim().isEmpty() && !password.isEmpty() && !registration.trim().isEmpty();
    }

}