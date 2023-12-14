package br.edu.telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaContagem {
    @FXML
    private ImageView logoImageView;


    @FXML
    private void initialize() {

        // Carregar a imagem durante a inicialização
        Image logoImage = new Image(getClass().getResource("/imagens/iflogo.png").toExternalForm());
        logoImageView.setImage(logoImage);
    }

    public void handleSobre(ActionEvent actionEvent) {
    }

    public void handleAjuda(ActionEvent actionEvent) {
    }

    public void handleVoltar(ActionEvent actionEvent) {
    }
}
