package com.cristian.projetofinalaplicativos1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClienteDadosPessoais extends AppCompatActivity {
    private TextView txtNomeBarbeariaP, txtCategoriaAlterar;
    private Button btnCriarR, btnVerR;
    private ClienteDAO objClienteDAO;
    private Cliente objCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_dados_pessoais);

        txtCategoriaAlterar = findViewById(R.id.txtCategoriaAlterar);
        btnCriarR = findViewById(R.id.btnCriarR);
        btnVerR = findViewById(R.id.btnVerR);

        Intent i = getIntent();

        if (i.hasExtra("cliente")){
            objCliente = (Cliente) i.getSerializableExtra("cliente");
            txtNomeBarbeariaP.setText(objCliente.getNome());
            txtCategoriaAlterar.setText(objCliente.getCategoria());
        }

        btnCriarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClienteDadosPessoais.this,CadastroClientes.class);
                startActivity(i);
            }
        });


    }

}