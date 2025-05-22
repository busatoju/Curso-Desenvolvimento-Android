package dev.busato.applistavip.repository;

import android.content.Context;
import android.content.SharedPreferences;

import dev.busato.applistavip.model.Pessoa;

public class PessoaRepository {

    private static final String PREF_NAME = "pref_lista_vip";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String COURSE_NAME = "course_name";
    private static final String PHONE = "phone";

    private final SharedPreferences sharedPreferences;

    public PessoaRepository(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void savePessoa(Pessoa pessoa) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIRST_NAME, pessoa.getNome());
        editor.putString(LAST_NAME, pessoa.getSobrenome());
        editor.putString(COURSE_NAME, pessoa.getNomeDoCurso());
        editor.putString(PHONE, pessoa.getTelefone());
        editor.apply();
    }

    public Pessoa getPessoa() {
        String nome = sharedPreferences.getString(FIRST_NAME, "");
        String sobrenome = sharedPreferences.getString(LAST_NAME, "");
        String nomeCurso = sharedPreferences.getString(COURSE_NAME, "");
        String phone = sharedPreferences.getString(PHONE, "");

        if (!nome.isBlank() && !sobrenome.isBlank() && !nomeCurso.isBlank() && !phone.isBlank()) {
            return new Pessoa(nome, sobrenome, nomeCurso, phone);
        }
        return null;
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
