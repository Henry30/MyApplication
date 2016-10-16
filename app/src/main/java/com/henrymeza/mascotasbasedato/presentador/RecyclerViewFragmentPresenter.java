package com.henrymeza.mascotasbasedato.presentador;

import android.content.Context;

import com.henrymeza.mascotasbasedato.pojo.ConstructorMascota;
import com.henrymeza.mascotasbasedato.pojo.Mascota;
import com.henrymeza.mascotasbasedato.vista.fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;

/**
 * Created by henry on 16/10/2016.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascota constructorMascota;
    private ArrayList<Mascota> lstMascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView,Context context){
        this.iRecyclerViewFragmentView=iRecyclerViewFragmentView;
        this.context=context;
        obtenerMascotas();
    }
    @Override
    public void obtenerMascotas() {
        constructorMascota = new ConstructorMascota(context);
        lstMascotas=constructorMascota.ListarMascotas();
        if(lstMascotas.size()<5)
            lstMascotas=constructorMascota.obtenerDatos();

        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(lstMascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
