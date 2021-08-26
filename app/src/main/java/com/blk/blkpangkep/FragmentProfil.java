package com.blk.blkpangkep;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.google.android.material.snackbar.Snackbar;
import com.jama.carouselview.CarouselView;
import com.jama.carouselview.CarouselViewListener;
import com.jama.carouselview.enums.OffsetType;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class FragmentProfil extends Fragment {

    private MaterialSearchView searchView;
    private ImageView tb_icon;
    private TextView tb_title;
    private CardView profil,visimisi,tugas,struktur;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_profil, parent, false);
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau
    // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        dialog = new Dialog(getContext());
        //Initialize ImageView
        tb_icon = (ImageView) view.findViewById(R.id.toolbar_icon);

        //Initialize TextView
        tb_title = (TextView) view.findViewById(R.id.toolbar_title);
        tb_title.setText(R.string.profil);

        //Initialize SearchView
        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);

        //Initialie Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        //Initialize Cardview
        profil = view.findViewById(R.id.profil);
        visimisi = view.findViewById(R.id.visimisi);
        tugas = view.findViewById(R.id.tugas);
        struktur = view.findViewById(R.id.struktur);

        //ImagePop
        final ImagePopup imagePopup = new ImagePopup(getContext());
        imagePopup.setImageOnClickClose(true);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgStruktur);
        imagePopup.initiatePopup(imageView.getDrawable());

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.string.profil_text, R.drawable.profil);
            }
        });

        visimisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.string.visi_misi, R.drawable.visimisi);
            }
        });

        tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.string.tugas_pokok, R.drawable.tugas);
            }
        });

        struktur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePopup.viewPopup();
            }
        });
    }

    private void showDialog(int text, int banner) {
        dialog.setContentView(R.layout.profil_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView bannerImg = dialog.findViewById(R.id.bannerDialog);
        TextView textContent = dialog.findViewById(R.id.textContent);
        ImageView close = dialog.findViewById(R.id.close);
        bannerImg.setImageDrawable(ContextCompat.getDrawable(getContext(), banner));
        textContent.setText(text);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}