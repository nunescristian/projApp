package com.cristian.projetofinalaplicativos1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome,edtSenha;
    private Button btnEntrar;
    private TextView txtCadastrar;
    private ClienteDAO objClienteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtCadastrar = findViewById(R.id.txtCadastrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Cliente> todosOsClientes = objClienteDAO.listarTodasOsClientes();


                for (int i = 0; i < todosOsClientes.size(); i ++){
                    if(todosOsClientes.get(i).getNome().equals(edtNome.getText().toString()) && todosOsClientes.get(i).getSenha().equals(edtSenha.getText().toString())){
                        startActivity(new Intent(MainActivity.this,ListarClientesActivity.class));
                        finish();
                        break;
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuário ou senha incorreto",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CadastroClientes.class));
                finish();
            }
        });

    }
}

