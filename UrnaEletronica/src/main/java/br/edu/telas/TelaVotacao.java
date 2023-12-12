package br.edu.telas;

import br.edu.uteis.Candidato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import br.edu.uteis.Candidato;
import java.util.ArrayList;
import java.util.List;

public class TelaVotacao {

    @FXML
    private Label chapa;

    @FXML
    private Label presidente;

    @FXML
    private Label vice;

    @FXML
    private Label resultado;

    @FXML
    private ImageView foto;

    Candidato candidato1 = new Candidato("Luiz Inácio", "PT", "Taveira", 13);
    Candidato candidato2 = new Candidato("Jair Bolsonaro", "PL", "Lucas G.", 22);

    Candidato[] listaDeCandidatos;

    {
        listaDeCandidatos = new Candidato[2];
    }

    private Candidato encontrarCandidato(int numero) {
        listaDeCandidatos[0] = candidato1;
        listaDeCandidatos[1] = candidato2;
        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == numero) {
                return candidato;
            }
        }
        return null;  // Retorne null se o candidato não for encontrado
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
        if (resultado.getText().length() < 2) {
            // Adiciona o texto do botão ao texto existente em 'resultado'
            resultado.setText(resultado.getText() + button.getText());
        } else {
            int numvoto = Integer.parseInt(resultado.getText());
        }
    }



    @FXML
    private void handleBranco() {
        // Imprime no console indicando que o usuário escolheu votar em branco
        System.out.println("Voto branco");

        // Define o texto exibido no componente 'resultado' como "BRANCO"
        resultado.setText("BRANCO");
    }



    @FXML
    private void handleCorrige() {
        // Imprime no console indicando que o usuário optou por corrigir a escolha de candidato
        System.out.println("Outro candidato");

        // Limpa o texto exibido no componente 'resultado'
        resultado.setText("");
    }



    @FXML
    private void handleConfirma() {
        // Imprime no console uma mensagem indicando a confirmação da escolha
        System.out.println("Por favor funciona");
    }


}