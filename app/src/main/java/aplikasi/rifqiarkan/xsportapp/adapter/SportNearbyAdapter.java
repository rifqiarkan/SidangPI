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

public class SportNearbyAdapter extends RecyclerView.Adapter<SportNearbyAdapter.ViewHolder> {

    private Context context;

    private List<Place> places;

    private OnEventListener<String> onEventListener;

    public void setOnEventListener(OnEventListener<String> onEventListener) {
        this.onEventListener = onEventListener;
    }

    public SportNearbyAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
    }
    @NonNull
    @Override
    public SportNearbyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_nearby, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportNearbyAdapter.ViewHolder holder, int position) {
        Place place = places.get(position);
        holder.bind(place, context);
        holder.itemView.setOnClickListener(view -> {
            onEventListener.onClickListener(String.valueOf(position));
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDistance;
        ImageView ivPlace;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameDetail);
            tvDistance = itemView.findViewById(R.id.tvDistance);
            ivPlace = itemView.findViewById(R.id.ivPlace);
        }

        public void bind(Place sportPlace, Context context) {
            tvName.setText(sportPlace.getName());
            tvDistance.setText(sportPlace.getLocation());
            Glide.with(context)
                    .load(sportPlace.getIcon())
                    .centerCrop()
                    .into(ivPlace);
        }
    }
}
