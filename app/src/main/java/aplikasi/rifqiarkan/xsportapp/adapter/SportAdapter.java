package aplikasi.rifqiarkan.xsportapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import aplikasi.rifqiarkan.xsportapp.OnEventListener;
import aplikasi.rifqiarkan.xsportapp.R;
import aplikasi.rifqiarkan.xsportapp.model.SportPlace;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    private Context context;

    public void setOnEventListener(OnEventListener<SportPlace> onEventListener) {
        this.onEventListener = onEventListener;
    }

    private OnEventListener<SportPlace> onEventListener;

    private List<SportPlace> sports;

    public SportAdapter(Context context, List<SportPlace> sports) {
        this.context = context;
        this.sports = sports;
    }
    @NonNull
    @Override
    public SportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sport, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.ViewHolder holder, int position) {
        SportPlace sportPlace = sports.get(position);
        holder.bind(sportPlace);
        holder.itemView.setOnClickListener(view -> {
            onEventListener.onClickListener(sportPlace);
        });
    }

    @Override
    public int getItemCount() {
        return sports.size() == 0 ? 0 : sports.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(SportPlace sportPlace) {
            tvName.setText(sportPlace.getName());

        }
    }
}
