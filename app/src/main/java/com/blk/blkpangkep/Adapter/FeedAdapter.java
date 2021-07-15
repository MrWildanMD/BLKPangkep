package com.blk.blkpangkep.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blk.blkpangkep.Interface.ItemClickListener;
import com.blk.blkpangkep.Model.RssObject;
import com.blk.blkpangkep.R;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
{

    public ImageView itemImg;
    public TextView tvTitle, tvPub, tvDescription;
    private ItemClickListener itemClickListener;

    public FeedViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        itemImg = (ImageView) itemView.findViewById(R.id.item_img);

        tvTitle = (TextView) itemView.findViewById(R.id.item_title);
        tvPub = (TextView) itemView.findViewById(R.id.item_published);
        tvDescription = (TextView) itemView.findViewById(R.id.item_dexcription);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);

    }

    @Override
    public boolean onLongClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), true);

        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{

    private RssObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public FeedAdapter(RssObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_item, parent, false);
        return new FeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedViewHolder holder, int position) {

        Glide.with(mContext)
                .load("http://static.careerjet.net/images/logo_top_uk.png")
                .error(R.drawable.ic_blk)
                .into(holder.itemImg);
        holder.tvTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.tvPub.setText(rssObject.getItems().get(position).getPubDate());
        holder.tvDescription.setText(rssObject.getItems().get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return rssObject.getItems().size();
    }
}
