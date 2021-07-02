package com.example.weguideu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    EditText editText_name, editText_age, editText_address, editText_phone;
    Button button_add;
    ListView lv_pwdList;
    ArrayAdapter pwdArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText_name = findViewById(R.id.editText_name);
        editText_age = findViewById(R.id.editText_age);
        editText_address = findViewById(R.id.editText_address);
        editText_phone = findViewById(R.id.editText_phone);

        button_add = findViewById(R.id.button_add);

        lv_pwdList = findViewById(R.id.PWD_List);

        dataBaseHelper = new DataBaseHelper(MainActivity2.this);

        ShowPWDOnListView(dataBaseHelper);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PWDModelClass pwdModelClass;

                try{
                    pwdModelClass = new PWDModelClass(-1, editText_name.getText().toString(), Integer.parseInt(editText_age.getText().toString()), editText_address.getText().toString(), Integer.parseInt(editText_phone.getText().toString()));
                    boolean success = dataBaseHelper.addOne(pwdModelClass);

                    Toast.makeText(MainActivity2.this, "Success ", Toast.LENGTH_SHORT).show();
                    ShowPWDOnListView(dataBaseHelper);
                }catch (Exception e){
                    Toast.makeText(MainActivity2.this, "Error Adding PWD", Toast.LENGTH_SHORT).show();


                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity2.this);


            }
        });



        lv_pwdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PWDModelClass clickedPWD = (PWDModelClass) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedPWD);
                ShowPWDOnListView(dataBaseHelper);
                Toast.makeText(MainActivity2.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowPWDOnListView(DataBaseHelper dataBaseHelper2) {
        pwdArrayAdapter = new ArrayAdapter<PWDModelClass>(MainActivity2.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_pwdList.setAdapter(pwdArrayAdapter);
    }

    public void Call(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}