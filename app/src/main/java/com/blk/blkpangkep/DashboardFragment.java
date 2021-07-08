package com.blk.blkpangkep;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.jama.carouselview.CarouselView;
import com.jama.carouselview.CarouselViewListener;
import com.jama.carouselview.enums.OffsetType;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class DashboardFragment extends Fragment {

    private MaterialSearchView searchView;
    private ImageView tb_icon;
    private TextView tb_title;
    private Button profil, peserta, loker;

    private int[] info_image = {R.drawable.infokan,
            R.drawable.infokan, R.drawable.infokan};

    private int[] galeri_image = {R.drawable.infokan,
            R.drawable.infokan, R.drawable.infokan,
            R.drawable.infokan, R.drawable.infokan,
            R.drawable.infokan, R.drawable.infokan,
            R.drawable.infokan};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_dashboard, parent, false);
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau 			
    // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Initialize Button
        profil = (Button) view.findViewById(R.id.profil);
        peserta = (Button) view.findViewById(R.id.peserta);
        loker = (Button) view.findViewById(R.id.loker);

        //Initialize ImageView
        tb_icon = (ImageView) view.findViewById(R.id.toolbar_icon);

        //Initialize TextView
        tb_title = (TextView) view.findViewById(R.id.toolbar_title);
        tb_title.setText("BLK Pangkep");

        //Initialie Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //Initialize Carousel
        CarouselView info_blk = view.findViewById(R.id.info_blk);
        CarouselView galeri_blk = view.findViewById(R.id.galeri_blk);

        //Carousel TODO
        info_blk.setSize(info_image.length);
        info_blk.setResource(R.layout.info_blk_carousel);
        info_blk.hideIndicator(true);
        info_blk.setCarouselOffset(OffsetType.START);
        info_blk.setCarouselViewListener(new CarouselViewListener() {
            @Override
            public void onBindView(View view, int position) {
                ImageView imageView = view.findViewById(R.id.imageView_info);
                imageView.setImageDrawable(getResources().getDrawable(info_image[position]));
            }
        });
        info_blk.show();

        galeri_blk.setSize(galeri_image.length);
        galeri_blk.setResource(R.layout.galeri_blk_carousel);
        galeri_blk.hideIndicator(true);
        galeri_blk.setCarouselOffset(OffsetType.START);
        galeri_blk.setCarouselViewListener(new CarouselViewListener() {
            @Override
            public void onBindView(View view, int position) {
                ImageView imageView = view.findViewById(R.id.imageView_galeri);
                imageView.setImageDrawable(getResources().getDrawable(galeri_image[position]));
            }
        });
        galeri_blk.show();

        //Initialize SearchView
        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);

        //SearchView TODO
        searchView.setVoiceSearch(false);
        searchView.setEllipsize(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(view.findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                tb_icon.setVisibility(View.GONE);
                tb_title.setVisibility(View.GONE);
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                tb_icon.setVisibility(View.VISIBLE);
                tb_title.setVisibility(View.VISIBLE);
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_firstFragment_to_secondFragment);
            }
        });

        peserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_first_fragment_to_fourth_fragment);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        super.onCreateOptionsMenu(menu,inflater);
    }
}