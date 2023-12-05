package br.edu.principal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @FXML
    private void handletecla1() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "1");
    }

    @FXML
    private void handletecla2() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "2");
    }

    @FXML
    private void handletecla3() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "3");
    }

    @FXML
    private void handletecla4() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "4");
    }

    @FXML
    private void handletecla5() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "5");
    }

    @FXML
    private void handletecla6() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "6");
    }

    @FXML
    private void handletecla7() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "7");
    }

    @FXML
    private void handletecla8() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "8");
    }

    @FXML
    private void handletecla9() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "9");
    }

    @FXML
    private void handletecla0() {
        System.out.print("Pressionado");
        if(resultado.getText().equals(".")) {
            resultado.setText("");
        }
        resultado.setText(resultado.getText() + "0");
    }

    @FXML
    private void handleBranco() {
        resultado.setText("BRANCO");
    }

    @FXML
    private void handleCorrige() {
        resultado.setText("");
    }

    @FXML
    private void handleConfirma() {
        System.out.print("Por favor funciona");
    }
}