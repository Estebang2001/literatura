package com.aluracursos.literatura.principal;

import com.aluracursos.literatura.model.*;
import com.aluracursos.literatura.repository.IAutorRepository;
import com.aluracursos.literatura.repository.IRepository;
import com.aluracursos.literatura.service.ConsumoAPI;
import com.aluracursos.literatura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    
    private Scanner sc = new Scanner(System.in);
    private String url = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private List<DatosLibro> datosLibroList = new ArrayList<>();

    private List<DatosLibro> listaGuardada = new ArrayList<>();
    private IRepository repositorio;
    private IAutorRepository autorRepository;

    public Principal(IRepository repository, IAutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void MostrarMenu() {
        
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija La opción a través de su número:
                    1- buscar libro por título
                    2- Listar libro regístrados
                    3- Lístar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    0- Salir
                    """;

            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    ListarAutores();
                    break;
                case 4:
                    listarAutoresVivoxAno();
                    break;
                case 5:
                    listarLibrosxIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Marque una opción valida");
            }
        }

    }

    private void listarLibrosxIdioma() {
        System.out.println("Indique el idioma del libro");
        var idioma = sc.nextLine();
        List<Libro> libros = autorRepository.findLibrosByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            libros.forEach(libro -> System.out.println(libro.getTitulo()));
        }
    }

    private void listarAutoresVivoxAno() {
        System.out.println("Indique el año");
        var año = sc.nextInt();
        sc.nextLine();
        List<Autor> autores = autorRepository.findAutoresVivosEnAno(año);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año especificado.");
        } else {
            autores.forEach(autor -> System.out.println(autor.getAutor()));
        }
    }

    private void ListarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores.");
        } else {
            autores.forEach(autor -> System.out.println(autor.getAutor()));
        }
    }

    private void listarLibros() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros.");
        } else {
            libros.forEach(libro -> System.out.println(libro.getTitulo()));
        }
    }

    private List<Autor> convertirDatosAutorAAutor(List<DatosAutor> datosAutores) {
        List<Autor> autores = new ArrayList<>();
        for (DatosAutor datosAutor : datosAutores) {
            Autor autor = new Autor();
            autor.setAutor(datosAutor.autor());
            autor.setAñoNacimiento(datosAutor.añoNacimiento());
            autor.setAñoFallecimiento(datosAutor.añoFallecimiento());
            autores.add(autor);
        }
        return autores;
    }

    private void buscarLibro() {
        var json = consumoAPI.obtenerDatos(url);
        var data = convierteDatos.obtenerDatos(json, Flat.class);

        List<DatosLibro> listaDeDatosLibros = new ArrayList<>();
        listaDeDatosLibros.addAll(data.results());


        System.out.println("Indique el nombre del libro");
        var nombreLibro = sc.nextLine();

        Optional<DatosLibro> buscarLibro = listaDeDatosLibros.stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst();



        if (buscarLibro.isPresent()) {
            DatosLibro datosLibro = buscarLibro.get();
            List<Autor> autores = convertirDatosAutorAAutor(datosLibro.autores());

            for (Autor autor : autores) {
                autorRepository.save(autor);
            }

            Libro libro = new Libro(datosLibro.titulo(), autores, datosLibro.idioma(), datosLibro.descargas());
            repositorio.save(libro);
        } else {
            System.out.println("Libro no encontrado");
        }
    }
}
