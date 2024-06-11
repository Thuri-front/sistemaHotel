/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lucas
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoBanco {   
    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
    private Connection conexao;   
    public conexaoBanco() {
        this.servidor = "200.195.171.122";
        this.banco = "grupo10_HostManager";
        this.usuario = "grupo10";
        this.senha = "gopMi1PKCuJpaB29";
    }
    public boolean conectar(){
        try
        {
            this.conexao = DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.banco,this.usuario,this.senha);
            return true;
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    public Connection getConnection() {
        return conexao;
    }

}

