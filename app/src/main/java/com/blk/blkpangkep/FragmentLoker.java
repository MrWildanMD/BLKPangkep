package com.blk.blkpangkep;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blk.blkpangkep.Adapter.FeedAdapter;
import com.blk.blkpangkep.Interface.Api;
import com.blk.blkpangkep.Model.RssObject;
import com.blk.blkpangkep.Network.RetrofitClient;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentLoker extends Fragment {

    private MaterialSearchView searchView;
    private ShimmerFrameLayout mShimmerViewContainer;

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private Gson gson;
    private RssObject rssObject;

    private ImageView tb_icon;
    private TextView tb_title;

//    private native String URL();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_loker, parent, false);
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
        tb_title.setText(R.string.loker);

        //Initialize SearchView
        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);

        //Initialize Shimmer
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        //Initialie Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //Initialize RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.list_loker);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadRSS();

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


    }

    private void loadRSS() {
        Call<RssObject> call = RetrofitClient.getInstance().getMyApi().getRss();
        call.enqueue(new Callback<RssObject>() {
            @Override
            public void onResponse(Call<RssObject> call, Response<RssObject> response) {
                if (response == null) {
                    Toast.makeText(getContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                    return;
                }
                rssObject = gson.fromJson(response.toString(), RssObject.class);
                FeedAdapter feedAdapter = new FeedAdapter(rssObject, getContext());
                recyclerView.setAdapter(feedAdapter);
                feedAdapter.notifyDataSetChanged();

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RssObject> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occured", Toast.LENGTH_SHORT).show();
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