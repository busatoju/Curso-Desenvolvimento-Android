package dev.busato.applistavip.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dev.busato.applistavip.R;
import dev.busato.applistavip.model.Pessoa;
import dev.busato.applistavip.repository.CursoRepository;
import dev.busato.applistavip.viewmodel.CursoViewmodel;
import dev.busato.applistavip.viewmodel.PessoaViewmodel;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameInput, lastNameInput, phoneInput;
    private Button clearButton, saveButton, finishButton;
    private Spinner coursesSpinner;
    private PessoaViewmodel pessoaViewmodel;

    private List<String> cursos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iniciarComponentesDeLayout();
        setupButtons();
        setupPessoaViewmodel();
        setupCursoViewmodel();
    }


    private void iniciarComponentesDeLayout() {

        /// EditText
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        phoneInput = findViewById(R.id.phoneInput);

        /// Button
        clearButton = findViewById(R.id.clearButton);
        saveButton = findViewById(R.id.saveButton);
        finishButton = findViewById(R.id.finishButton);

        /// Spinner
        coursesSpinner = findViewById(R.id.courseSpinner);

    }

    private void setupClearButton() {
        clearButton.setOnClickListener(v -> clearFields());
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> {
            if (validateForm()) {
                Pessoa pessoa = new Pessoa(firstNameInput.getText().toString(), lastNameInput.getText().toString(), coursesSpinner.getSelectedItem().toString(), phoneInput.getText().toString());
                pessoaViewmodel.savePessoa(pessoa);
                Toast.makeText(this, "Salvo " + pessoa, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Preencha o formulário antes de salvar", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private boolean validateForm() {
        boolean isValid = true;

        if (firstNameInput.getText().toString().isBlank()) {
            firstNameInput.setError("Campo obrigatório");
            isValid = false;
        }
        if (lastNameInput.getText().toString().isBlank()) {
            lastNameInput.setError("Campo obrigatório");
            isValid = false;

        }

        if (phoneInput.getText().toString().isBlank()) {
            phoneInput.setError("Campo obrigatório");
            isValid = false;

        }

        return isValid;
    }

    private void setupFinishButton() {
        finishButton.setOnClickListener(v -> {
            Toast.makeText(this, "Volte sempre", Toast.LENGTH_LONG).show();
            finish();
        });
    }


    private void popularFormulario(Pessoa pessoa) {
        firstNameInput.setText(pessoa.getNome());
        lastNameInput.setText(pessoa.getSobrenome());
        phoneInput.setText(pessoa.getTelefone());


        if (!pessoa.getNomeDoCurso().isEmpty()) {
            int posicao = cursos.indexOf(pessoa.getNomeDoCurso());
            if (posicao >= 0) {
                coursesSpinner.setSelection(posicao);
            }
        }
    }

    private void clearFields() {
        firstNameInput.setText("");
        lastNameInput.setText("");
        phoneInput.setText("");
        pessoaViewmodel.clear();
    }

    private void setupButtons() {
        setupClearButton();
        setupFinishButton();
        setupSaveButton();
    }

    private void setupPessoaViewmodel() {
        pessoaViewmodel = new ViewModelProvider(this).get(PessoaViewmodel.class);
        pessoaViewmodel.init(this);

        pessoaViewmodel.getPessoa().observe(this, pessoa -> {
            if (pessoa != null) popularFormulario(pessoa);
        });
    }

    private void setupCursoViewmodel() {
        CursoViewmodel cursoViewmodel = new ViewModelProvider(this).get(CursoViewmodel.class);
        cursoViewmodel.init(new CursoRepository());
        cursos = cursoViewmodel.getCursosLiveData().getValue();
        assert cursos != null;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                cursos
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(adapter);
    }
}