package com.example.isaacwassouf.vocabulary;

import android.content.Intent;
import android.net.Uri;
import android.sax.EndElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;

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

        if (engWord.isEmpty() && araWord.isEmpty()){
            Toast.makeText(this, "Both of the fields are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(engWord.isEmpty()){
            Toast.makeText(this, "Source word is empty", Toast.LENGTH_SHORT).show();
        }
        else if (araWord.isEmpty()){
            Toast.makeText(this, "you didn\'t enter a meaning", Toast.LENGTH_SHORT).show();
        }
        else {
            database.add(engWord,araWord);
            Eng.setText("");
            Ara.setText("");
        }
    }

    public void openBrowser(View view) {
        String sourceWord= Eng.getText().toString();

        if(sourceWord.isEmpty()){
            Toast.makeText(this, "you haven\'t entered a word ", Toast.LENGTH_SHORT).show();
        }
        else {
            String url =  "https://translate.google.com/#en/ar/"+sourceWord;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

    }
}
