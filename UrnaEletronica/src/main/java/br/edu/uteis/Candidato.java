package br.edu.uteis;

public class Candidato {

    protected String Nome;
    protected String Partido;
    protected String Vice;
    protected String Imagem;
    protected int Numero;
    protected int Votos;

    public Candidato(String nome, String partido, String vice, String Imagem, int numero) {
        this.Nome = nome;
        this.Partido = partido;
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

    public String getPartido() {
        return Partido;
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
