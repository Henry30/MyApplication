package com.henrymeza.mascotasbasedato.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.henrymeza.mascotasbasedato.R;
import com.henrymeza.mascotasbasedato.pojo.ConstructorMascota;
import com.henrymeza.mascotasbasedato.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by henry on 15/10/2016.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> lstObjMascota;
    int nuevoRank;
    //ArrayList<Mascota> lstObjMascota;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> lstObjMascota,Activity activity) {
        this.lstObjMascota=lstObjMascota;
        this.activity=activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewholder,final int position) {
        final Mascota objMascota=lstObjMascota.get(position);
        mascotaViewholder.tvNombreCV.setText(objMascota.getNombre());
        mascotaViewholder.tvRankCV.setText(String.valueOf(objMascota.getRank()));
        mascotaViewholder.imgFoto.setImageResource(objMascota.getFoto());

        mascotaViewholder.btnRank_MarcarCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConstructorMascota constructorMascota = new ConstructorMascota(activity);
                constructorMascota.darLikeMascota(objMascota);

                mascotaViewholder.tvRankCV.setText(String.valueOf(constructorMascota.obtenerLikesMascota(objMascota)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstObjMascota.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private ImageView btnRank_MarcarCV;
        private TextView tvNombreCV;
        private TextView tvRankCV;
        private ImageView imgRank_LecturaCV;
        public MascotaViewHolder(View itemView){
            super(itemView);
            imgFoto=(ImageView)itemView.findViewById(R.id.imgFoto);
            btnRank_MarcarCV=(ImageView)itemView.findViewById(R.id.btnRank_MarcarCV);
            tvNombreCV=(TextView)itemView.findViewById(R.id.tvNombreCV);
            tvRankCV=(TextView)itemView.findViewById(R.id.tvRankCV);
            imgRank_LecturaCV=(ImageView)itemView.findViewById(R.id.imgRank_LecturaCV);
        }

    }
}
