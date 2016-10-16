package com.henrymeza.mascotasbasedato.pojo;

import android.os.Parcel;

/**
 * Created by henry on 15/10/2016.
 */

public class Mascota {


    private int id;
    private String nombre;
    private int foto;
    private int rank;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Mascota(String nombre, int foto, int rank){
        this.nombre=nombre;
        this.foto=foto;
        this.rank=rank;
    }

    public Mascota()
    {

    }

}
