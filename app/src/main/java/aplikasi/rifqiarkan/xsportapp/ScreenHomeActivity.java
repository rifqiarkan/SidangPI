package aplikasi.rifqiarkan.xsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class ScreenHomeActivity extends AppCompatActivity {

    public static class BUNDLE {
        public static String KEY_LOGIN = "KEY_LOGIN";
        public static String KEY_TENTANG = "KEY_TENTANG";

    }

    MaterialButton buttonTentang, buttonLogin, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);
        initView();
        setupOnClick();
    }

    private void setupOnClick() {
        buttonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScreenSportActivity.class);
            startActivity(intent);
        });
        buttonTentang.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScreenTentang.class);
            startActivity(intent);
        });
        buttonLogout.setOnClickListener(view -> {
            finish();
        });
    }

    private void initView() {
        buttonLogin = findViewById(R.id.btn_masuk);
        buttonTentang = findViewById(R.id.btn_tentang);
        buttonLogout = findViewById(R.id.btnLogout);


    }

}