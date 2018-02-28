package com.example.isaacwassouf.vocabulary;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main3Activity extends AppCompatActivity {

    ListView listView;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView = findViewById(R.id.listview);
        database= new Database(this);
        Cursor cursor = database.show();
        CustomlistView customlistView = new CustomlistView(this,cursor);
        listView.setAdapter(customlistView);
    }





    class CustomlistView extends CursorAdapter{

        public CustomlistView(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return LayoutInflater.from(context).inflate(R.layout.row_layout,viewGroup,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            TextView engTextView = view.findViewById(R.id.textView);
            TextView araTextView = view.findViewById(R.id.textView3);

            String english = cursor.getString(cursor.getColumnIndexOrThrow("English"));
            String arabic = cursor.getString(cursor.getColumnIndexOrThrow("Arabic"));

            engTextView.setText(english);
            araTextView.setText(arabic);



        }
    }


}
