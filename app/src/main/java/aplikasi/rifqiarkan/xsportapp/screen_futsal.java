package aplikasi.rifqiarkan.xsportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class screen_futsal extends AppCompatActivity {

    public static class BUNDLE{
        public static String KE_LOGIN= "EY_LOGIN";

    }

    MaterialButton butonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_futsal);
        String KEY_FROM_FUTSAL = getIntent().getStringExtra(ScreenSport.BUNDLE.KEY_FUTSAL);
    }
}