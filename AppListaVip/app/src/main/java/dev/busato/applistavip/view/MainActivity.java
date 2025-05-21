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
    public static final String NOME_PREFERENCES = "pref_lista_vip";
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
        SharedPreferences sharedPreferences = getSharedPreferences(NOME_PREFERENCES, 0);

        Editor listaVip = sharedPreferences.edit();

        controller = new PessoaController();


        iniciarComponentesDeLayout();
        clearButton();
        saveButton(listaVip);
        finishButton();
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

    private void clearButton() {
        clearButton.setOnClickListener(v -> {
            firstNameInput.getText().clear();
            lastNameInput.getText().clear();
            courseNameInput.getText().clear();
            phoneInput.getText().clear();
        });
    }

    private void saveButton(Editor listaVip) {
        saveButton.setOnClickListener(v -> {

            if (validate()) {
                Pessoa pessoa = new Pessoa(firstNameInput.getText().toString(), lastNameInput.getText().toString(), courseNameInput.getText().toString(), phoneInput.getText().toString());
                controller.salvar(pessoa);
                listaVip.putString("fistName", pessoa.getNome());
                listaVip.putString("lastname", pessoa.getSobrenome());
                listaVip.putString("courseName", pessoa.getNomeDoCurso());
                listaVip.putString("phoneName", pessoa.getTelefone());
                listaVip.apply();
                Toast.makeText(this, "Salvo " + pessoa, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Preencha o formulário antes de salvar", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void finishButton() {
        finishButton.setOnClickListener(v -> {
            Toast.makeText(this, "Volte sempre", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private boolean validate() {
        return isNotEmpty(firstNameInput.getText().toString()) && isNotEmpty(lastNameInput.getText().toString()) && isNotEmpty(courseNameInput.getText().toString()) && isNotEmpty(phoneInput.getText().toString());
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.isBlank();
    }
}