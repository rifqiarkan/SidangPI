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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import aplikasi.rifqiarkan.xsportapp.OnEventListener;
import aplikasi.rifqiarkan.xsportapp.R;
import aplikasi.rifqiarkan.xsportapp.model.Place;
import aplikasi.rifqiarkan.xsportapp.model.PlaceResponse;

public class SportNearbyAdapter extends RecyclerView.Adapter<SportNearbyAdapter.ViewHolder> {

    private Context context;

    private List<Place> places;

    private OnEventListener<String> onEventListener;

    public void setOnEventListener(OnEventListener<String> onEventListener) {
        this.onEventListener = onEventListener;
    }

    public void sortPlacesByDistance() {
        // Menggunakan Comparator untuk mengurutkan tempat berdasarkan jarak terdekat
        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place place1, Place place2) {
                return Double.compare(place1.getDistance(), place2.getDistance());
            }
        });
        notifyDataSetChanged();
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
            onEventListener.onClickListener(place.getName());
        });
    }

    @Override
    public int getItemCount() {
        Integer data = places.size();
        if (data >= 5) {
            return 5;
        } else {
            return data;
        }
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
            tvDistance.setText(sportPlace.getDistance() + "km");
            Glide.with(context)
                    .load(sportPlace.getIcon())
                    .centerCrop()
                    .into(ivPlace);
        }
    }
}
