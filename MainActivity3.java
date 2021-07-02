package com.example.weguideu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    EditText etPhone;
    ImageButton Button_Call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        etPhone = findViewById(R.id.etPhone);
        Button_Call = findViewById(R.id.Button_Call);

        Button_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                if (phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter Number! "
                    ,Toast.LENGTH_SHORT).show();
                }else{
                    String s = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });

    }
}