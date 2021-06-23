package model.dao;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Equipamento;
public class EquipamentoDAO {
    public void create(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO equipamento(id,sigla,modelo,marca,alimentador,subestacao,"
                    + "potencia,tensaoaliment,descricao,numero,bairro,complemento,coordenada,"
                    + "codigolocalizacao,cidade,estado,cep)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,e.getId());
            stmt.setString(2,e.getSigla());
            stmt.setString(3,e.getModelo());
            stmt.setString(4,e.getMarca());
            stmt.setString(5,e.getAlimentador());
            stmt.setString(6,e.getSubestacao());
            stmt.setInt(7,e.getPotencia());
            stmt.setInt(8,e.getTensaoaliment());
            stmt.setString(9,e.getDescricao());
            stmt.setString(10,e.getRua());
            stmt.setString(11,e.getBairro());
            stmt.setString(12,e.getComplemento());
            stmt.setString(13,e.getCoordenada());
            stmt.setString(14,e.getCodigolocalizacao());
            stmt.setString(15,e.getCidade());
            stmt.setString(16,e.getEstado());
            stmt.setString(17,e.getCep());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
public List<Equipamento> read(){
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Equipamento> equipamentos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM equipamento");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setId(rs.getString("id"));
                equipamento.setSigla(rs.getString("sigla"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setMarca(rs.getString("marca"));
                equipamento.setAlimentador(rs.getString("alimentador"));
                equipamento.setSubestacao(rs.getString("subestacao"));
                equipamento.setPotencia(rs.getInt("potencia"));
                equipamento.setTensaoaliment(rs.getInt("tensaoaliment"));
                equipamento.setDescricao(rs.getString("descricao"));
                equipamento.setRua(rs.getString("rua"));
                equipamento.setNumero(rs.getInt("numero"));
                equipamento.setBairro(rs.getString("bairro"));
                equipamento.setComplemento(rs.getString("complemento"));
                equipamento.setCoordenada(rs.getString("coordenada"));
                equipamento.setCodigolocalizacao(rs.getString("codigolocalizacao"));
                equipamento.setCidade(rs.getString("cidade"));
                equipamento.setEstado(rs.getString("estado"));
                equipamento.setCep(rs.getString("cep"));
                equipamentos.add(equipamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return equipamentos;
}
    public List<Equipamento> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Equipamento> equipamentos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT sigla, potencia, alimentador, subestacao FROM equipamento "
                    + "WHERE id LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento e = new Equipamento();
                e.setSigla(rs.getString("sigla"));
                e.setPotencia(rs.getInt("potencia"));
                e.setAlimentador(rs.getString("alimentador"));
                e.setSubestacao(rs.getString("subestacao"));
                equipamentos.add(e);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return equipamentos;
    }
public void salvar(List<String> linhas) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO equipamento(id, sigla, marca, modelo, potencia, numfases, tensao, descricao,"
                + "alimentador, subestacao, rua, bairro, complemento, coordenada, codigolocalizacao, cidade,"
                + " estado, cep)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        con.setAutoCommit(false);
        PreparedStatement psmt = con.prepareStatement(sql);
        int contador = 0;
        for (String linha : linhas) {
            String[] colunas = linha.split(";");
            psmt.setString(1, colunas[0]);
            psmt.setString(2, colunas[1]);
            psmt.setString(3, colunas[2]);
            psmt.setString(4, colunas[3]);
            psmt.setString(5, colunas[4]);
            psmt.setString(6, colunas[5]);
            psmt.setString(7, colunas[6]);
            psmt.setString(8, colunas[7]);
            psmt.setString(9, colunas[8]);
            psmt.setString(10, colunas[9]);
            psmt.setString(11, colunas[10]);
            psmt.setString(12, colunas[11]);
            psmt.setString(13, colunas[12]);
            psmt.setString(14, colunas[13]);
            psmt.setString(15, colunas[14]);
            psmt.setString(16, colunas[15]);
            psmt.setString(17, colunas[16]);
            psmt.setString(18, colunas[17]);
            psmt.addBatch();
            contador++;
            if (contador == 1000) {
                psmt.executeBatch();
                con.commit();
                contador = 0;
            }
        }
        psmt.executeBatch();
        con.commit();
        con.close();
    }
 public void update(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE equipamento SET sigla=?,modelo=?,marca=?,alimentador=?,"
                    + "subestacao=?,"
                    + "potencia=?,tensaoaliment=?,descricao=? WHERE id=?");
            stmt.setString(1,e.getSigla());
            stmt.setString(2,e.getModelo());
            stmt.setString(3,e.getMarca());
            stmt.setString(4,e.getAlimentador());
            stmt.setString(5,e.getSubestacao());
            stmt.setInt(6,e.getPotencia());
            stmt.setInt(7,e.getTensaoaliment());
            stmt.setString(8,e.getDescricao());
            stmt.setString(9,e.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
 public void updatelocal(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE equipamento SET rua=?,numero=?,bairro=?,complemento=?,"
                    + "coordenada=?,"
                    + "codigolocalizacao=?,cidade=?,estado=?,cep=? WHERE id=?");
            stmt.setString(1,e.getRua());
            stmt.setInt(2,e.getNumero());
            stmt.setString(3,e.getBairro());
            stmt.setString(4,e.getComplemento());
            stmt.setString(5,e.getCoordenada());
            stmt.setString(6,e.getCodigolocalizacao());
            stmt.setString(7,e.getCidade());
            stmt.setString(8,e.getEstado());
            stmt.setString(9,e.getCep());
            stmt.setString(10,e.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
 public void delete(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM equipamento WHERE id=?");
            stmt.setString(1,e.getId());                       
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
 public void deletebanco(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("TRUNCATE TABLE equipamento");
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
 public int selecionar(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int pot = 0;
        try {
            stmt = con.prepareStatement("SELECT potencia AS pot FROM equipamento WHERE id=?");
            stmt.setString(1,e.getId());                       
            rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                pot = rs.getInt("pot");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return pot;
    }
 public int NumFases(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int fases = 0;
        try {
            stmt = con.prepareStatement("SELECT numfases AS num FROM equipamento WHERE id=?");
            stmt.setString(1,e.getId());                       
            rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                fases = rs.getInt("num");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return fases;
    }
}
