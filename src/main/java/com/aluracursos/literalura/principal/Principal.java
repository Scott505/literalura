package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repositorio.AutorRepository;
import com.aluracursos.literalura.repositorio.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    Optional<Autor> autorBuscar;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    *****  MENU  *****
                    1 - Buscar libro por titulo para registrar 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    filtraAutorEnAnno();
                    break;
                case 5:
                    buscarPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
    }

    private void buscarPorIdioma() {
        System.out.println("""
                    Elija un idioma:
                    
                    es - Español 
                    en - Ingles
                    fr - Frances
                    pt - Portugues""");
        var idioma = teclado.nextLine();

        List<Libro> librosPorIdioma = libroRepository.findByIdiomas(idioma.toLowerCase().trim());
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros en el idioma: "+idioma);
        } else {
            librosPorIdioma.forEach(System.out::println);
        }
    }

    private DatosLibros getDatosLibros() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
        DatosResultados datosResultados = conversor.obtenerDatos(json, DatosResultados.class);
        if (datosResultados != null && datosResultados.results() != null && !datosResultados.results().isEmpty()) {
            DatosLibros primerLibro = datosResultados.results().get(0);
            return primerLibro;
        } else {
            return null;
        }
    }

    private void buscarLibro() {
        DatosLibros datos = getDatosLibros();
        if (datos == null) {
            System.out.println("No se encontró ningún libro con ese nombre.");
            return;
        }
        Libro libro = new Libro(datos);
        Autor autor = autorRepository
                .findByNombreContainsIgnoreCase(libro.getAutor().getNombre())
                .orElse(null);
        System.out.println("Autor buscado: " + autor);
        if (autor == null) {
            autor = libro.getAutor();
            autorRepository.save(autor);
        }

        libro.setAutor(autor);
        libroRepository.save(libro);

        System.out.println("Libro: " + libro);
    }

    private void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    public void filtraAutorEnAnno() {
        System.out.println("¿En que año?");
        var anno = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = autorRepository.AutorVivoEnElAnno(anno);
        if (autores.isEmpty()) {
            System.out.println("** NO se encontraron autores en el año " + anno + " **");
        } else {
            System.out.println("*** Autores vivos en " + anno + "***");
            autores.forEach(a ->
                    System.out.println("Nombre: " + a.getNombre() + " - Nacimiento: " + a.getNacimiento() + " - Fallecimiento: " +
                            a.getFallecimiento()));
        }
    }


}