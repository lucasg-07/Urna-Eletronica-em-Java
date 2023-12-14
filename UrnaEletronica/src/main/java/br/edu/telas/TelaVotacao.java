package br.edu.telas;

import br.edu.uteis.Candidato;
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

public class TelaVotacao {

    @FXML
    private Button confirma;

    @FXML
    private Label chapa;

    @FXML
    private Label presidente;

    @FXML
    private Label vice;

    @FXML
    private Label resultado;

    @FXML
    private ImageView logoImageView;

    private String matricula;
    private Candidato[] listaDeCandidatos;

    private Candidato nulo = new Candidato("Nulo", "", "", "", 0);
    private Candidato branco = new Candidato("Branco", "", "", "", 0);

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @FXML
    public void initialize() {
        listaDeCandidatos = new Candidato[2];
        listaDeCandidatos[0] = new Candidato("Lucas Gonzaga", "PL", "Jonathan Freitas", "img/testeL", 24);
        listaDeCandidatos[1] = new Candidato("Enzo Gabriela", "PT", "Kaike Desaparecido.", "img/testeB", 69);

        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
    }

    public boolean verificarStatusVoto() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "SELECT status FROM usuarios WHERE usuario = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, matricula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    return status; // Retorna true se o usuário já votou
                } else {
                    return false; // Se não encontrou o usuário, considera como não votou
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void encontrarCandidato(int numero, String resultadoVoto) {
        boolean candidatoEncontrado = false;

        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == numero) {
                System.out.printf("O valor corresponde à %s\n", candidato.getNome());
                chapa.setText("CHAPA: " + candidato.getPartido());
                presidente.setText("PRESIDENTE: " + candidato.getNome());
                vice.setText("VICE: " + candidato.getVice());
                resultado.setText(resultadoVoto);
                candidatoEncontrado = true;

                // Adicionar o voto na coluna 'voto' no banco de dados
                adicionarVotoNoBanco(matricula, numero);

                // Adicionar a lógica para alterar a imagem aqui
                break;
            }
        }

        if (!candidatoEncontrado) {
            resultado.setText("NULO");
            chapa.setText("");
            presidente.setText("");
            vice.setText("");

            if (resultado.getText().equals("NULO")) {
                // Adicionar o voto nulo no banco de dados
                adicionarVotoNoBanco(matricula, 100); // 100 pode ser um valor que representa o voto nulo
            } else if (resultado.getText().equals("BRANCO")) {
                branco.somaVotos();
                System.out.printf("Voto em branco registrado -> %d\n", branco.getVotos());
                // Adicionar o voto em branco no banco de dados
                adicionarVotoNoBanco(matricula, 101); // 101 pode ser um valor que representa o voto em branco
            } else {
                System.out.println("Nenhum candidato corresponde ao número informado");
            }

        }
    }

    private void votarCandidato(int numero, String registration) {

        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == numero) {
                System.out.printf("Voto computado para %s\n", candidato.getNome());
                candidato.somaVotos();
                System.out.printf("%s recebeu: %d votos\n", candidato.getNome(), candidato.getVotos());

                // Atualizar o campo 'status' no banco de dados para indicar que o usuário votou
                atualizarStatusNoBanco(registration);

                // Adicionar o voto na coluna 'voto' no banco de dados
                adicionarVotoNoBanco(registration, numero);

                break;
            }
        }
    }

    private void adicionarVotoNoBanco(String registration, int numero) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "UPDATE usuarios SET status = 1, voto = ? WHERE usuario = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, numero);
            stmt.setString(2, registration);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void atualizarStatusNoBanco(String registration) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/students";
        String dbUser = "root";
        String dbPassword = "";
        String query = "UPDATE usuarios SET status = 1 WHERE usuario = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, registration);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTeclaNumerica(ActionEvent event) {
        System.out.println("Pressionado");

        if (resultado.getText().equals(".")) {
            resultado.setText("");
        }

        Button button = (Button) event.getSource();

        if (resultado.getText() != null && resultado.getText().length() < 2) {
            resultado.setText(resultado.getText() + button.getText());
            if (resultado.getText().length() == 2) {
                int numvoto = Integer.parseInt(resultado.getText());
                System.out.printf("%d\n", numvoto);
                encontrarCandidato(numvoto, resultado.getText());
            }
        }
    }

    @FXML
    private void handleBranco() {
        System.out.println("Branco Pressionado");
        resultado.setText("BRANCO");
        chapa.setText("");
        presidente.setText("");
        vice.setText("");
    }

    @FXML
    private void handleCorrige() {
        System.out.println("Corrige Pressionado");
        resultado.setText("");
        chapa.setText("CHAPA: ");
        presidente.setText("PRESIDENTE: ");
        vice.setText("VICE: ");
    }

    @FXML
    private void handleConfirma() {
        System.out.println("Confirma Pressionado");
        int numvoto = 0;
        if (resultado.getText().matches("\\d+") && matricula != null) {
            numvoto = Integer.parseInt(resultado.getText());
            votarCandidato(numvoto, matricula);
        }else if (resultado.getText().equals("BRANCO")) {
            // Adicionar o voto em branco no banco de dados
            adicionarVotoNoBanco(matricula, 101); // 101 pode ser um valor que representa o voto em branco
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-fim.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Voto Computado");

            Stage loginStage = (Stage) confirma.getScene().getWindow();
            loginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
