package aplikasi.rifqiarkan.xsportapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import aplikasi.rifqiarkan.xsportapp.utis.DistanceCalculator;

public class SplashActivity extends AppCompatActivity {
    /**
     * FusedLocationProviderClient
     * digunakan untuk mendapatkan latitude & longitude user
     */
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * set variable fusedLocationClient
         */
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /**
         * funtion untuk mendapatkan permission lokasi dari device user
         */
        getLocationPermissions();

    }

    private void getLocationPermissions() {
        Activity activity = this;
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        /**
                         * ketika diberikan akses oleh user, lakukan aksi ini untuk mendapatkan lokasi terkini.
                         */
                        getCurrentLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        /**
                         * ketika tidak diberikan akses oleh user, muncul peringatan permission harus diberikan.
                         */
                        Toast.makeText(activity, "onPermissionDenied", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    /**
                     * ketika lokasi terkini didaptakan, masukkan menjadi variable global
                     * lalu arahkan user ke halaman home
                     */
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        DistanceCalculator.latitudeUser = latitude;
                        DistanceCalculator.longitudeUser = longitude;
                    } else {
                        // Location is null, handle this case
                    }
                    routeToHome();
                });
    }

    private void routeToHome() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goScreenHome = new Intent(SplashActivity.this, ScreenHomeActivity.class);
                startActivity(goScreenHome);
                finish();
            }
        }, 1000);
    }
}
