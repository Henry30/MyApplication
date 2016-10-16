package com.henrymeza.mascotasbasedato.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.henrymeza.mascotasbasedato.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by henry on 15/10/2016.
 */

public class BaseDato extends SQLiteOpenHelper {
    private Context context;
    public BaseDato(Context context) {
        super(context, ConstantesBaseDato.DATABASE_NAME, null, ConstantesBaseDato.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota="CREATE TABLE "+ConstantesBaseDato.TABLE_MASCOTA+"("+
                ConstantesBaseDato.TABLE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDato.TABLE_MASCOTA_NOMBRE+" TEXT, "+
                ConstantesBaseDato.TABLE_MASCOTA_FOTO+" INTEGER "+
                ")";

        String queryCrearTablaLikesMascota="CREATE TABLE "+ConstantesBaseDato.TABLE_LIKE_MASCOTA+"("+
                ConstantesBaseDato.TABLE_LIKE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDato.TABLE_LIKE_MASCOTA_ID_MASCOTA+" INTEGER, "+
                ConstantesBaseDato.TABLE_LIKE_MASCOTA_NUMEROS_LIKE+" INTEGER, "+
                "FOREIGN KEY (" + ConstantesBaseDato.TABLE_LIKE_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDato.TABLE_MASCOTA + "(" + ConstantesBaseDato.TABLE_MASCOTA_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDato.TABLE_LIKE_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDato.TABLE_MASCOTA);
        onCreate(db);
    }


    public ArrayList<Mascota> obtenerTodosLosContactos() {
        ArrayList<Mascota> lstMascota = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDato.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDato.TABLE_LIKE_MASCOTA_NUMEROS_LIKE+") as likes " +
                    " FROM " + ConstantesBaseDato.TABLE_LIKE_MASCOTA +
                    " WHERE " + ConstantesBaseDato.TABLE_LIKE_MASCOTA_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setRank(registrosLikes.getInt(0));
            }else {
                mascotaActual.setRank(0);
            }

            lstMascota.add(mascotaActual);

        }

        db.close();

        return lstMascota;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDato.TABLE_MASCOTA,null, contentValues);
        db.close();
    }
    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDato.TABLE_LIKE_MASCOTA, null, contentValues);
        db.close();
    }


    public int obtenerLikesMascota(Mascota contacto){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDato.TABLE_LIKE_MASCOTA_NUMEROS_LIKE+")" +
                " FROM " + ConstantesBaseDato.TABLE_LIKE_MASCOTA +
                " WHERE " + ConstantesBaseDato.TABLE_LIKE_MASCOTA_ID_MASCOTA + "="+contacto.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }

}
