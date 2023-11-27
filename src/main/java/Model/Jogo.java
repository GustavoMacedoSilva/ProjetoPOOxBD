package Model;

public class Jogo {

    private int id;
    private String nome;
    private String genero;
    private String plataforma;

    public Jogo(String nome, String genero, String plataforma){
        this.nome = nome;
        this.genero = genero;
        this.plataforma = plataforma;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getPlataforma() {
        return plataforma;
    }
}
