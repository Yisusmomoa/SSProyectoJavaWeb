/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bjls2
 */
public class Grupo {
    /*
    idGrupo int auto_increment primary key not null unique,
claveMateriaGrupo int,
numAlumnos int,
CONSTRAINT fkmateria foreign key (claveMateriaGrupo) references Materia(claveMateria)
    */
    
    private int idGrupo;
    private int claveMateriaGrupo;
    private int numAlumnos;
    private Materia materia;

    public Grupo() {
    }

    public Grupo(int idGrupo, int claveMateriaGrupo, int numAlumnos, Materia materia) {
        this.idGrupo = idGrupo;
        this.claveMateriaGrupo = claveMateriaGrupo;
        this.numAlumnos = numAlumnos;
        this.materia = materia;
    }

    public Grupo(int numAlumnos, Materia materia) {
        this.numAlumnos = numAlumnos;
        this.materia = materia;
    }

    public Grupo(int claveMateriaGrupo, int numAlumnos) {
        this.claveMateriaGrupo = claveMateriaGrupo;
        this.numAlumnos = numAlumnos;
    }

    public Grupo(int idGrupo, int numAlumnos, Materia materia) {
        this.idGrupo = idGrupo;
        this.numAlumnos = numAlumnos;
        this.materia = materia;
    }
    

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getClaveMateriaGrupo() {
        return claveMateriaGrupo;
    }

    public void setClaveMateriaGrupo(int claveMateriaGrupo) {
        this.claveMateriaGrupo = claveMateriaGrupo;
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public void setNumAlumnos(int numAlumnos) {
        this.numAlumnos = numAlumnos;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    
    
}
