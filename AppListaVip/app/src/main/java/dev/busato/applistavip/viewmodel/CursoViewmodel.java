package dev.busato.applistavip.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import dev.busato.applistavip.repository.CursoRepository;

public class CursoViewmodel extends ViewModel {

    private final MutableLiveData<List<String>> cursoLiveData = new MutableLiveData<>();

    private CursoRepository repository;


    public void init(CursoRepository repository) {
        this.repository = repository;
        loadCursos();
    }

    public LiveData<List<String>> getCursosLiveData() {
        return cursoLiveData;
    }

    private void loadCursos() {
        List<String> cursos = repository.dataToSpinner();
        cursoLiveData.setValue(cursos);
    }
}
