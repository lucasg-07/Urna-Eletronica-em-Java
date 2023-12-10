package br.edu.principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class TelaCadastro {
    @FXML
    private PasswordField newPassword;
    @FXML
    private TextField newRegistration;
    @FXML
    private TextField newName;
    @FXML
    private Button newRegister;


    @FXML
    private void handleCadastrar(ActionEvent click){
        String name = this.newName.getText();
        String registration = this.newRegistration.getText();
        String password = this.newPassword.getText();

        try {
            if (this.createNewUser(name,registration,password)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votação");

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




    private boolean createNewUser(String name, String password, String registration){
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "INSERT INTO usuarios(usuario, senha, registration) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, registration);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;


        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

}