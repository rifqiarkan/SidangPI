package aplikasi.rifqiarkan.xsportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import aplikasi.rifqiarkan.xsportapp.model.SportPlace;

public class ScreenDetailSportActivity extends AppCompatActivity {

    public static class BUNDLE{
        public static String KE_LOGIN= "EY_LOGIN";

    }

    TextView tvTitle;

    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;

    SportDetailAdapter sportDetailAdapter;

    ArrayList<Place> places = new ArrayList<>();

    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_futsal);
        firebaseDatabase = FirebaseDatabase.getInstance();
        String id = getIntent().getStringExtra(ScreenSportActivity.BUNDLE.KEY_DATA);
        name = getIntent().getStringExtra(ScreenSportActivity.BUNDLE.KEY_TITLE);
        getDetailSport(id);
    }

    private void getDetailSport(String id) {
        getReference("sports/"+String.valueOf(Integer.parseInt(id) - 1)+"/place").addValueEventListener(new ValueEventListener() {
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


    private void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.rvSportDetail);
        sportDetailAdapter = new SportDetailAdapter(this, places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sportDetailAdapter);
        tvTitle.setText(name);
    }

    private DatabaseReference getReference(String path) {
        return firebaseDatabase.getReference(path);
    }

}