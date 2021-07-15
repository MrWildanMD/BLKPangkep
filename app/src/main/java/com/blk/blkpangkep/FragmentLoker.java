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
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blk.blkpangkep.Adapter.FeedAdapter;
import com.blk.blkpangkep.Model.RssObject;
import com.blk.blkpangkep.Network.RetrofitClient;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentLoker extends Fragment {

    private MaterialSearchView searchView;
    private ShimmerFrameLayout mShimmerViewContainer;

    private RecyclerView recyclerView;
    private Gson gson = new Gson();
    private SwipeRefreshLayout refreshLayout;
    private RssObject rssObject = new RssObject();

    private ImageView tb_icon;
    private TextView tb_title;
    private EditText editText;

    public FeedAdapter feedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
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
//        editText = (EditText) searchView.findViewById(com.miguelcatalan.materialsearchview.R.id.searchTextView);
//        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //Initialize Shimmer
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        //Initialie Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //Initialize RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.list_loker);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);

        loadRSS();

        //SearchView TODO
        searchView.setVoiceSearch(false);
        searchView.setEllipsize(true);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                feedAdapter.getFilter().filter(newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                tb_icon.setVisibility(View.GONE);
                tb_title.setVisibility(View.GONE);

                refreshLayout.setEnabled(false);
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                tb_icon.setVisibility(View.VISIBLE);
                tb_title.setVisibility(View.VISIBLE);

                refreshLayout.setEnabled(true);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mShimmerViewContainer.startShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.VISIBLE);
                loadRSS();
                refreshLayout.setRefreshing(false);
                setHasOptionsMenu(false);
                getActivity().invalidateOptionsMenu();
            }
        });

    }

    private void loadRSS() {
        Call<RssObject> call = RetrofitClient.getInstance().getMyApi().getRss();
        call.enqueue(new Callback<RssObject>() {
            @Override
            public void onResponse(@NotNull Call<RssObject> call, @NotNull Response<RssObject> response) {
               if (!response.isSuccessful()) {
                   Toast.makeText(getContext(), "Error: "+response.errorBody(), Toast.LENGTH_SHORT).show();
               }
               setHasOptionsMenu(true);
               getActivity().invalidateOptionsMenu();
                rssObject = response.body();
                feedAdapter = new FeedAdapter(rssObject, getContext());
                recyclerView.setAdapter(feedAdapter);
                feedAdapter.notifyDataSetChanged();

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NotNull Call<RssObject> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
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