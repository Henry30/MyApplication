package com.henrymeza.mascotasbasedato.pojo;

import android.content.ContentValues;
import android.content.Context;

import com.henrymeza.mascotasbasedato.R;
import com.henrymeza.mascotasbasedato.db.BaseDato;
import com.henrymeza.mascotasbasedato.db.ConstantesBaseDato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by henry on 15/10/2016.
 */

public class ConstructorMascota {
    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascota(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {
        BaseDato db = new BaseDato(context);
        insertarCincoMascotas(db);
        return  db.obtenerTodosLosContactos();
    }
    public ArrayList<Mascota> ListarMascotas() {
        BaseDato db = new BaseDato(context);
        return  db.obtenerTodosLosContactos();
    }
    public ArrayList<Mascota> ListarCincoUltimos() {

        ArrayList<Mascota> lst= new ArrayList<Mascota>();
        BaseDato db = new BaseDato(context);
        lst=db.obtenerTodosLosContactos();
        Collections.sort(lst, new Comparator<Mascota>() {
            @Override
            public int compare(Mascota m1, Mascota m2) {
                return new Integer(m2.getRank()).compareTo(new Integer(m1.getRank()));
            }
        });
        return lst;
    }
    public void insertarCincoMascotas(BaseDato db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_NOMBRE, "Lobo");
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_FOTO, R.drawable.imgperro5);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_NOMBRE, "Ronny");
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_FOTO, R.drawable.imgperro2);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_NOMBRE, "Dumy");
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_FOTO, R.drawable.imgperro3);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_NOMBRE, "Donko");
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_FOTO, R.drawable.imgperro4);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_NOMBRE, "Bobby");
        contentValues.put(ConstantesBaseDato.TABLE_MASCOTA_FOTO, R.drawable.imgperro6);
        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDato db = new BaseDato(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDato.TABLE_LIKE_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDato.TABLE_LIKE_MASCOTA_NUMEROS_LIKE, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDato db = new BaseDato(context);
        return db.obtenerLikesMascota(mascota);
    }

}
