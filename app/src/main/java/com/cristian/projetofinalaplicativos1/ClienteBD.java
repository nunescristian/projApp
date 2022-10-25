package com.cristian.projetofinalaplicativos1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClienteBD extends SQLiteOpenHelper {
    private static final String NAME = "bdCliente";
    private static final int VERSION = 1;

    public ClienteBD(Context contexto){
        super(contexto, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase bdCliente) {
        bdCliente.execSQL("create table tb_Cliente("+
                "id integer primary key autoincrement," +
                "nome varchar(100),"+
                "usuario varchar(30),"+
                "senha varchar(15),"+
                "categoria varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bdCliente, int i, int i1) {}

}
