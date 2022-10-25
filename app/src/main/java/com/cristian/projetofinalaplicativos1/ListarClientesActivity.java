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

public class ListarClientesActivity extends AppCompatActivity {
    private ListView lstClientes;
    private ClienteDAO objClienteDAO;
    private MenuItem mnExcluir, mnAlterar, mnEntrar;
    private List<Cliente> todosOsClientes;
    private List<Cliente> clientesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);

        lstClientes = findViewById(R.id.lstClientes);
        objClienteDAO = new ClienteDAO(ListarClientesActivity.this);

        todosOsClientes = objClienteDAO.listarTodasOsClientes();
        clientesFiltrados.addAll(todosOsClientes);

        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<>(ListarClientesActivity.this, android.R.layout.simple_list_item_1,clientesFiltrados);

        lstClientes.setAdapter(adaptador);
        registerForContextMenu(lstClientes);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mi = getMenuInflater();

        mi.inflate(R.menu.menu_clientes_informacoes,menu);

        mnAlterar = menu.findItem(R.id.mnAlterar);
        mnExcluir = menu.findItem(R.id.mnExcluir);
        mnEntrar = menu.findItem(R.id.mnEntrar);

        mnExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AdapterView.AdapterContextMenuInfo posicaoDoCliente = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Cliente objClienteExcluir = clientesFiltrados.get(posicaoDoCliente.position);

                AlertDialog confirmar = new AlertDialog.Builder(ListarClientesActivity.this)
                        .setTitle("Atenção !!")
                        .setMessage("Você está prestes a excluir um cliente, você perderá os Dados")
                        .setMessage("Você quer excluir a " + objClienteExcluir.getNome())
                        .setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                todosOsClientes.remove(objClienteExcluir);
                                clientesFiltrados.remove(objClienteExcluir);
                                objClienteDAO.excluirCliente(objClienteExcluir);

                                lstClientes.invalidateViews();
                                Intent intent = new Intent(ListarClientesActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).create();
                confirmar.show();
                return false;
            }
        });

        mnAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AdapterView.AdapterContextMenuInfo posicaoDoCliente = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Cliente objClienteAlterar = clientesFiltrados.get(posicaoDoCliente.position);

                Intent i = new Intent(ListarClientesActivity.this,CadastroClientes.class);
                i.putExtra("cliente",objClienteAlterar);

                startActivity(i);

                return false;
            }
        });
        mnEntrar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent i = new Intent(ListarClientesActivity.this,ClienteDadosPessoais.class);
                startActivity(i);

                return false;
            }
        });
    }
}