package br.edu.telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;

public class TelaAjuda {

    @FXML
    private Button sairAjuda;
    @FXML
    private Button sobreAjuda;
    @FXML
    private ImageView logoImageView;
    @FXML
    private Text ajudaTexto;


    @FXML
    private void initialize() {
        String instrucoesDeUso = "1. Instruções de Uso:\n" +
                "  Na tela inicial, preencha o número de matrícula da instituição e a senha. Caso não tenha cadastro, clique em “Não possui cadastro? Clique aqui”. A seguir, abrirá a página de cadastro solicitando o nome, matrícula e senha. Após preenchido, clique em “Cadastrar”. Volte para a tela inicial, preencha os campos requeridos e você já pode votar. Na tela de votação, clique no número do candidato, confira na tela se as informações são do candidato desejado e aperte em “CONFIRMA”. Caso deseje corrigir o número, aperte no botão “CORRIGE”. Em seguida, digite o número correto, verifique se as informações estão corretas e aperte “CONFIRME”. Se deseja votar em branco, aperte “BRANCO” e em seguida “CONFIRME”.";

        String candidatos = "2. Candidatos\n" +
                "   Na urna, caso o número não represente nenhum candidato, será considerado NULO.\n" +
                "   Apresentamos os números dos candidatos e os partidos.\n" +
                "   Lucas Gonzaga (24) – Partido do Lapisco.\n" +
                "   Enzo Gabriela (69) – Partido Contra o Lapisco.";

        String suporte = "3. Contato de Suporte:\n" +
                "   Em caso de problemas na urna, dirijam-se ao responsável presente no local da votação ou ao mesário, para que as devidas providências sejam tomadas.";

        ajudaTexto.setText(instrucoesDeUso + "\n\n" + candidatos + "\n\n" + suporte);

        // Aplicar negrito ao título "1. Instruções de Uso" e "2. Candidatos"
        ajudaTexto.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
    }


    @FXML
    private void handleSobre() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-sobre.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sobre");

            Stage loginStage = (Stage) sobreAjuda.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSair() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/principal/tela-login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");

            Stage loginStage = (Stage) sairAjuda.getScene().getWindow();
            loginStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
