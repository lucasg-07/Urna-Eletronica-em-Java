package br.edu.uteis;

public class Candidato {

    public String Nome;
    public String Chapa;
    public String Vice;
    public int Numero;
    public int votos;

    public Candidato(String nome, String chapa, String vice, int numero) {
        this.Nome = nome;
        this.Chapa = chapa;
        this.Vice = vice;
        this.Numero = numero;
        this.votos = 0;
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

    public int getNumero() {
        return Numero;
    }

    public int getVotos() {
        return votos;
    }
}
