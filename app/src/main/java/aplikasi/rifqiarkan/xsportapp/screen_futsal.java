package aplikasi.rifqiarkan.xsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class screen_futsal extends AppCompatActivity {

    public static class BUNDLE{
        public static String KEY_LOGIN= "KEY_LOGIN";

    }

    MaterialButton buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_futsal);
        initView();
        setupOnClick();
    }

    private void setupOnClick() {
        buttonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScreenSport.class);
            intent.putExtra(BUNDLE.KEY_LOGIN, "arkan@gmail.com");
            startActivity(intent);
        });
    }

    private void initView() {
        buttonLogin = findViewById(R.id.button);
    }
}