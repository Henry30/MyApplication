package com.henrymeza.mascotasbasedato.vista.fragment;

import com.henrymeza.mascotasbasedato.adapter.MascotaAdaptador;
import com.henrymeza.mascotasbasedato.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by henry on 16/10/2016.
 */

public interface IRecyclerViewFragmentMascotaFavorita {
    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> lstMascota);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
