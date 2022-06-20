/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bjls2
 */
public class Materia {
    /*
    claveMateria int auto_increment primary key not null unique,
nombreMateria varchar(255),
estatus boolean default true 
    
    */
    private int claveMateria;
    private String nombreMateria;
    private boolean estatus;

    public Materia() {
    }

    public Materia(int claveMateria, String nombreMateria, 
            boolean estatus) {
        this.claveMateria = claveMateria;
        this.nombreMateria = nombreMateria;
        this.estatus = estatus;
    }

    public Materia(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
    
    
    
    public int getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(int claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    
    
    
}
