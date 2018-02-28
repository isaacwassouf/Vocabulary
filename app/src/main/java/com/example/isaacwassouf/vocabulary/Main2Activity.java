package com.example.isaacwassouf.vocabulary;

import android.sax.EndElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {



    EditText Eng;
    EditText Ara;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Eng = findViewById(R.id.editText);
        Ara = findViewById(R.id.editText2);
        database = new Database(this);
    }


    public void addVoca(View view) {

        String engWord= Eng.getText().toString();
        String araWord= Ara.getText().toString();

        if (engWord.isEmpty() || araWord.isEmpty()){
            Toast.makeText(this, "one of the fields is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        database.add(engWord,araWord);
        Eng.setText("");
        Ara.setText("");
    }
}
