package com.cristian.projetofinalaplicativos1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroBarbeariaActivity extends AppCompatActivity {
    private EditText edtNomeP, edtQuantidadeP, edtValorS;
    private Button btnVerDiasDeAgendamento, btnCadBarbeiro;
    private BarbeariaDAO objBarbeariaDAO;
    private Barbearia objBarbearia = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_barbearia);

        edtNomeP = findViewById(R.id.edtNomeP);
        edtValorS = findViewById(R.id.edtValorS);
        btnCadBarbeiro = findViewById(R.id.btnCadBarbeiro);
        btnVerDiasDeAgendamento = findViewById(R.id.btnVerDiasDeAgendamento);
        objBarbeariaDAO = new BarbeariaDAO(CadastroBarbeariaActivity.this);

        Intent i = getIntent();

        if (i.hasExtra("barbearia")){
            objBarbearia = (Barbearia) i.getSerializableExtra("barbearia");
            edtNomeP.setText(objBarbearia.getNome());
            edtValorS.setText(String.valueOf(objBarbearia.getValor()));
            edtQuantidadeP.setText(String.valueOf(objBarbearia.getQuantidade()));
            btnCadBarbeiro.setText("Alterar");
        }

        objBarbeariaDAO = new BarbeariaDAO(CadastroBarbeariaActivity.this);

        btnCadBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (objBarbearia == null){
                    objBarbearia = new Barbearia();

                    objBarbearia.setQuantidade(Integer.parseInt(edtQuantidadeP.getText().toString()));
                    objBarbearia.setNome(edtNomeP.getText().toString());
                    objBarbearia.setValor(Float.parseFloat(edtValorS.getText().toString()));

                    objBarbeariaDAO.cadastrarBarbearia(objBarbearia);

                    startActivity(new Intent(CadastroBarbeariaActivity.this,ListarBarbeariaActivity.class));
                    limparCampos();
                }else{

                    objBarbearia.setQuantidade(Integer.parseInt(edtQuantidadeP.getText().toString()));
                    objBarbearia.setNome(edtNomeP.getText().toString());
                    objBarbearia.setValor(Float.parseFloat(edtValorS.getText().toString()));

                    objBarbeariaDAO.alterarBarbearia(objBarbearia);
                    limparCampos();
                }

            }
        });

        btnVerDiasDeAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getApplicationContext(),ListarBarbeariaActivity.class));
                finish();

            }
        });
    }
    public void limparCampos(){
        edtNomeP.setText("");
        edtQuantidadeP.setText("");
        edtValorS.setText("");
        edtQuantidadeP.requestFocus();
    }
}