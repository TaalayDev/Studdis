package com.zoro.studdis.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zoro.studdis.R;
// import com.zoro.studdis.ui.login.UI.RegisterActivity;
import com.zoro.studdis.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity {


    Button loginBtn;
    TextView registerTV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        initdate();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private void initdate() {
        /*registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                // startActivity(intent);
            }
        });*/
    }

    private void initUI() {
        // loginBtn = findViewById(R.id.loginBtn);
        // registerTV = findViewById(R.id.registerTV);
    }

}
