package com.henrymeza.mascotasbasedato.presentador;

import android.content.Context;

import com.henrymeza.mascotasbasedato.pojo.ConstructorMascota;
import com.henrymeza.mascotasbasedato.pojo.Mascota;
import com.henrymeza.mascotasbasedato.vista.fragment.IRecyclerViewFragmentMascotaFavorita;
import com.henrymeza.mascotasbasedato.vista.fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;

/**
 * Created by henry on 16/10/2016.
 */

public class RecyclerViewFragmentMascotaFavoritaPresenter implements IRecyclerViewFragmentMascotaFavoritaPresenter {
    private IRecyclerViewFragmentMascotaFavorita iRecyclerViewFragmentMascotaFavorita;
    private Context context;
    private ConstructorMascota constructorMascota;
    private ArrayList<Mascota> lstMascotas;

    public RecyclerViewFragmentMascotaFavoritaPresenter(IRecyclerViewFragmentMascotaFavorita iRecyclerViewFragmentMascotaFavorita,Context context){
        this.iRecyclerViewFragmentMascotaFavorita=iRecyclerViewFragmentMascotaFavorita;
        this.context=context;
        obtenerMascotas();
    }
    @Override
    public void obtenerMascotas() {
        constructorMascota = new ConstructorMascota(context);
        lstMascotas=constructorMascota.ListarCincoUltimos();
        if(lstMascotas.size()<5)
            lstMascotas=constructorMascota.obtenerDatos();

        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {

        iRecyclerViewFragmentMascotaFavorita.inicializarAdaptadorRV( iRecyclerViewFragmentMascotaFavorita.crearAdaptador(lstMascotas));
        iRecyclerViewFragmentMascotaFavorita.generarLinearLayoutVertical();
    }
}
