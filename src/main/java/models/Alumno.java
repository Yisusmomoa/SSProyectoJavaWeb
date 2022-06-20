/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bjls2
 */
public class Alumno {
    /*
    matricula int auto_increment primary key not null unique,
usuario varchar(255) not null,
nombre varchar(255),
contraseña varchar(8),
estatus boolean default true
    
    */
    
    private int matricula;
    private String usuario;
    private String nombre;
    private String contraseña;
    private boolean estatus;

    public Alumno() {
    }

    public Alumno(String usuario, String nombre, String contraseña) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public Alumno(int matricula, String usuario, 
            String nombre, String contraseña, boolean estatus) {
        this.matricula = matricula;
        this.usuario = usuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.estatus = estatus;
    }

    public Alumno(int matricula, String usuario,
            String nombre, boolean estatus) {
        this.matricula = matricula;
        this.usuario = usuario;
        this.nombre = nombre;
        this.estatus = estatus;
    }

    public Alumno(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    
    
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    
}
