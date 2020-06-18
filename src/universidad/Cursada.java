/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

/**
 *
 * @author Pablo
 */
public class Cursada {
    int idCursada;
    Alumno alumno;
    Materia materia;
    double nota;

    public Cursada(Materia materia, Alumno alumno, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Cursada() {
    }

    public int getIdCursada() {
        return idCursada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    
    public double getNota() {
        return nota;
    }

    public void setIdCursada(int idCursada) {
        this.idCursada = idCursada;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    
}
