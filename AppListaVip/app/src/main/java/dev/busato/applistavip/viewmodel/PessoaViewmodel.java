package dev.busato.applistavip.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.busato.applistavip.model.Pessoa;
import dev.busato.applistavip.repository.PessoaRepository;

public class PessoaViewmodel extends ViewModel {

    private final MutableLiveData<Pessoa> pessoaLiveData = new MutableLiveData<>();
    private PessoaRepository repository;

    public void init(Context context) {
        if (repository == null) {
            repository = new PessoaRepository(context);
            Pessoa pessoa = repository.getPessoa();

            if (pessoa != null) pessoaLiveData.setValue(pessoa);
        }
    }

    public LiveData<Pessoa> getPessoa() {
        return pessoaLiveData;
    }

    public void savePessoa(Pessoa pessoa) {
        repository.savePessoa(pessoa);
        pessoaLiveData.setValue(pessoa);
    }

    public void clear() {
        repository.clear();
        pessoaLiveData.setValue(null);
    }
}
