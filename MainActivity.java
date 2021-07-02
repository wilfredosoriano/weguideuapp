package com.example.weguideu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);

    }

    public void Login(View view) {
        String name = etName.getText().toString();
        String pass = etPassword.getText().toString();
        if(name.equals("admin") && pass.equals("1234")){
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "Invalid Details",
                    Toast.LENGTH_SHORT).show();
        }

    }
}