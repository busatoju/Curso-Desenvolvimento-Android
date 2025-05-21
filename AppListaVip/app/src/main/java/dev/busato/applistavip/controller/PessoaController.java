package dev.busato.applistavip.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import dev.busato.applistavip.model.Pessoa;
import dev.busato.applistavip.view.MainActivity;

public class PessoaController {
    public static final String NOME_PREFERENCES = "pref_lista_vip";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String COURSE_NAME = "course_name";
    public static final String PHONE = "phone";

    private final SharedPreferences sharedPreferences;

    public PessoaController(MainActivity mainActivity) {
        sharedPreferences = mainActivity.getSharedPreferences(NOME_PREFERENCES, MODE_PRIVATE);
    }

    public void save(Pessoa pessoa) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIRST_NAME, pessoa.getNome());
        editor.putString(LAST_NAME, pessoa.getSobrenome());
        editor.putString(COURSE_NAME, pessoa.getNomeDoCurso());
        editor.putString(PHONE, pessoa.getTelefone());
        editor.apply();
    }

    public Pessoa fetch() {
        String nome = sharedPreferences.getString(FIRST_NAME, "");
        String sobrenome = sharedPreferences.getString(LAST_NAME, "");
        String nomeCurso = sharedPreferences.getString(COURSE_NAME, "");
        String phone = sharedPreferences.getString(PHONE, "");

        if (isNotEmpty(nome) && isNotEmpty(sobrenome) && isNotEmpty(nomeCurso) && isNotEmpty(phone)) {
            return new Pessoa(nome, sobrenome, nomeCurso, phone);
        }
        return null;
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.isBlank();
    }
}
