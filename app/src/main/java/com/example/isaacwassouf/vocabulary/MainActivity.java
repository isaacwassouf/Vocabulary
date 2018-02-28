package com.example.isaacwassouf.vocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToaddVoca(View view) {
        intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    public void goTodeleteVoca(View view) {
        intent = new Intent(this,Main4Activity.class);
        startActivity(intent);
    }

    public void goToshowVocs(View view) {
        intent = new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}
