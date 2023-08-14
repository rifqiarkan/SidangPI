package aplikasi.rifqiarkan.xsportapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import aplikasi.rifqiarkan.xsportapp.OnEventListener;
import aplikasi.rifqiarkan.xsportapp.R;
import aplikasi.rifqiarkan.xsportapp.model.Place;

public class SportDetailAdapter extends RecyclerView.Adapter<SportDetailAdapter.ViewHolder> {

    private Context context;

    private List<Place> places;

    private OnEventListener<String> onEventListener;

    public void setOnEventListener(OnEventListener<String> onEventListener) {
        this.onEventListener = onEventListener;
    }

    public SportDetailAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
    }
    @NonNull
    @Override
    public SportDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_sport, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportDetailAdapter.ViewHolder holder, int position) {
        Place placeResponse = places.get(position);
        holder.bind(placeResponse, context);
        holder.itemView.setOnClickListener(view -> {
            onEventListener.onClickListener(String.valueOf(position));
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation;
        ImageView ivPlace;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameDetail);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            ivPlace = itemView.findViewById(R.id.ivPlace);
        }

        public void bind(Place sportPlaces, Context context) {
            tvName.setText(sportPlaces.getName());
            tvLocation.setText(sportPlaces.getLocation());
            Glide.with(context)
                    .load(sportPlaces.getIcon())
                    .centerCrop()
                    .into(ivPlace);
        }
    }
}
