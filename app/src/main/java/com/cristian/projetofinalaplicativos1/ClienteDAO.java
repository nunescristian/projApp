package com.cristian.projetofinalaplicativos1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private ClienteBD ClienteBD;
    private SQLiteDatabase bdCliente;

    public ClienteDAO(Context contexto) {
        this.ClienteBD = new ClienteBD(contexto);
        this.bdCliente = this.ClienteBD.getWritableDatabase();
    }

    public void cadastrarCliente(Cliente objCliente) {
        ContentValues valoresCampo = new ContentValues();

        valoresCampo.put("nome", objCliente.getNome());
        valoresCampo.put("usuario", objCliente.getUsuario());
        valoresCampo.put("senha", objCliente.getSenha());
        valoresCampo.put("categoria", objCliente.getCategoria());

        this.bdCliente.insert("tb_cliente", null, valoresCampo);
    }

    public List<Cliente> listarTodasOsClientes() {
        List<Cliente> todasOsClientes = new ArrayList<>();

        String[] campos = {"id", "nome", "usuario", "senha", "categoria"};

        Cursor cursor = bdCliente.query("tb_cliente", campos, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Cliente objCliente = new Cliente();

            objCliente.setId(cursor.getInt(0));
            objCliente.setNome(cursor.getString(1));
            objCliente.setUsuario(cursor.getString(2));
            objCliente.setSenha(cursor.getString(3));
            objCliente.setCategoria(cursor.getString(4));

            todasOsClientes.add(objCliente);
        }
        return todasOsClientes;

    }

    public void alterarCliente(Cliente objCliente) {

        ContentValues valoresCampo = new ContentValues();

        valoresCampo.put("nome", objCliente.getNome());
        valoresCampo.put("usuario", objCliente.getUsuario());
        valoresCampo.put("senha", objCliente.getSenha());
        valoresCampo.put("categoria", objCliente.getCategoria());

        String[] id = {String.valueOf(objCliente.getId())};

        this.bdCliente.update("tb_Cliente", valoresCampo, "nome = ?", id);

    }

    public void excluirCliente(Cliente objCliente) {

        String[] id = {String.valueOf(objCliente.getId())};
        this.bdCliente.delete("tb_cliente", "nome = ?", id);

    }
}
