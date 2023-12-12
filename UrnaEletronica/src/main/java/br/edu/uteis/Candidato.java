package br.edu.uteis;

public class Candidato {

    protected String Nome;
    protected String Chapa;
    protected String Vice;
    protected String Imagem;
    protected int Numero;
    protected int Votos;

    public Candidato(String nome, String chapa, String vice, String Imagem, int numero) {
        this.Nome = nome;
        this.Chapa = chapa;
        this.Vice = vice;
        this.Numero = numero;
        this.Imagem = Imagem;
        this.Votos = 0;
    }

    public Candidato() {
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

    public int somaVotos() {
        this.Votos += 1;
        return Votos;
    }

}
