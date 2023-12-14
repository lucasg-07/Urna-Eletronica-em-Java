package br.edu.uteis;

public class Candidato {

    private String nome;
    private String partido;
    private String vice;
    private String imagem;
    private int numero;
    private int votos;

    public Candidato(String nome, String partido, String vice, String imagem, int numero) {
        this.nome = nome;
        this.partido = partido;
        this.vice = vice;
        this.imagem = imagem;
        this.numero = numero;
        this.votos = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public String getVice() {
        return vice;
    }

    public String getImagem() {
        return imagem;
    }

    public int getNumero() {
        return numero;
    }

    public int getVotos() {
        return votos;
    }

}
