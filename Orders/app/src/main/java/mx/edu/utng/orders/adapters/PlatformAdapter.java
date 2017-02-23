package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Platform;
import mx.edu.utng.orders.model.Product;

/**
 * Created by Toshiba on 23/02/2017.
 */
public class PlatformAdapter extends RecyclerView.Adapter<PlatformAdapter.PlatformViewHolder> implements View.OnClickListener {

    List<Platform> platforms;
    View.OnClickListener listener;

    public PlatformAdapter(List<Platform> platforms){
        this.platforms = platforms;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public PlatformAdapter.PlatformViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout2,parent,false);

        PlatformViewHolder holder = new PlatformViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlatformAdapter.PlatformViewHolder holder, int position) {
        holder.tvImgFilename.setText(platforms.get(position).getImgFilename());
        holder.tvName.setText(platforms.get(position).getName());
        holder.ivPlatform.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {

        return platforms.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!= null){
            listener.onClick(v);
        }

    }

    public static class PlatformViewHolder extends  RecyclerView.ViewHolder  implements View.OnClickListener{

        CardView cvPlatform;
        TextView tvImgFilename;
        TextView tvName;
        ImageView ivPlatform;
        ImageButton btEditPlatform;
        ImageButton btDeletePlatform;
        View.OnClickListener listener;



        public PlatformViewHolder(View itemView) {
            super(itemView);
            cvPlatform = (CardView) itemView.findViewById(R.id.cv_platform);
            ivPlatform = (ImageView) itemView.findViewById(R.id.iv_platform);
            tvImgFilename = (TextView) itemView.findViewById(R.id.tv_img_filename);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            btEditPlatform = (ImageButton) itemView.findViewById(R.id.bt_edit_platform);
            btDeletePlatform = (ImageButton) itemView.findViewById(R.id.bt_delete_platform);
            btEditPlatform.setOnClickListener(this);
            btDeletePlatform.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }

        }

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }
    }
}




