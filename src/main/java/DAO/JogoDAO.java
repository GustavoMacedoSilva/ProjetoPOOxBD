package DAO;

import Model.Jogo;

import java.sql.SQLException;
import java.util.ArrayList;
public class JogoDAO extends ConnectionDAO{
    boolean sucesso = false;

    //INSERT
    public boolean insertJogo(Jogo jogo) {

        connectToDB();

        String sql = "INSERT INTO jogo (nome,genero,plataforma) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jogo.getNome());
            pst.setString(2, jogo.getGenero());
            pst.setString(3,jogo.getPlataforma());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    public boolean updateJogo(int id, Jogo jogo) {
        connectToDB();
        String sql = "UPDATE jogo SET nome=?, genero=?, plataforma=? where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jogo.getNome());
            pst.setString(2, jogo.getGenero());
            pst.setString(3, jogo.getPlataforma());
            pst.setInt(4,id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteJogo(int id) {
        connectToDB();
        String sql = "DELETE FROM jogo where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public ArrayList<Jogo> selectJogo() {
        ArrayList<Jogo> users = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM jogo";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de jogos: ");

            while (rs.next()) {

                Jogo jogoAux = new Jogo(rs.getString("nome"),rs.getString("genero"), rs.getString("plataforma"));

                System.out.println("nome = " + jogoAux.getNome());
                System.out.println("genero = " + jogoAux.getGenero());
                System.out.println("plataforma = " + jogoAux.getPlataforma());
                System.out.println("--------------------------------");

                users.add(jogoAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return users;
    }
}
