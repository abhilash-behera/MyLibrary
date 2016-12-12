package com.developer.abhilash.mylibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class References extends AppCompatActivity {
    ArrayAdapter<String> booksAdapter;
    ListView booksListView;
    SQLiteDatabase db;
    Boolean processing=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        booksListView=(ListView)findViewById(R.id.booksListView);
        booksAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_list_item);

        Intent i=getIntent();
        final int chapter_no=i.getIntExtra("chapter_no",99);
        switch (chapter_no){
            case 0:
                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getReferences(0);
                    }
                });t.start();
                break;
            case 1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getReferences(1);
                    }
                }).start();
                break;
            case 2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getReferences(2);
                    }
                }).start();
                break;
            case 3:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getReferences(3);
                    }
                }).start();
                break;
            default:
                Toast.makeText(References.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        while(processing);
        booksListView.setAdapter(booksAdapter);

        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(References.this,Chapter.class);
                intent.putExtra("book_id",position);
                intent.putExtra("chapter_no",chapter_no);
                startActivity(intent);
            }
        });
    }

    public void getReferences(int chapter_no){
        db=openOrCreateDatabase("mylibrary",MODE_WORLD_WRITEABLE,null);
        Cursor c=db.rawQuery("SELECT * FROM books",null);
        while(c.moveToNext()){
            String book="";
            book+="Name : "+c.getString(1)+"\n"+"Author : "+c.getString(2)+"\n"+"Edition : "+c.getString(3)+"\n";
            booksAdapter.add(book);
        }
        processing=false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(References.this,MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
