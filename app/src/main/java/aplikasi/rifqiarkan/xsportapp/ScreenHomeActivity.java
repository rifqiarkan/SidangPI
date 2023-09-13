package aplikasi.rifqiarkan.xsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class ScreenHomeActivity extends AppCompatActivity {

    MaterialButton buttonTentang, buttonExplore, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);
        initView();
        setupOnClick();
    }

    private void setupOnClick() {
        buttonExplore.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScreenSportActivity.class);
            startActivity(intent);
        });
        buttonTentang.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScreenAboutActivity.class);
            startActivity(intent);
        });
        buttonLogout.setOnClickListener(view -> {
            finish();
        });
    }

    private void initView() {
        buttonExplore = findViewById(R.id.btn_masuk);
        buttonTentang = findViewById(R.id.btn_tentang);
        buttonLogout = findViewById(R.id.btnLogout);


    }

}