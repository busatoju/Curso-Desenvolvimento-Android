package dev.busato.applistavip.controller;

import java.util.ArrayList;
import java.util.List;

import dev.busato.applistavip.model.Curso;

public class CursoController {

    private List<Curso> coursesList;

    private List<Curso> getListaCurso() {
        coursesList = new ArrayList<Curso>();

        coursesList.add(new Curso("Java"));
        coursesList.add(new Curso("Kotlin"));
        coursesList.add(new Curso("Swift"));
        coursesList.add(new Curso("Dart"));
        coursesList.add(new Curso("Flutter"));
        coursesList.add(new Curso("C#"));
        coursesList.add(new Curso("SQL"));
        return coursesList;
    }

    public ArrayList<String> dataToSpinner() {
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < getListaCurso().size(); i++) {
            Curso curso = (Curso) getListaCurso().get(i);
            data.add(curso.getNome());
        }

        return data;
    }
}
