package com.cristian.projetofinalaplicativos1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarBarbeariaActivity extends AppCompatActivity {

    private ListView lstBarbearia;
    private MenuItem mnExcluirP, mnAlterarP;
    private BarbeariaDAO objbarbeariaDAO;
    private List<Barbearia> todasAsBarbearias;
    private List<Barbearia> barbeariasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_barbearia);

        lstBarbearia = findViewById(R.id.lstBarbearia);
        objbarbeariaDAO = new BarbeariaDAO(ListarBarbeariaActivity.this);

        todasAsBarbearias = objbarbeariaDAO.listarTodasAsBarbearias();
        barbeariasFiltradas.addAll(todasAsBarbearias);

        ArrayAdapter<Barbearia> adaptador = new ArrayAdapter<>(ListarBarbeariaActivity.this, android.R.layout.simple_list_item_1,barbeariasFiltradas);

        lstBarbearia.setAdapter(adaptador);
        registerForContextMenu(lstBarbearia);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mi = getMenuInflater();

        mi.inflate(R.menu.menu_clientes_informacoes,menu);

        mnExcluirP = menu.findItem(R.id.mnExluirP);
        mnAlterarP = menu.findItem(R.id.mnAlterarP);

        mnExcluirP.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AdapterView.AdapterContextMenuInfo posicaoDaBarbearia = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Barbearia objBarbeariaExcluir = barbeariasFiltradas.get(posicaoDaBarbearia.position);
                AlertDialog confirmar = new AlertDialog.Builder(ListarBarbeariaActivity.this)
                        .setTitle("Atenção !!")
                        .setMessage("Você quer alterar dados da barbearia " + objBarbeariaExcluir.getNome())
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                todasAsBarbearias.remove(objBarbeariaExcluir);
                                barbeariasFiltradas.remove(objBarbeariaExcluir);
                                objbarbeariaDAO.excluirBarbearia(objBarbeariaExcluir);

                                lstBarbearia.invalidateViews();
                            }
                        }).create();
                confirmar.show();
                return false;
            }
        });

        mnAlterarP.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AdapterView.AdapterContextMenuInfo posicaoDaBarbearia = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Barbearia objBarbeariaAlterar = barbeariasFiltradas.get(posicaoDaBarbearia.position);

                Intent i = new Intent(ListarBarbeariaActivity.this,CadastroBarbeariaActivity.class);
                i.putExtra("barbearia",objBarbeariaAlterar);

                startActivity(i);

                return false;
            }
        });
    }
}