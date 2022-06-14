package aplikasi.rifqiarkan.xsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.FirebaseDatabase;

public class ScreenSport extends AppCompatActivity {

    public static class BUNDLE{
        public static String KEY_FUTSAL = "KEY_FUTSAL";
        public static String KEY_BADMINTON = "KEY_BADMINTON";
        public static String KEY_SWIMMING = "KEY_SWIMMING";
    }

    LinearLayout buttonFutsal, buttonBadminton, button;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_sport);
        String KEY_FROM_LOGIN = getIntent().getStringExtra(ScreenHome.BUNDLE.KEY_LOGIN);
        initView();
        setupOnClick();
    }

    private void setupOnClick() {
        buttonFutsal.setOnClickListener(view -> {
            Intent intent = new Intent(this, screen_futsal.class);
            intent.putExtra(BUNDLE.KEY_FUTSAL,"KEY_FUTSAL");
            startActivity(intent);

        });
        buttonBadminton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScreenBadminton.class);
            intent.putExtra(BUNDLE.KEY_BADMINTON,"KEY_BADMINTON");
            startActivity(intent);
        });

    }

    private void initView() {
        buttonFutsal = findViewById(R.id.icFutsal);
        buttonBadminton = findViewById(R.id.icBadminton);
    }



}