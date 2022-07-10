package aplikasi.rifqiarkan.xsportapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import aplikasi.rifqiarkan.xsportapp.adapter.SportDetailPlaceAdapter;
import aplikasi.rifqiarkan.xsportapp.model.Place;

public class ScreenDetailPlaceActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;

    TextView tvTitlePlace, tvInfo, tvLoc, tvContact, tvOperational;

    LinearLayout ctaGmaps;

    RecyclerView rvImages;

    private SportDetailPlaceAdapter sportDetailPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_detail_place);
        String idSport = getIntent().getStringExtra(ScreenDetailSportActivity.BUNDLE.KEY_ID_SPORT);
        String idPlace = getIntent().getStringExtra(ScreenDetailSportActivity.BUNDLE.KEY_ID_PLACE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        getDetailPlace(idSport, idPlace);
    }

    private void getDetailPlace(String idSport, String idPlace) {
        Log.d("datanya", idSport);

        getReference("sports/" + (Integer.parseInt(idSport) - 1) + "/place/" + idPlace).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Place dataResult = snapshot.getValue(Place.class);
                    initView(dataResult);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("datanya", "engga");
            }
        });
    }

    private void initView(Place dataResult) {
        //deklarasi id
        tvTitlePlace = findViewById(R.id.tvPlace);
        tvInfo = findViewById(R.id.tvInfo);
        tvLoc = findViewById(R.id.tvLocation);
        tvContact = findViewById(R.id.tvContact);
        ctaGmaps = findViewById(R.id.ctaGmaps);
        tvOperational = findViewById(R.id.tvOperational);
        sportDetailPlaceAdapter = new SportDetailPlaceAdapter(this, dataResult.getImages());
        rvImages = findViewById(R.id.rvImage);
        rvImages.setAdapter(sportDetailPlaceAdapter);
        rvImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        //nyetak dari database ke view
        tvTitlePlace.setText(dataResult.getName());
        tvInfo.setText(dataResult.getInformation());
        tvLoc.setText(dataResult.getLocation());
        tvContact.setText(dataResult.getPhoneNumber());
        tvOperational.setText(dataResult.getOperational());
        //ivPlace.setText(dataResult.getImages());
        ctaGmaps.setOnClickListener(view ->
                goToMaps(dataResult.getLatitude(), dataResult.getLongitude())
        );
    }


    void goToMaps(String latitude, String longitude) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + latitude+ "," + longitude));
        startActivity(intent);
    }

    //untuk referensi ke database
    private DatabaseReference getReference(String path) {
        return firebaseDatabase.getReference(path);
    }
}