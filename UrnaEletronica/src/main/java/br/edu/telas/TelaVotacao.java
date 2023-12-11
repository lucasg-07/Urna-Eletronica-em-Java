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

    Candidato candidato1 = new Candidato("Luiz Inácio", "PT", "Taveira", "img/testeL", 13);
    Candidato candidato2 = new Candidato("Jair Bolsonaro", "PL", "Lucas G.", "img/testeB", 22);

    Candidato[] listaDeCandidatos;

    {
        listaDeCandidatos = new Candidato[2];
    }

    private void encontrarCandidato(int Numero, String Resultado) {
        listaDeCandidatos[0] = candidato1;
        listaDeCandidatos[1] = candidato2;
        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == Numero) {
                System.out.printf("O valor corresponde à %s\n", candidato.Nome);
                chapa.setText("CHAPA: " + candidato.Chapa);
                presidente.setText("PRESIDENTE: " + candidato.Nome);
                vice.setText("VICE: " + candidato.Vice);
                resultado.setText(Resultado);
                break;
            } else {
                System.out.printf("O valor não corresponde à %s\n", candidato.Nome);
                resultado.setText("NULO");
                chapa.setText("");
                presidente.setText("");
                vice.setText("");
            }
        }
    }

    private void votarCandidato(int Numero) {
        listaDeCandidatos[0] = candidato1;
        listaDeCandidatos[1] = candidato2;
        for (Candidato candidato : listaDeCandidatos) {
            if (candidato.getNumero() == Numero) {
                System.out.printf("Voto computado para %s\n", candidato.Nome);
                candidato.Votos += 1;
                System.out.printf("%s recebeu: %d votos\n", candidato.Nome, candidato.Votos);
                break;
            } else {
                System.out.printf("%s recebeu: %d votos\n", candidato.Nome, candidato.Votos);
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
        if (resultado.getText().length() < 2) {
            // Adiciona o texto do botão ao texto existente em 'resultado'
            resultado.setText(resultado.getText() + button.getText());
            if (resultado.getText().length() == 2) {
                System.out.printf("Por favor Funfa\n");
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
        int numvoto = Integer.parseInt(resultado.getText());
        votarCandidato(numvoto);

    }


}