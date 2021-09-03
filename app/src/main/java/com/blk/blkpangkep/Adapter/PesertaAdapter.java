package com.blk.blkpangkep.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blk.blkpangkep.Interface.ItemClickListener;

import org.jetbrains.annotations.NotNull;

import com.blk.blkpangkep.Model.Item;
import com.blk.blkpangkep.Model.Peserta;
import com.blk.blkpangkep.R;

import java.util.ArrayList;
import java.util.List;

class PesertaViewHolder extends RecyclerView.ViewHolder
{
    public TextView tvNama, tvJk, tvPdk, tvTl, tvAlamat;
    private ItemClickListener itemClickListener;

    public PesertaViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        tvNama = itemView.findViewById(R.id.text_nama);
        tvJk = itemView.findViewById(R.id.text_jk);
        tvPdk = itemView.findViewById(R.id.text_pdk);
        tvTl = itemView.findViewById(R.id.text_tl);
        tvAlamat = itemView.findViewById(R.id.text_alamat);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}

public class PesertaAdapter extends RecyclerView.Adapter<PesertaViewHolder> implements Filterable {

    private Peserta peserta;
    private Context mContext;
    private LayoutInflater inflater;
    private List<List<String>> dataList;
    private List<List<String>> dataListFull;
    private List<String> item;

    public PesertaAdapter(Peserta peserta, Context mContext) {
        this.peserta = peserta;
        this.mContext = mContext;
        dataList = peserta.getData();
        dataListFull = new ArrayList<>(dataList);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<List<String>> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (int i = 0; i < dataList.size(); i++) {
                    if (dataListFull.get(i).get(1).toLowerCase().contains(filterPattern) || dataListFull.get(i).get(5).toLowerCase().contains(filterPattern)) {
                        filteredList.add(dataListFull.get(i));
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList.clear();
            dataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public PesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_peserta, parent, false);
        return new PesertaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PesertaViewHolder holder, int position) {
        holder.tvNama.setText(peserta.getData().get(position).get(1));
        holder.tvJk.setText(peserta.getData().get(position).get(2));
        holder.tvPdk.setText(peserta.getData().get(position).get(3));
        holder.tvTl.setText(peserta.getData().get(position).get(4));
        holder.tvAlamat.setText(peserta.getData().get(position).get(5));
    }

    @Override
    public int getItemCount() {
        return peserta.getData().size();
    }
}
