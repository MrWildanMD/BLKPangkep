package com.blk.blkpangkep;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blk.blkpangkep.Adapter.GaleriAdapter;
import com.blk.blkpangkep.Adapter.PesertaAdapter;
import com.blk.blkpangkep.Model.Peserta;
import com.blk.blkpangkep.Model.Youtube.Galeri;
import com.blk.blkpangkep.Network.RetrofitClientThree;
import com.blk.blkpangkep.Network.RetrofitClientTwo;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGaleri extends Fragment {

    private MaterialSearchView searchView;
    private ShimmerFrameLayout mShimmerViewContainer;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private ImageView tb_icon;
    private TextView tb_title;

    private Galeri galeri = new Galeri();
    private GaleriAdapter galeriAdapter;

    private final String LOG_TAG = "FragmentGaleri";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_galeri, parent, false);
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau
    // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Initialize ImageView
        tb_icon = (ImageView) view.findViewById(R.id.toolbar_icon);

        //Initialize TextView
        tb_title = (TextView) view.findViewById(R.id.toolbar_title);
        tb_title.setText(R.string.galeri);

        //Initialize Shimmer
        mShimmerViewContainer = view.findViewById(R.id.shimmer_galeri);

        //Initialie Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        //Initialize RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.list_galeri);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_galeri);

        loadGaleri();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mShimmerViewContainer.startShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.VISIBLE);
                loadGaleri();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void loadGaleri() {
        Call<Galeri> call = RetrofitClientThree.getInstance().getMyApi().getGaleri();
        call.enqueue(new Callback<Galeri>() {
            @Override
            public void onResponse(Call<Galeri> call, Response<Galeri> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: "+response.errorBody(), Toast.LENGTH_SHORT).show();
                }
                galeri = response.body();
                galeriAdapter = new GaleriAdapter(galeri, getContext());
                recyclerView.setAdapter(galeriAdapter);
                galeriAdapter.notifyDataSetChanged();

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Galeri> call, Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}