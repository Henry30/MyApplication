package com.henrymeza.mascotasbasedato;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.henrymeza.mascotasbasedato.adapter.PageAdapter;
import com.henrymeza.mascotasbasedato.pojo.Mascota;
import com.henrymeza.mascotasbasedato.vista.fragment.FotosFragment;
import com.henrymeza.mascotasbasedato.vista.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    //RecyclerViewFragment fragmentReciclerView;
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
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.toolbar1);
        toolbar.setLogo(R.drawable.cat_footprint_48);
        toolbar.setTitle(R.string.app_name);
        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();
        if(toolbar !=null)
        {
            setSupportActionBar(toolbar);
        }
    }

    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.mnuAbout:
                Intent i= new Intent(this,AcercadeActivity.class);
                startActivity(i);
                break;
            case R.id.mnuContacto:
                Intent i3= new Intent(this,ContactoActivity.class);
                startActivity(i3);
                break;
            case R.id.mnuRanking:
                Intent i4= new Intent(this,MascotasFavoritasActivity.class);
                startActivity(i4);

                break;
        }
        return true;

    }
    private ArrayList<Fragment> agregarFragments(){

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        //fragments.add(new FotosFragment());

        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_facedog);
      //  tabLayout.getTabAt(1).setIcon(R.mipmap.ic_facedog);
    }

}
