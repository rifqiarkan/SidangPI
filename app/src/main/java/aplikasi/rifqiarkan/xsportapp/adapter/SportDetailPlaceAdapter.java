package aplikasi.rifqiarkan.xsportapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import aplikasi.rifqiarkan.xsportapp.OnEventListener;
import aplikasi.rifqiarkan.xsportapp.R;
import aplikasi.rifqiarkan.xsportapp.model.Image;
import aplikasi.rifqiarkan.xsportapp.model.Place;

public class SportDetailPlaceAdapter extends RecyclerView.Adapter<SportDetailPlaceAdapter.ViewHolder> {

    private Context context;

    private List<Image> images;

    private OnEventListener<String> onEventListener;

    public void setOnEventListener(OnEventListener<String> onEventListener) {
        this.onEventListener = onEventListener;
    }

    public SportDetailPlaceAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Image image = images.get(position);
        holder.bind(image, context);
        holder.itemView.setOnClickListener(view -> {
            onEventListener.onClickListener(String.valueOf(position));
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }

        public void bind(Image sportPlace, Context context) {
            Glide.with(context)
                    .load(sportPlace.getImageUrl())
                    .centerCrop()
                    .into(ivPicture);
        }
    }
}
