package Model;

public class Candidato {

    private int id;

    private String nome;

    private String email;

    private String nome_convecao;

    public Candidato(String nome, String email, String nome_convecao){
        this.nome = nome;
        this.email = email;
        this.nome_convecao = nome_convecao;
    }

    public String getNome() {
        return nome;
    }


    public String getEmail() {
        return email;
    }

    public String getNome_convecao() {
        return nome_convecao;
    }
}
