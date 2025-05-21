package dev.busato.applistavip.view;

import android.content.Context;
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

import dev.busato.applistavip.R;
import dev.busato.applistavip.controller.CursoController;
import dev.busato.applistavip.controller.PessoaController;
import dev.busato.applistavip.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText phoneInput;

    private Spinner coursesSpinner;

    private Button clearButton;
    private Button saveButton;
    private Button finishButton;
    private PessoaController pessoaController;
    private CursoController cursoController;

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


        pessoaController = new PessoaController(this);
        cursoController = new CursoController();

        iniciarComponentesDeLayout();

        Pessoa pessoaInPreferences = pessoaController.fetch();

        if (pessoaInPreferences != null) popularEditText(pessoaInPreferences);
        setupClearButton();
        setupSaveButton();
        setupFinishButton();
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) this, android.R.layout.simple_list_item_1, cursoController.dataToSpinner());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        coursesSpinner.setAdapter(adapter);

    }

    private void setupClearButton() {
        clearButton.setOnClickListener(v -> clearFields());
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> {
            if (validateForm()) {
                Pessoa pessoa = new Pessoa(firstNameInput.getText().toString(), lastNameInput.getText().toString(), "Nome do curso", phoneInput.getText().toString());
                pessoaController.save(pessoa);
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
//        if (courseNameInput.getText().toString().isBlank()) {
//            courseNameInput.setError("Campo obrigatório");
//            isValid = false;
//
//        }
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


    private void popularEditText(Pessoa pessoa) {
        firstNameInput.setText(pessoa.getNome());
        lastNameInput.setText(pessoa.getSobrenome());
        phoneInput.setText(pessoa.getTelefone());
    }

    private void clearFields() {
        firstNameInput.setText("");
        lastNameInput.setText("");
        phoneInput.setText("");
        pessoaController.clear();
    }
}