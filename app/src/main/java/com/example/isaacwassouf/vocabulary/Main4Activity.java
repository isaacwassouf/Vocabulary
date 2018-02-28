package com.example.isaacwassouf.vocabulary;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    ListView listView;
    Database database;
    ArrayList<String> toDelete;
    Custom2listView custom2listView;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView= findViewById(R.id.listview2);
        toDelete = new ArrayList<>();
        database = new Database(this);
        cursor = database.getEnglish();
        custom2listView = new Custom2listView(this,cursor);
        listView.setAdapter(custom2listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void deleteVoca(MenuItem item) {
        try {
            database.delete(toDelete);
            cursor = database.getEnglish();
            custom2listView = new Custom2listView(this, cursor);
            listView.setAdapter(custom2listView);
            toDelete = new ArrayList<>();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    class Custom2listView extends CursorAdapter{


        public Custom2listView(Context context, Cursor c) {
            super(context, c,0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return LayoutInflater.from(context).inflate(R.layout.row_layout2,viewGroup,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            final String word=cursor.getString(cursor.getColumnIndexOrThrow("English"));
            checkBox.setText(word);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()){
                        toDelete.add(word);
                    }
                }
            });
        }
    }



}
