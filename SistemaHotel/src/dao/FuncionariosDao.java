
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.Funcionarios;


public class FuncionariosDao implements DaoGenerica<Funcionarios>{

    private ConexaoBanco conexao;
    
    public FuncionariosDao()
    {
        this.conexao = new ConexaoBanco();
    }
    
    @Override
    public void inserir(Funcionarios funcionarios) {
        //string com a consulta que será executada no banco
        String sql = "INSERT INTO Funcionarios (CPF, Nome, Telefone, Usuario, Senha, FK1_Sexo) VALUES (?,?,?,?,?(select ID_Genero from Genero where Tipo_Genero = ?),?)";
        
        try
        {
            //tenta realizar a conexão, se retornar verdadeiro entra no IF
            if(this.conexao.conectar())
            {
                //prepara a sentença com a consulta da string
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                //subtitui as interrograções da consulta, pelo valor específico
                sentenca.setInt(2,funcionarios.getCPF());
                sentenca.setString(3,funcionarios.getNome()); 
                sentenca.setInt(4,funcionarios.getTelefone());
                sentenca.setString(5,funcionarios.getUsuario());
                sentenca.setString(6,funcionarios.getSenha());
                sentenca.setInt(7,funcionarios.getFK1_Sexo()); 
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
        String sql = "UPDATE Funcionarios SET ID_Funcionario = ?, cpf = ?, idsexo = (select idsexo from cadsexo where nomesexo = ?), email = ?, foto = ? where idcad = ?";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                sentenca.setInt(1,funcionarios.getID_funcionario());
                sentenca.setInt(2,funcionarios.getCPF());
                sentenca.setString(3,funcionarios.getNome());
                sentenca.setInt(4,funcionarios.getTelefone());
                sentenca.setString(5,funcionarios.getUsuario());
                sentenca.setString(6, funcionarios.getSenha());
                sentenca.setInt(7, funcionarios.getFK1_Sexo());
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
    

    
}