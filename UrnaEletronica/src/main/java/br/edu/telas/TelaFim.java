package br.edu.telas;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class TelaFim {

    private void tocarMusica() {
        // Toca o som da urna
        playUrnaSound();

        // Fecha a tela final da urna
        closeTelaFinal();
    }

    private static void playUrnaSound() {
        try {
            URL soundFile = TelaFim.class.getResource("/caminho/do/seu/arquivo/urna_sound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeTelaFinal() {
        // Implemente o código para fechar a tela final da urna
    }
    }

}
