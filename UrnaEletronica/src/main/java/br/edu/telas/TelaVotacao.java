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
import br.edu.uteis.Candidato;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    private Label semCandidato;

    @FXML
    private ImageView foto;

    @FXML
    private ImageView logoImageView;


    Candidato candidato1 = new Candidato("Luiz Inácio", "PT", "Taveira", "img/testeL", 13);
    Candidato candidato2 = new Candidato("Jair Bolsonaro", "PL", "Lucas G.", "img/testeB", 22);
    Candidato nulo = new Candidato();
    Candidato branco = new Candidato();

    Candidato[] listaDeCandidatos;

    {
        listaDeCandidatos = new Candidato[2];
    }

    @FXML
    public void initialize() {
        listaDeCandidatos = new Candidato[2];
        listaDeCandidatos[0] = candidato1;
        listaDeCandidatos[1] = candidato2;

        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
    }


    private void encontrarCandidato(int Numero, String Resultado) {
        boolean candidatoEncontrado = false;

        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == Numero) {
                System.out.printf("O valor corresponde à %s\n", candidato.getNome());
                chapa.setText("CHAPA: " + candidato.getPartido());
                presidente.setText("PRESIDENTE: " + candidato.getNome());
                vice.setText("VICE: " + candidato.getVice());
                resultado.setText(Resultado);
                candidatoEncontrado = true;
                break;
            }
        }

        if (!candidatoEncontrado) {
            System.out.println("O valor não corresponde a nenhum candidato");
            resultado.setText("NULO");
            chapa.setText("");
            presidente.setText("");
            vice.setText("");
        }
    }

    private void votarCandidato(int Numero, String ResultadoVoto) {
        boolean candidatoEncontrado = false;

        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == Numero) {
                System.out.printf("Voto computado para %s\n", candidato.getNome());
                candidato.somaVotos();
                System.out.printf("%s recebeu: %d votos\n", candidato.getNome(), candidato.getVotos());
                candidatoEncontrado = true;
                break;
            }
        }

        if (!candidatoEncontrado) {
            if (ResultadoVoto.equals("NULO")) {
                nulo.somaVotos();
                System.out.printf("Voto nulo registrado -> %d\n", nulo.getVotos());
            } else if (ResultadoVoto.equals("BRANCO")) {
                branco.somaVotos();
                System.out.printf("Voto em branco registrado -> %d\n", branco.getVotos());
            } else {
                System.out.println("Nenhum candidato corresponde ao número informado");
            }
        }
    }

    @FXML
    private void handleTeclaNumerica(ActionEvent event) {
        // Imprime "Pressionado" no console quando este método é chamado
        System.out.println("Pressionado");

        // Verifica se o texto atual em 'resultado' é um ponto, se sim, limpa o texto
        if (resultado.getText().equals(".")) {
            resultado.setText("");
        }

        // Obtém o botão que disparou o evento
        Button button = (Button) event.getSource();

        // Verifica se o tamanho atual do texto em 'resultado' é menor que 2 (considerando '.' como um caractere)
        if (resultado.getText() != null && resultado.getText().length() < 2) {
            // Adiciona o texto do botão ao texto existente em 'resultado'
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
        // Imprime no console indicando que o usuário escolheu votar em branco
        System.out.println("Branco Pressionado");

        // Define o texto exibido no componente 'resultado' como "BRANCO"
        resultado.setText("BRANCO");
        chapa.setText("");
        presidente.setText("");
        vice.setText("");
    }


    @FXML
    private void handleCorrige() {
        // Imprime no console indicando que o usuário optou por corrigir a escolha de candidato
        System.out.println("Corrige Pressionado");
        // Limpa o texto exibido nos componentes 'resultado', 'chapa', 'presidente', 'vice'
        resultado.setText("");
        chapa.setText("CHAPA: ");
        presidente.setText("PRESIDENTE: ");
        vice.setText("VICE: ");
    }


    @FXML
    private void handleConfirma() {
        // Imprime no console uma mensagem indicando a confirmação da escolha
        System.out.println("Confirma Pressionado");
        // computar novos votos
        int numvoto = 0;
        if (resultado.getText().matches("\\d+")) {
            numvoto = Integer.parseInt(resultado.getText());
        }
        votarCandidato(numvoto, resultado.getText());
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