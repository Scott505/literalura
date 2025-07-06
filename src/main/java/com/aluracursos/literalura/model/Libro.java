package com.aluracursos.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private int id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Column(length = 500)
    private String resumen;
    private String idiomas;
    private Integer cantidadDescargas;

    public Libro(DatosLibros datos) {
        this.id = datos.id();
        this.titulo = datos.titulo();
        String resumenOriginal = datos.resumen().get(0);
        this.resumen = resumenOriginal.length() > 500 ? resumenOriginal.substring(0, 500) : resumenOriginal;
        this.idiomas = datos.idiomas().get(0);
        this.cantidadDescargas = datos.cantidadDescargas();
        this.autor = new Autor(datos.autor().get(0));
    }

    public Libro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", resumen='" + resumen + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", cantidadDescargas=" + cantidadDescargas;
    }
}
