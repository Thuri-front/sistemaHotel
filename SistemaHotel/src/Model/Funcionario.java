/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tobias
 */
public class Funcionario {
    private int ID_funcionario;
    private int CPF;
    private String Nome;
    private int Telefone;
    private String Usuario;
    private String Senha;
    private int FK1_Sexo;
    private int Tipo_User;
    
    public Funcionario(){
  
    };
    
    public Funcionario(int CPF, String Nome, int Telefone, String Usuario, String Senha, int FK1_Sexo, int Tipo_User){
        this.CPF = CPF;
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Usuario = Usuario;
        this.Senha = Senha;
        this.FK1_Sexo = FK1_Sexo;
        this.Tipo_User = Tipo_User;
    };
    
    public Funcionario(int ID_Funcionario,int CPF, String Nome, int Telefone, String Usuario, String Senha, int FK1_Sexo, int Tipo_User){
        this.ID_funcionario = ID_Funcionario;
        this.CPF = CPF;
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Usuario = Usuario;
        this.Senha = Senha;
        this.FK1_Sexo = FK1_Sexo;
        this.Tipo_User = Tipo_User;
    };

    public int getID_funcionario() {
        return ID_funcionario;
    }

    public void setID_funcionario(int ID_funcionario) {
        this.ID_funcionario = ID_funcionario;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int Telefone) {
        this.Telefone = Telefone;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public int getFK1_Sexo() {
        return FK1_Sexo;
    }

    public void setFK1_Sexo(int FK1_Sexo) {
        this.FK1_Sexo = FK1_Sexo;
    }
    
    public int getTipo_User() {
        return Tipo_User;
    }

    public void setTipo_User(int Tipo_User) {
        this.Tipo_User = Tipo_User;
    }
}