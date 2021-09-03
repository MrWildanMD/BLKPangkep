package com.blk.blkpangkep;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blk.blkpangkep.Model.Youtube.Galeri;
import com.blk.blkpangkep.Network.RetrofitClientThree;
import com.bumptech.glide.Glide;
import com.jama.carouselview.CarouselView;
import com.jama.carouselview.CarouselViewListener;
import com.jama.carouselview.enums.OffsetType;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private MaterialSearchView searchView;
    private ImageView tb_icon;
    private TextView tb_title,tv_info, tv_galeri;
    private Button profil, peserta, loker, registrasi;
    private Galeri galeri = new Galeri();
    private Dialog dialog;

    private int[] info_image = {R.drawable.infokan};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, parent, false);
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau 			
    // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        dialog = new Dialog(getContext());
        //Initialize Button
        profil = (Button) view.findViewById(R.id.profil);
        peserta = (Button) view.findViewById(R.id.peserta);
        loker = (Button) view.findViewById(R.id.loker);
        registrasi = (Button) view.findViewById(R.id.registrasi);

        //Initialize ImageView
        tb_icon = (ImageView) view.findViewById(R.id.toolbar_icon);

        //Initialize TextView
        tb_title = (TextView) view.findViewById(R.id.toolbar_title);
        tb_title.setText("BLK Pangkep");
        tv_info = (TextView) view.findViewById(R.id.tv_info);
        tv_galeri = (TextView) view.findViewById(R.id.tv_galeri);

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
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://www.simpulrakyat.co.id/2021/06/blk-pangkep-cetak-tenaga-kerja-terampil-dan-kompetitif.html"));
                        requireContext().startActivity(i);
                    }
                });
            }
        });
        info_blk.show();

        Call<Galeri> call = RetrofitClientThree.getInstance().getMyApi().getGaleri();
        call.enqueue(new Callback<Galeri>() {
            @Override
            public void onResponse(Call<Galeri> call, Response<Galeri> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: "+response.errorBody(), Toast.LENGTH_SHORT).show();
                }
                galeri = response.body();
                galeri_blk.setSize(galeri.getItems().size());
                galeri_blk.setResource(R.layout.galeri_blk_carousel);
                galeri_blk.hideIndicator(true);
                galeri_blk.setCarouselOffset(OffsetType.START);
                galeri_blk.setCarouselViewListener(new CarouselViewListener() {
                    @Override
                    public void onBindView(View view, int position) {
                        ImageView imageView = view.findViewById(R.id.imageView_galeri);
                        Glide.with(requireContext())
                                .load(Uri.parse(galeri.getItems().get(position).getSnippet().getThumbnails().getHigh().getUrl()))
                                .error(R.drawable.ic_blk)
                                .placeholder(R.drawable.ic_blk)
                                .into(imageView);
                    }
                });
                galeri_blk.show();
            }

            @Override
            public void onFailure(Call<Galeri> call, Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
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

        tv_galeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_first_fragment_to_third_fragment);
            }
        });

        loker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_first_fragment_to_fifth_fragment);
            }
        });

        tv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.simpulrakyat.co.id/"));
                requireContext().startActivity(i);
            }
        });

        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        requireActivity()
                .getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                        builder.setTitle("Konfirmasi");
                        builder.setMessage("Apakah anda ingin keluar dari aplikasi?");

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        });
                        alertDialog.show();
                    }
                });
    }

    private void showDialog() {
        dialog.setContentView(R.layout.option_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button bt1 = dialog.findViewById(R.id.bt1);
        Button bt2 = dialog.findViewById(R.id.bt2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), RegisterActivity.class);
                requireContext().startActivity(i);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), RegisterKemnaker.class);
                requireContext().startActivity(i);
            }
        });
        dialog.show();
    }

}