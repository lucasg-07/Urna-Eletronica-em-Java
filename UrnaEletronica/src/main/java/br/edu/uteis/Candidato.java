package br.edu.uteis;

public class Candidato {

    public String Nome;
    public String Chapa;
    public String Vice;
    public String Imagem;
    public int Numero;
    public int Votos;

    public Candidato(String nome, String chapa, String vice, String Imagem, int numero) {
        this.Nome = nome;
        this.Chapa = chapa;
        this.Vice = vice;
        this.Numero = numero;
        this.Imagem = Imagem;
        this.Votos = 0;
    }

    public Candidato(String nome) {
        this.Nome = nome;
        this.Votos = 0;
    }


    public String getNome() {
        return Nome;
    }

    public String getChapa() {
        return Chapa;
    }

    public String getVice() {
        return Vice;
    }

    public String getImagem() {
        return Imagem;
    }

    public int getNumero() {
        return Numero;
    }

    public int getVotos() {
        return Votos;
    }

}
