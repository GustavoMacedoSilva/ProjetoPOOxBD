package Model;

public class Cidade {

    private String nome;

    private String nome_convencao;

    public Cidade(String nome, String nome_convencao){
        this.nome = nome;
        this.nome_convencao = nome_convencao;
    }

    public String getNome() {
        return nome;
    }

    public String getNome_convencao() {
        return nome_convencao;
    }
}
