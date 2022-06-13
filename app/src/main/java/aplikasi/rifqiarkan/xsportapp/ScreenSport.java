package aplikasi.rifqiarkan.xsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

public class ScreenSport extends AppCompatActivity {

    public static class BUNDLE{
        public static String KEY_FUTSAL = "KEY_FUTSAL";
    }

    LinearLayout buttonFutsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_sport);
        String KEY_FROM_LOGIN = getIntent().getStringExtra(screen_futsal.BUNDLE.KEY_LOGIN);
        initView();
        setupOnClick();
    }

    private void setupOnClick() {
        buttonFutsal.setOnClickListener(view -> {

        });
    }

    private void initView() {
        buttonFutsal = findViewById(R.id.icFutsal);
    }
}