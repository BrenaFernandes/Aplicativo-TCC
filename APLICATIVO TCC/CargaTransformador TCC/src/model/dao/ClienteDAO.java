package model.dao;
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
import model.bean.Cliente;
import model.bean.Demanda;
import model.bean.Equipamento;
public class ClienteDAO {
    public void create(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO cliente(medidor,contrato,nome,rg,cpf,telefone,"
                    + "endereco,numero,demandacontratada,classe,"
                    + "unidadegeradora,alimentacao,tensao,poste,transformador,bairro,complemento,tipo)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, c.getMedidor());
            stmt.setString(2, c.getContrato());
            stmt.setString(3, c.getNome());
            stmt.setInt(4, c.getRg());
            stmt.setInt(5, c.getCpf());
            stmt.setInt(6, c.getTelefone());
            stmt.setString(7, c.getEndereco());
            stmt.setInt(8, c.getNumero());
            stmt.setDouble(9, c.getDemandacontratada());
            stmt.setString(10, c.getClasse());
            stmt.setString(11, c.getUnidadegeradora());
            stmt.setString(12, c.getAlimentacao());
            stmt.setInt(13, c.getTensao());
            stmt.setString(14, c.getPoste());
            stmt.setString(15, c.getTransformador());
            stmt.setString(16, c.getBairro());
            stmt.setString(17, c.getComplemento());
            stmt.setString(18, c.getTipo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");        
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void salvar(List<String> linhas) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO cliente(medidor, contrato, nome, telefone, rg, cpf, endereco, numero, "
                + "bairro,demandacontratada, transformador, poste, unidadegeradora, tensao, alimentacao,"
                + " classe, tipo)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
    public List<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setMedidor(rs.getString("medidor"));
                cliente.setContrato(rs.getString("contrato"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getInt("rg"));
                cliente.setCpf(rs.getInt("cpf"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setDemandacontratada(rs.getDouble("demandacontratada"));
                cliente.setClasse(rs.getString("classe"));
                cliente.setUnidadegeradora(rs.getString("unidadegeradora"));
                cliente.setAlimentacao(rs.getString("alimentacao"));
                cliente.setTensao(rs.getInt("tensao"));
                cliente.setPoste(rs.getString("poste"));
                cliente.setTransformador(rs.getString("transformador"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
     public List<Cliente> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT nome, contrato, classe, alimentacao, transformador FROM "
                    + "cliente WHERE nome LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setContrato(rs.getString("contrato"));
                c.setClasse(rs.getString("classe"));
                c.setAlimentacao(rs.getString("alimentacao"));
                c.setTransformador(rs.getString("transformador"));
                clientes.add(c);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
    public List<Cliente> readBairro(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente GROUP BY bairro");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setMedidor(rs.getString("medidor"));
                cliente.setContrato(rs.getString("contrato"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getInt("rg"));
                cliente.setCpf(rs.getInt("cpf"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setDemandacontratada(rs.getDouble("demandacontratada"));
                cliente.setClasse(rs.getString("classe"));
                cliente.setUnidadegeradora(rs.getString("unidadegeradora"));
                cliente.setAlimentacao(rs.getString("alimentacao"));
                cliente.setTensao(rs.getInt("tensao"));
                cliente.setPoste(rs.getString("poste"));
                cliente.setTransformador(rs.getString("transformador"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
    public void update(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE cliente SET contrato=?,nome=?,rg=?,cpf=?,telefone=?,endereco=?,"
                    + "numero=?,demandacontratada=?,classe=?,"
                    + "unidadegeradora=?,alimentacao=?,tensao=?,poste=? WHERE medidor=?");
            stmt.setString(1, c.getContrato());
            stmt.setString(2, c.getNome());
            stmt.setInt(3, c.getRg());
            stmt.setInt(4, c.getCpf());
            stmt.setInt(5, c.getTelefone());
            stmt.setString(6, c.getEndereco());
            stmt.setInt(7, c.getNumero());
            stmt.setDouble(8, c.getDemandacontratada());
            stmt.setString(9, c.getClasse());
            stmt.setString(10, c.getUnidadegeradora());
            stmt.setString(11, c.getAlimentacao());
            stmt.setInt(12, c.getTensao());
            stmt.setString(13, c.getPoste());
            stmt.setString(14, c.getMedidor());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");        
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void delete(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE contrato=?");
            stmt.setString(1, c.getContrato());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");        
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void deletebanco(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("TRUNCATE TABLE cliente");
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");        
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Cliente> consultafasea(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientesa = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT medidor,nome,demandacontratada,classe,poste FROM cliente WHERE"
                    + " alimentacao= 'Monofasico A'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente clientea = new Cliente();
                clientea.setMedidor(rs.getString("medidor"));
                clientea.setNome(rs.getString("nome"));
                clientea.setDemandacontratada(rs.getDouble("demandacontratada"));
                clientea.setClasse(rs.getString("classe"));
                clientea.setPoste(rs.getString("poste"));
                clientesa.add(clientea);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientesa;
    }
    public List<Cliente> consultafaseb(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientesb = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT medidor,nome,demandacontratada,classe,poste FROM cliente WHERE"
                    + " alimentacao= 'Monofasico B'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente clienteb = new Cliente();
                clienteb.setMedidor(rs.getString("medidor"));
                clienteb.setNome(rs.getString("nome"));
                clienteb.setDemandacontratada(rs.getDouble("demandacontratada"));
                clienteb.setClasse(rs.getString("classe"));
                clienteb.setPoste(rs.getString("poste"));
                clientesb.add(clienteb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientesb;
    }
    public List<Cliente> consultafasec(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientesc = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT medidor,nome,demandacontratada,classe,poste FROM cliente WHERE "
                    + "alimentacao= 'Monofasico C'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente clientec = new Cliente();
                clientec.setMedidor(rs.getString("medidor"));
                clientec.setNome(rs.getString("nome"));
                clientec.setDemandacontratada(rs.getDouble("demandacontratada"));
                clientec.setClasse(rs.getString("classe"));
                clientec.setPoste(rs.getString("poste"));
                clientesc.add(clientec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientesc;
    }
    public List<Cliente> consultatrif(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientestri = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT medidor,nome,demandacontratada,classe,poste FROM cliente WHERE "
                    + "alimentacao= 'Trifasico'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente clientet = new Cliente();
                clientet.setMedidor(rs.getString("medidor"));
                clientet.setNome(rs.getString("nome"));
                clientet.setDemandacontratada(rs.getDouble("demandacontratada"));
                clientet.setClasse(rs.getString("classe"));
                clientet.setPoste(rs.getString("poste"));
                clientestri.add(clientet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientestri;
    }
  public double Somademanda(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatorio=0;
        try {
            stmt = con.prepareStatement("SELECT demandacontratada FROM cliente");
            rs = stmt.executeQuery();
            while(rs.next()){
            somatorio = somatorio + rs.getDouble("demandacontratada");
            }        
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
   return somatorio;
}
  public double carregamentoA(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatorioA=0;
        try {
            stmt = con.prepareStatement("SELECT demandacalc FROM cliente WHERE alimentacao='Monofasico A' and "
                    + "transformador=?");
            stmt.setString(1, c.getTransformador());
            rs = stmt.executeQuery();
            while(rs.next()){
            somatorioA = somatorioA + rs.getDouble("demandacalc");
            }       
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return somatorioA;
    }
  public double carregamentoB(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatorioB=0;
        try {
            stmt = con.prepareStatement("SELECT demandacalc FROM cliente WHERE alimentacao='Monofasico B' and"
                    + " transformador=?");
             stmt.setString(1, c.getTransformador());
            rs = stmt.executeQuery();
            while(rs.next()){
            somatorioB = somatorioB + rs.getDouble("demandacalc");
            }       
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return somatorioB;
    }
  public double carregamentoC(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatorioC=0;
        try {
            stmt = con.prepareStatement("SELECT demandacalc FROM cliente WHERE alimentacao='Monofasico C' "
                    + "and transformador=?");
             stmt.setString(1, c.getTransformador());
            rs = stmt.executeQuery();
            while(rs.next()){
            somatorioC = somatorioC + rs.getDouble("demandacalc");
            }       
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return somatorioC;
    }
  public List<Cliente> readcargaA(Equipamento e){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<Cliente> demandaA = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT contrato, demandacontratada FROM cliente WHERE "
                    + "alimentacao='Monofasico A' and transformador=? ORDER BY demandacontratada");
            stmt.setString(1, e.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setContrato(rs.getString("contrato"));
                cliente.setDemandacontratada(rs.getDouble("demandacontratada"));
                demandaA.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
   return demandaA;
   }
  public List<Cliente> readcargaB(Equipamento e){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<Cliente> demandaB = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT contrato, demandacontratada FROM cliente WHERE "
                    + "alimentacao='Monofasico B' and transformador=? ORDER BY demandacontratada");
            stmt.setString(1, e.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setContrato(rs.getString("contrato"));
                cliente.setDemandacontratada(rs.getDouble("demandacontratada"));
                demandaB.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
   return demandaB;
   }
  public List<Cliente> readcargaC(Equipamento e){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<Cliente> demandaC = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT contrato, demandacontratada FROM cliente WHERE "
                    + "alimentacao='Monofasico C' and transformador=? ORDER BY demandacontratada");
            stmt.setString(1, e.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setContrato(rs.getString("contrato"));
                cliente.setDemandacontratada(rs.getDouble("demandacontratada"));
                demandaC.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
   return demandaC;
   }
  public List<Cliente> readCliente(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        Cliente c = new Cliente();
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE transformador=?");
            stmt.setString(1, c.getTransformador());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setMedidor(rs.getString("medidor"));
                cliente.setContrato(rs.getString("contrato"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getInt("rg"));
                cliente.setCpf(rs.getInt("cpf"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setDemandacontratada(rs.getDouble("demandacontratada"));
                cliente.setClasse(rs.getString("classe"));
                cliente.setUnidadegeradora(rs.getString("unidadegeradora"));
                cliente.setAlimentacao(rs.getString("alimentacao"));
                cliente.setTensao(rs.getInt("tensao"));
                cliente.setPoste(rs.getString("poste"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
  public List<Cliente> readCliente(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setMedidor(rs.getString("medidor"));
                cliente.setContrato(rs.getString("contrato"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getInt("rg"));
                cliente.setCpf(rs.getInt("cpf"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setDemandacontratada(rs.getDouble("demandacontratada"));
                cliente.setClasse(rs.getString("classe"));
                cliente.setUnidadegeradora(rs.getString("unidadegeradora"));
                cliente.setAlimentacao(rs.getString("alimentacao"));
                cliente.setTensao(rs.getInt("tensao"));
                cliente.setPoste(rs.getString("poste"));
                cliente.setTransformador(rs.getString("transformador"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
public int qtdClientes(Equipamento e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         ResultSet rs = null;
        int soma=0;
        try {
            stmt = con.prepareStatement("SELECT COUNT(contrato) AS total FROM cliente WHERE transformador=?");
            stmt.setString(1, e.getId());    
            rs = stmt.executeQuery();
            while(rs.next()){
            soma = rs.getInt("total");
            }  
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return soma;
    }
public int qtdClientesA(String equipamento){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         ResultSet rs = null;
        int soma=0;
        String alimentacao = "Monofasico A";
        String trafo = equipamento;
        try {
            stmt = con.prepareStatement("SELECT COUNT(contrato) AS total FROM cliente WHERE transformador=?"
                    + " and alimentacao=?");
            stmt.setString(1, trafo);    
            stmt.setString(2, alimentacao);    
            rs = stmt.executeQuery();
            while(rs.next()){
            soma = rs.getInt("total");
            }  
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return soma;
    }
public int qtdClientesB(String equipamento){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         ResultSet rs = null;
        int soma=0;
        String alimentacao = "Monofasico B";
        String trafo = equipamento;
        try {
            stmt = con.prepareStatement("SELECT COUNT(contrato) AS total FROM cliente WHERE transformador=? "
                    + "and alimentacao=?");
            stmt.setString(1, trafo);    
            stmt.setString(2, alimentacao);    
            rs = stmt.executeQuery();
            while(rs.next()){
            soma = rs.getInt("total");
            }  
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return soma;
    }
public int qtdClientesC(String equipamento){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         ResultSet rs = null;
        int soma=0;
        String alimentacao = "Monofasico C";
        String trafo = equipamento;
        try {
            stmt = con.prepareStatement("SELECT COUNT(contrato) AS total FROM cliente WHERE transformador=? "
                    + "and alimentacao=?");
            stmt.setString(1, trafo);    
            stmt.setString(2, alimentacao);    
            rs = stmt.executeQuery();
            
            while(rs.next()){
            soma = rs.getInt("total");
            }  
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return soma;
    }
public int qtdClientestri(String equipamento){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         ResultSet rs = null;
        int soma=0;
        String alimentacao = "Trifasico";
        String trafo = equipamento;
        try {
            stmt = con.prepareStatement("SELECT COUNT(contrato) AS total FROM cliente WHERE transformador=? "
                    + "and alimentacao=?");
            stmt.setString(1, trafo);    
            stmt.setString(2, alimentacao);    
            rs = stmt.executeQuery();
            while(rs.next()){
            soma = rs.getInt("total");
            }  
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return soma;
    }
    }
    
    
    
    

    
    

