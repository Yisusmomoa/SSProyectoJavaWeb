/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bjls2
 */
public class Maestro {
    /*
    noEmpleado int auto_increment primary key not null unique, 
    nombreMaestro varchar(255) not null,
    usuario varchar(255) not null unique,
    contraseña varchar(8) not null,
    tipo varchar(255),
    estatus boolean default true
    */
    private int noEmpleado;
    private String nombreMaestro;
    private String contraseña;
    private String usuario;
    private String tipo;
    private boolean estatus;

    public Maestro() {
    }

    public Maestro(int noEmpleado, String nombreMaestro, 
            String usuario, String tipo, boolean estatus) {
        this.noEmpleado = noEmpleado;
        this.nombreMaestro = nombreMaestro;
        this.usuario = usuario;
        this.tipo = tipo;
        this.estatus = estatus;
    }

    public Maestro(int noEmpleado, String nombreMaestro,
            String usuario, boolean estatus) {
        this.noEmpleado = noEmpleado;
        this.nombreMaestro = nombreMaestro;
        this.usuario = usuario;
        this.estatus = estatus;
    }

    public Maestro(String contraseña, 
            String usuario) {
        this.contraseña = contraseña;
        this.usuario = usuario;
    }

    public Maestro(String nombreMaestro, 
            String contraseña, String usuario,
            String tipo) {
        this.nombreMaestro = nombreMaestro;
        this.contraseña = contraseña;
        this.usuario = usuario;
        this.tipo = tipo;
    }
    
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public int getNoEmpleado() {
        return noEmpleado;
    }

    public void setNoEmpleado(int noEmpleado) {
        this.noEmpleado = noEmpleado;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    
}
