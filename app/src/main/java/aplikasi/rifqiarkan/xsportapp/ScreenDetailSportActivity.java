package aplikasi.rifqiarkan.xsportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import aplikasi.rifqiarkan.xsportapp.adapter.SportDetailAdapter;
import aplikasi.rifqiarkan.xsportapp.model.Place;

public class ScreenDetailSportActivity extends AppCompatActivity {

    public static class BUNDLE{
        public static final String KEY_ID_SPORT = "KEY_ID_SPORT";
        public static String KEY_ID_PLACE= "KEY_ID_PLACE";
    }

    TextView tvTitle;

    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;

    SportDetailAdapter sportDetailAdapter;

    ArrayList<Place> places = new ArrayList<>();

    String name = "";
    String idSport  = "";

    //fungsi yang pertama kali di jalankan saat halaman dibuka
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_detail_sport);
        firebaseDatabase = FirebaseDatabase.getInstance();
        idSport = getIntent().getStringExtra(ScreenSportActivity.BUNDLE.KEY_DATA);
        name = getIntent().getStringExtra(ScreenSportActivity.BUNDLE.KEY_TITLE);
        getDetailSport(idSport);

    }

    //logic get data detail tempat sport dari firebase
    private void getDetailSport(String id) {
        getReference("sports/"+ (Integer.parseInt(id) - 1) +"/place").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                places.clear();
                Log.d("testFirebase", "onDataChange: ");
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Place dataResult = dataSnapshot.getValue(Place.class);
                        places.add(dataResult);
                    }
                    initView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //untuk mendklarasikan id
    private void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.rvSportDetail);
        sportDetailAdapter = new SportDetailAdapter(this, places);
        sportDetailAdapter.setOnEventListener(position -> {
            Intent intent = new Intent(this, ScreenDetailPlaceActivity.class);
            intent.putExtra(BUNDLE.KEY_ID_PLACE, position);
            intent.putExtra(BUNDLE.KEY_ID_SPORT, idSport);
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sportDetailAdapter);
        tvTitle.setText(name);
    }

    //untuk referensi ke database
    private DatabaseReference getReference(String path) {
        return firebaseDatabase.getReference(path);
    }

}