package aplikasi.rifqiarkan.xsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ScreenHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goScreenSport = new Intent(ScreenHome.this, ScreenSport.class);
                startActivity(goScreenSport);
                finish();
            }
        },10000);
    }
}