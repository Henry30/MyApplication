package com.henrymeza.mascotasbasedato.vista.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henrymeza.mascotasbasedato.R;
import com.henrymeza.mascotasbasedato.adapter.MascotaAdaptador;
import com.henrymeza.mascotasbasedato.pojo.Mascota;
import com.henrymeza.mascotasbasedato.presentador.IRecyclerViewFragmentPresenter;
import com.henrymeza.mascotasbasedato.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by henry on 16/10/2016.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{
    public ArrayList<Mascota> lstObjMascota;
    private RecyclerView rvListaMascotas;
    private IRecyclerViewFragmentPresenter presenter;
    //pri

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_recyclerview,container,false);

        rvListaMascotas=(RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter=new RecyclerViewFragmentPresenter(this, getContext());

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm= new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> lstMascota) {
        MascotaAdaptador adaptador= new MascotaAdaptador(lstMascota,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvListaMascotas.setAdapter(adaptador);
    }
}
