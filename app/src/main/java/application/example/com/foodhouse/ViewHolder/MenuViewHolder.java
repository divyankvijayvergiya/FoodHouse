package application.example.com.foodhouse.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import application.example.com.foodhouse.Interface.ItemClickListener;
import application.example.com.foodhouse.R;

/**
 * Created by Dell on 16-10-2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtMenuName;
    public ImageView imageView;
    private ItemClickListener itemClickListener;
    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName= (TextView) itemView.findViewById(R.id.menu_name);
        imageView= (ImageView) itemView.findViewById(R.id.menu_image);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
