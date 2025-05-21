package dev.busato.applistavip.view;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.busato.applistavip.R;
import dev.busato.applistavip.controller.PessoaController;
import dev.busato.applistavip.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText courseNameInput;
    private EditText phoneInput;

    private Button clearButton;
    private Button saveButton;
    private Button finishButton;
    private PessoaController controller;

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


        controller = new PessoaController(this);

        iniciarComponentesDeLayout();

        Pessoa pessoaInPreferences = controller.fetch();

        if (pessoaInPreferences != null) popularEditText(pessoaInPreferences);
        setupClearButton();
        setupSaveButton();
        setupFinishButton();
    }


    private void iniciarComponentesDeLayout() {
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        courseNameInput = findViewById(R.id.courseNameInput);
        phoneInput = findViewById(R.id.phoneInput);

        clearButton = findViewById(R.id.clearButton);
        saveButton = findViewById(R.id.saveButton);
        finishButton = findViewById(R.id.finishButton);
    }

    private void setupClearButton() {
        clearButton.setOnClickListener(v -> clearFields());
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> {
            if (validateForm()) {
                Pessoa pessoa = new Pessoa(firstNameInput.getText().toString(), lastNameInput.getText().toString(), courseNameInput.getText().toString(), phoneInput.getText().toString());
                controller.save(pessoa);
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
        if (courseNameInput.getText().toString().isBlank()) {
            courseNameInput.setError("Campo obrigatório");
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




    private void popularEditText(Pessoa pessoa) {
        firstNameInput.setText(pessoa.getNome());
        lastNameInput.setText(pessoa.getSobrenome());
        courseNameInput.setText(pessoa.getNomeDoCurso());
        phoneInput.setText(pessoa.getTelefone());
    }

    private void clearFields() {
        firstNameInput.setText("");
        lastNameInput.setText("");
        courseNameInput.setText("");
        phoneInput.setText("");
        controller.clear();
    }
}