package com.blk.blkpangkep.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blk.blkpangkep.Interface.ItemClickListener;

import org.jetbrains.annotations.NotNull;

import com.blk.blkpangkep.Model.Youtube.Galeri;
import com.blk.blkpangkep.Model.Youtube.Id;
import com.blk.blkpangkep.Model.Youtube.Medium;
import com.blk.blkpangkep.Model.Youtube.Snippet;
import com.blk.blkpangkep.Model.Youtube.Thumbnails;
import com.blk.blkpangkep.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class GaleriViewHolder extends RecyclerView.ViewHolder
{
    public ImageView thumbnails;
    public TextView tv_title,tv_publish, tv_desc;
    private ItemClickListener itemClickListener;
    public CardView card_galeri;

    public GaleriViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        thumbnails = (ImageView) itemView.findViewById(R.id.galeri_img);
        tv_title = (TextView) itemView.findViewById(R.id.galeri_title);
        tv_publish = (TextView) itemView.findViewById(R.id.galeri_published);
        tv_desc = (TextView) itemView.findViewById(R.id.galeri_description);
        card_galeri = (CardView) itemView.findViewById(R.id.card_galeri);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}

public class GaleriAdapter extends RecyclerView.Adapter<GaleriViewHolder> {

    private Galeri galeri;
    private Context mContext;
    private LayoutInflater inflater;

    public GaleriAdapter(Galeri galeri, Context mContext){
        this.galeri = galeri;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GaleriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_galeri, parent, false);
        return new GaleriViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriViewHolder holder, int position) {
        Glide.with(mContext)
                .load(Uri.parse(galeri.getItems().get(position).getSnippet().getThumbnails().getHigh().getUrl()))
                .error(R.drawable.ic_blk)
                .into(holder.thumbnails);
        holder.tv_title.setText(galeri.getItems().get(position).getSnippet().getTitle());
        holder.tv_publish.setText(galeri.getItems().get(position).getSnippet().getPublishedAt());
        holder.tv_desc.setText(galeri.getItems().get(position).getSnippet().getDescription());
        holder.card_galeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Intent i = new Intent(Intent.ACTION_VIEW);
                if (galeri.getItems().get(pos).getId().getKind().equalsIgnoreCase("youtube#video")){
                    i.setData(Uri.parse("https://youtu.be/"+galeri.getItems().get(pos).getId().getVideoId()));
                } else {
                    i.setData(Uri.parse("https://www.youtube.com/channel/"+galeri.getItems().get(pos).getId().getChannelId()));
                }
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galeri.getItems().size();
    }
}
