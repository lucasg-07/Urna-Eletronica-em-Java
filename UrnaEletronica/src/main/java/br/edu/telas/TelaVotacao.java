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
    @FXML
    private ImageView fotoCandidato;

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
        listaDeCandidatos[0] = new Candidato("Lucas Gonzaga", "PL (Partido do Lapisco)", "Jonathan Freitas", "/imagens/lucas.jpg", 24);
        listaDeCandidatos[1] = new Candidato("Enzo Gabriela", "PCL (Partido do Contra o Lapisco)", "Kaike Desaparecido", "/imagens/enzo.jpg", 69);

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

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("status");
                } else {
                    return false;
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
                chapa.setText("CHAPA: " + candidato.getPartido());
                presidente.setText("PRESIDENTE: " + candidato.getNome());
                vice.setText("VICE: " + candidato.getVice());
                resultado.setText(resultadoVoto);
                candidatoEncontrado = true;

                exibirImagemCandidato(numero);
                break;
            }
        }

        if (!candidatoEncontrado) {
            resultado.setText("NULO");
            chapa.setText("");
            presidente.setText("");
            vice.setText("");
        }
    }

    private void exibirImagemCandidato(int numero) {
        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == numero) {
                String imagePath = candidato.getImagem();

                if (imagePath != null) {
                    Image imagemCandidato = new Image(getClass().getResource(imagePath).toExternalForm());
                    fotoCandidato.setImage(imagemCandidato);
                }
                break;
            }
        }
    }

    private void votarCandidato(int numero, String registration) {
        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == numero) {
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
        String query = "UPDATE usuarios SET voto = ?, status = 1 WHERE usuario = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, numero);
            stmt.setString(2, registration);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTeclaNumerica(ActionEvent event) {
        if (resultado.getText().equals(".")) {
            resultado.setText("");
        }

        Button button = (Button) event.getSource();

        if (resultado.getText() != null && resultado.getText().length() < 2) {
            resultado.setText(resultado.getText() + button.getText());
            if (resultado.getText().length() == 2) {
                int numvoto = Integer.parseInt(resultado.getText());
                encontrarCandidato(numvoto, resultado.getText());
            }
        }
    }

    @FXML
    private void handleBranco() {
        resultado.setText("BRANCO");
        chapa.setText("");
        presidente.setText("");
        vice.setText("");
        fotoCandidato.setImage(null);
    }

    @FXML
    private void handleCorrige() {
        resultado.setText("");
        chapa.setText("PARTIDO: ");
        presidente.setText("PRESIDENTE: ");
        vice.setText("VICE: ");
        fotoCandidato.setImage(null);
    }

    @FXML
    private void handleConfirma() {
        int numvoto = 0;

        if (resultado.getText().matches("\\d+") && matricula != null) {
            numvoto = Integer.parseInt(resultado.getText());
            votarCandidato(numvoto, matricula);
        } else if (resultado.getText().equals("BRANCO")) {
            // Adicionar o voto em branco no banco de dados
            adicionarVotoNoBanco(matricula, 101); // 101 pode ser um valor que representa o voto em branco
        } else if (resultado.getText().equals("NULO")) {
            // Adicionar o voto nulo no banco de dados
            adicionarVotoNoBanco(matricula, 100); // 100 pode ser um valor que representa o voto nulo
        } else {
            resultado.setText("Escolha inválida");
            chapa.setText("");
            presidente.setText("");
            vice.setText("");
            return; // Sai do método se a escolha não for válida
        }



        // Atualiza o status do voto
        atualizarStatusNoBanco(matricula);

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
}
