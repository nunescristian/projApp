package com.cristian.projetofinalaplicativos1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BarbeariaDAO {
    private BarbeariaBD barbeariaBD;
    private SQLiteDatabase bdBarbearia;

    public BarbeariaDAO(Context contexto){
        this.barbeariaBD = new BarbeariaBD(contexto);
        this.bdBarbearia = this.barbeariaBD.getWritableDatabase();
    }

    public void cadastrarBarbearia(Barbearia objBarbearia){
        ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("quantidade",objBarbearia.getQuantidade());
        valoresCampos.put("nome",objBarbearia.getNome());
        valoresCampos.put("valor",objBarbearia.getValor());
        valoresCampos.put("valor_total",objBarbearia.valorTotal());

        this.bdBarbearia.insert("tb_barbearia",null,valoresCampos);

    }

    public List<Barbearia> listarTodasAsBarbearias(){
        List<Barbearia> todasAsBarbearias = new ArrayList<>();

        String [] campos = {"quantidade","nome","valor","valor_total"};

        Cursor cursor = bdBarbearia.query("tb_barbearia",campos,null,null,null,null,null,null);

        while (cursor.moveToNext()){

            Barbearia objBarbearia = new Barbearia();

            objBarbearia.setQuantidade(cursor.getInt(0));
            objBarbearia.setNome(cursor.getString(1));
            objBarbearia.setValor(cursor.getFloat(2));
            objBarbearia.setValorTotal(cursor.getFloat(3));

            todasAsBarbearias.add(objBarbearia);
        }

        return todasAsBarbearias;

    }

    public void alterarBarbearia(Barbearia objBarbearia){

        ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("quantidade",objBarbearia.getQuantidade());
        valoresCampos.put("valor",objBarbearia.getValor());
        valoresCampos.put("nome",objBarbearia.getNome());

        String [] id = {String.valueOf(objBarbearia.getNome())};

        this.bdBarbearia.update("tb_barbearia",valoresCampos,"nome = ?",id);

    }

    public void excluirBarbearia(Barbearia objBarbearia){

        String [] id = {String.valueOf(objBarbearia.getNome())};
        this.bdBarbearia.delete("tb_barbearia","nome = ?",id);

    }


}

