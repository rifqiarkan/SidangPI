package aplikasi.rifqiarkan.xsportapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import aplikasi.rifqiarkan.xsportapp.model.PlaceResponse;

public class ScreenDetailPlaceActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;

    TextView tvTitlePlace, tvInfo, tvLoc, tvContact, tvOperational, tvPrice;

    ImageView ivLocationMaps;

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

        getReference("sports/" + (Integer.parseInt(idSport) - 1) + "/place/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        // Dapatkan objek dari setiap elemen array.
                        PlaceResponse dataResult = snapshot1.getValue(PlaceResponse.class);

                        // Cek jika ID objek sesuai dengan yang Anda cari.
                        if (dataResult.getName().equals(idPlace)) {
                            // Objek dengan ID yang sesuai ditemukan.
                            // Lakukan apa yang Anda inginkan dengan objek ini.
                            initView(dataResult);

                            // Misalnya, objek sekarang berisi data yang sesuai dengan ID yang Anda cari.
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("datanya", "engga");
            }
        });
    }

    private void initView(PlaceResponse dataResult) {
        //deklarasi id
        tvTitlePlace = findViewById(R.id.tvPlace);
        tvInfo = findViewById(R.id.tvInfo);
        tvLoc = findViewById(R.id.tvLocation);
        tvContact = findViewById(R.id.tvContact);
        tvOperational = findViewById(R.id.tvOperational);
        tvPrice = findViewById(R.id.tvPrice);
        ivLocationMaps = findViewById(R.id.ivLocationMaps);
        Glide.with(this)
                .load(dataResult.getImageMaps())
                .centerCrop()
                .into(ivLocationMaps);
        //nyetak dari database ke view
        tvTitlePlace.setText(dataResult.getName());
        tvInfo.setText(dataResult.getInformation());
        tvLoc.setText(dataResult.getLocation());
        tvContact.setText(dataResult.getPhoneNumber());
        tvOperational.setText(dataResult.getOperational());
        tvPrice.setText(dataResult.getPrice());
        //ivPlace.setText(dataResult.getImages());
        ivLocationMaps.setOnClickListener(view ->
                goToMaps(dataResult.getLatitude(), dataResult.getLongitude())
        );
    }


    void goToMaps(String latitude, String longitude) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + latitude + "," + longitude));
        startActivity(intent);
    }

    //untuk referensi ke database
    private DatabaseReference getReference(String path) {
        return firebaseDatabase.getReference(path);
    }
}