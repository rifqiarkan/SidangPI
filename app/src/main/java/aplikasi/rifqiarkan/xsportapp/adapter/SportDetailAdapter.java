package aplikasi.rifqiarkan.xsportapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import aplikasi.rifqiarkan.xsportapp.R;
import aplikasi.rifqiarkan.xsportapp.model.Place;

public class SportDetailAdapter extends RecyclerView.Adapter<SportDetailAdapter.ViewHolder> {

    private Context context;

    private List<Place> places;

    public SportDetailAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
    }
    @NonNull
    @Override
    public SportDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_sport_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportDetailAdapter.ViewHolder holder, int position) {
        Place sportPlace = places.get(position);
        holder.bind(sportPlace);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameDetail);
            tvLocation = itemView.findViewById(R.id.tvLocation);
        }

        public void bind(Place sportPlace) {
            tvName.setText(sportPlace.getName());
            tvLocation.setText(sportPlace.getLocation());
        }
    }
}
