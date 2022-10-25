package com.cristian.projetofinalaplicativos1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroClientes extends AppCompatActivity {
    private EditText edtNome, edtUsuario, edtSenha;
    private Button btnCadastrar;
    private ClienteDAO objClienteDAO;
    private Cliente objCliente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);

        edtNome = findViewById(R.id.edtNome);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        Intent i = getIntent();

        if (i.hasExtra("cliente")){
            objCliente = (Cliente) i.getSerializableExtra("cliente");

            edtNome.setText(objCliente.getNome());
            edtSenha.setText(objCliente.getSenha());
            edtUsuario.setText(objCliente.getUsuario());
            btnCadastrar.setText("Alterar");
        }

        objClienteDAO = new ClienteDAO(CadastroClientes.this);

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (objCliente == null){
                    objCliente = new Cliente();

                    objCliente.setNome(edtNome.getText().toString());
                    objCliente.setUsuario(edtUsuario.getText().toString());
                    objCliente.setSenha(edtSenha.getText().toString());


                    objClienteDAO.cadastrarCliente(objCliente);


                    startActivity(new Intent(CadastroClientes.this, ListarClientesActivity.class));
                    limparCampos();
                }else{

                    objCliente.setNome(edtNome.getText().toString());
                    objCliente.setUsuario(edtUsuario.getText().toString());
                    objCliente.setSenha(edtSenha.getText().toString());


                    objClienteDAO.alterarCliente(objCliente);

                    startActivity(new Intent(CadastroClientes.this, ListarClientesActivity.class));
                    limparCampos();
                }
            }
        });
    }

    public void limparCampos(){
        edtNome.setText("");
        edtUsuario.setText("");
        edtSenha.setText("");
        edtNome.requestFocus();

        }

}