package com.cristian.projetofinalaplicativos1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BarbeariaBD extends SQLiteOpenHelper {
    private static final String NAME = "bdbarbearia";
    private static final int VERSION = 1;

    public BarbeariaBD(Context contexto){
        super(contexto, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bdBarbearia) {
        bdBarbearia.execSQL("create table tb_valores("+
                "quantidade integer (3),"+
                "nome varchar(40),"+
                "valor float(5),"+
                "valor_total float(7))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bdBarbearia, int i, int i1) {

    }
}