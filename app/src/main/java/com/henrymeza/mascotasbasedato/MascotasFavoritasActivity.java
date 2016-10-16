package com.henrymeza.mascotasbasedato;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.henrymeza.mascotasbasedato.adapter.PageAdapter;
import com.henrymeza.mascotasbasedato.pojo.Mascota;
import com.henrymeza.mascotasbasedato.vista.fragment.FotosFragment;
import com.henrymeza.mascotasbasedato.vista.fragment.RecyclerViewFragment;
import com.henrymeza.mascotasbasedato.vista.fragment.RecyclerViewFragmentMascotaFavorita;

import java.util.ArrayList;

public class MascotasFavoritasActivity extends AppCompatActivity {

    ArrayList<Mascota> lstObjMascota;
    private RecyclerView rvListaMascotas;
    private Toolbar mnuToolBar;
    private ArrayList<Fragment> fragments= new ArrayList<>();
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        toolbar=(Toolbar)findViewById(R.id.toolbar1);
        toolbar.setLogo(R.drawable.cat_footprint_48);
        toolbar.setTitle(R.string.app_name);


        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();
        if(toolbar !=null)
        {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowCustomEnabled(false);
        }
    }

    public  boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        MenuItem mnuRank=menu.findItem(R.id.mnuRanking);
        MenuItem mnuAbout=menu.findItem(R.id.mnuAbout);
        MenuItem mnuContacto=menu.findItem(R.id.mnuContacto);
        mnuRank.setVisible(false);
        mnuAbout.setVisible(false);
        mnuContacto.setVisible(false);
        return true;
    }

    private ArrayList<Fragment> agregarFragments(){

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragmentMascotaFavorita());

        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

     //   tabLayout.getTabAt(0).setIcon(R.mipmap.ic_home);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_facedog);
    }

}
