
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.cj.xdevapi.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Funcionarios;


public class FuncionariosDao implements DaoGenerica<Funcionarios>{

    private conexaoBanco conexao;
    
    public FuncionariosDao()
    {
        this.conexao = new conexaoBanco();
    }
    
    @Override
    public void inserir(Funcionarios funcionarios) {
        //string com a consulta que será executada no banco
        String sql = "INSERT INTO Funcionario (ID_Funcionario, CPF, Nome, Telefone, Usuario, Senha, FK1_Sexo, Tipo_User) VALUES (?,?,?,?,?,?,(select ID_Genero from Genero where Tipo_Genero = ?),?)";
        
        try
        {
            //tenta realizar a conexão, se retornar verdadeiro entra no IF
            if(this.conexao.conectar())
            {
                //prepara a sentença com a consulta da string
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                //subtitui as interrograções da consulta, pelo valor específico
                sentenca.setInt(1,funcionarios.getID_funcionario());
                sentenca.setString(2,funcionarios.getCPF());
                sentenca.setString(3,funcionarios.getNome()); 
                sentenca.setString(4,funcionarios.getTelefone());
                sentenca.setString(5,funcionarios.getUsuario());
                sentenca.setString(6,funcionarios.getSenha());
                sentenca.setString(7,funcionarios.getFK1_Sexo()); 
                sentenca.setInt(8,funcionarios.getTipo_User());
                sentenca.execute(); //executa o comando no banco
                sentenca.close(); //fecha a sentença
                this.conexao.getConnection().close(); //fecha a conexão com o banco
            }
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    }

    @Override
    public void alterar(Funcionarios funcionarios) {
        String sql = "UPDATE Funcionario SET ID_Funcionario = ?, CPF = ?, Nome = ?, Telefone = ?, Usuario = ?, Senha = ?, FK1_Sexo = (select ID_Genero from Genero where Tipo_Genero = ?), Tipo_User = ?";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                sentenca.setInt(1,funcionarios.getID_funcionario());
                sentenca.setString(2,funcionarios.getCPF());
                sentenca.setString(3,funcionarios.getNome());
                sentenca.setString(4,funcionarios.getTelefone());
                sentenca.setString(5,funcionarios.getUsuario());
                sentenca.setString(6, funcionarios.getSenha());
                sentenca.setString(7, funcionarios.getFK1_Sexo());
                sentenca.setInt(8, funcionarios.getTipo_User());
                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    }

    @Override
    public void excluir() {
        String sql = "DELETE FROM ESCOLA";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
   
                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    }
    
    public void excluirID(int id) {
        String sql = "DELETE FROM cadbasico WHERE idcad = ?";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                sentenca.setInt(1, id);
                
                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    }
    
    @Override
    public ArrayList<Funcionarios> consultar() {
        
        ArrayList<Funcionarios> listaFuncionarios = new ArrayList<Funcionarios>();
        String sql = "SELECT c.ID_Funcionario, c.CPF, c.Nome, c.Telefone, c.Usuario, s.Tipo_Genero, c.Tipo_User "+
                     "FROM Funcionario as c "+
                     "LEFT JOIN Genero AS s ON (s.ID_Genero = c.FK1_Sexo) "+  
                     "ORDER BY c.ID_Funcionario ";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                //recebe o resultado da consulta
                ResultSet resultadoSentenca = sentenca.executeQuery();

                //percorrer cada linha do resultado
                while(resultadoSentenca.next()) 
                {
                    //resgata o valor de cada linha, selecionando pelo nome de cada coluna da tabela Escola
                    Funcionarios funcionario = new Funcionarios();
                    funcionario.setID_funcionario(resultadoSentenca.getInt("ID_Funcionario"));
                    funcionario.setCPF(resultadoSentenca.getString("CPF"));
                    funcionario.setNome(resultadoSentenca.getString("Nome"));
                    funcionario.setTelefone(resultadoSentenca.getString("Telefone"));
                    funcionario.setUsuario(resultadoSentenca.getString("Usuario"));
                    funcionario.setFK1_Sexo(resultadoSentenca.getString("FK1_Sexo"));
                    funcionario.setTipo_User(resultadoSentenca.getInt("Tipo_User"));
                    listaFuncionarios.add(funcionario);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaFuncionarios;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    }
    public ArrayList<Funcionarios> consultar(String str) {
        
        ArrayList<Funcionarios> listaFuncionariosStr = new ArrayList<Funcionarios>();
        String sql = "SELECT c.ID_Funcionario, c.CPF, c.Nome, c.Telefone, c.Usuario, s.Tipo_Genero, c.Tipo_User "+
                     "FROM Funcionarios as c "+
                     "LEFT JOIN Genero AS s ON (s.ID_Genero = c.FK1_Sexo) "+
                     "WHERE ( UPPER(c.Nome like UPPER(?))) "+   
                     "ORDER BY s.Tipo_Genero ";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                //recebe o resultado da consulta
                sentenca.setString(1, "%"+str+"%");
                ResultSet resultadoSentenca = sentenca.executeQuery();

                //percorrer cada linha do resultado
                while(resultadoSentenca.next()) 
                {
                    //resgata o valor de cada linha, selecionando pelo nome de cada coluna da tabela Escola
                    Funcionarios funcionario = new Funcionarios();
                    funcionario.setID_funcionario(resultadoSentenca.getInt("ID_Funcionario"));
                    funcionario.setCPF(resultadoSentenca.getString("CPF"));
                    funcionario.setNome(resultadoSentenca.getString("Nome"));
                    funcionario.setTelefone(resultadoSentenca.getString("Telefone"));
                    funcionario.setUsuario(resultadoSentenca.getString("Usuario"));
                    funcionario.setFK1_Sexo(resultadoSentenca.getString("FK1_Sexo"));
                    funcionario.setTipo_User(resultadoSentenca.getInt("Tipo_User"));
                    
                    listaFuncionariosStr.add(funcionario);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaFuncionariosStr;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    }
    public ArrayList<Funcionarios> dashboard() {
        
        ArrayList<Funcionarios> ListarDashBoard = new ArrayList<Funcionarios>();
//        String sql = "select count(idcad) as numcad, count(idcad)*2 as sumcad, (select count(idsexo)+100 from cadsexo) as numsexualidade from cadbasico;";
        String sql = "select FLOOR(RAND()(10-5+1)*10) as numcad, FLOOR(RAND()(10-5+1)10) as sumcad, FLOOR(RAND()(10-5+1)*10) as numsexualidade";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                //recebe o resultado da consulta
                 ResultSet resultadoSentenca = sentenca.executeQuery();

                //percorrer cada linha do resultado
                while(resultadoSentenca.next()) 
                {
                    //resgata o valor de cada linha, selecionando pelo nome de cada coluna da tabela Escola
 //                   Cadastro cadastro = new Cadastro();
 //                   cadastro.setTotalCadastros(resultadoSentenca.getInt("numcad"));
 //                   cadastro.SetSomaCodigos(resultadoSentenca.getInt("sumcad"));
 //                   cadastro.SetNumSexualidade(resultadoSentenca.getInt("numsexualidade"));
                    
 //                   ListarDashBoard.add(cadastro);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return ListarDashBoard;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    }
    /*public static ResultSet ListarTabla(String consulta){
        
        conexaoBanco cn = conexao.conectar();
        Statement sql;
        ResultSet datos = null;
        try{
            sql=cn.createStatement()
            datos =sql.executeQuery(consulta)
            
        }
        } catch(Exception e){
            System.out.println("error");   
        }
    }*/
    
}