package aplikasi.rifqiarkan.xsportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import aplikasi.rifqiarkan.xsportapp.adapter.SportAdapter;
import aplikasi.rifqiarkan.xsportapp.model.SportPlace;

public class ScreenSportActivity extends AppCompatActivity {

    public static class BUNDLE {
        public static String KEY_DATA = "KEY_DATA";
        public static String KEY_TITLE = "KEY_TITLE";
    }

    LinearLayout buttonFutsal, buttonBadminton, button;

    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;

    ArrayList<SportPlace> sports = new ArrayList<>();

    SportAdapter sportAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_sport);
        firebaseDatabase = FirebaseDatabase.getInstance();
        getSports();
    }

    private void initView() {
        sportAdapter = new SportAdapter(this, sports);
        recyclerView = findViewById(R.id.rvSport);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(sportAdapter);
        sportAdapter.setOnEventListener(data -> {
            Intent intent = new Intent(this, ScreenDetailSportActivity.class);
            intent.putExtra(BUNDLE.KEY_DATA, data.getId());
            intent.putExtra(BUNDLE.KEY_TITLE, data.getName());
            startActivity(intent);
        });
    }


    private void getSports() {
        getReference("sports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sports.clear();
                Log.d("testFirebase", "onDataChange: ");
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        SportPlace dataResult = dataSnapshot.getValue(SportPlace.class);
                        sports.add(dataResult);
                    }
                    initView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("testFirebase", "onCancelled: ");

            }
        });
    }


    private DatabaseReference getReference(String path) {
        return firebaseDatabase.getReference(path);
    }


}