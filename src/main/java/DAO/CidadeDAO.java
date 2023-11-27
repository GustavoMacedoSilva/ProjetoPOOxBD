package DAO;
import Model.Cidade;

import java.sql.SQLException;
import java.util.ArrayList;
public class CidadeDAO extends ConnectionDAO{
    boolean sucesso = false;


    //INSERT
    public boolean insertCidade(Cidade cidade){
        connectToDB();
        String sql = "Insert INTO cidade (nome, nome_convencao) values (?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cidade.getNome());
            pst.setString(2, cidade.getNome_convencao());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc){
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
    public boolean updateCidade(String nome, Cidade cidade) {
        connectToDB();
        String sql = "UPDATE cidade SET nome=?, nome_convencao=? where nome=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cidade.getNome());
            pst.setString(2, cidade.getNome_convencao());
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
    public boolean deleteCidade(String nome) {
        connectToDB();
        String sql = "DELETE FROM cidade where nome=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
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
    public ArrayList<Cidade> selectCidade() {
        ArrayList<Cidade> users = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM cidade";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de cidades: ");

            while (rs.next()) {

                Cidade cidadeAux = new Cidade(rs.getString("nome"),rs.getString("nome_convencao"));

                System.out.println("nome = " + cidadeAux.getNome());
                System.out.println("nome_convencao = " + cidadeAux.getNome_convencao());
                System.out.println("--------------------------------");

                users.add(cidadeAux);
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
