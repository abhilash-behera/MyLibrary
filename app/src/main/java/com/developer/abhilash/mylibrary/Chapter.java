package com.developer.abhilash.mylibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Chapter extends AppCompatActivity {

    TextView topic;
    TextView content;
    String topicString;
    String contentString;
    int new_chapter_no;
    boolean processing=true;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        Intent intent=getIntent();
        final int chapter_no=intent.getIntExtra("chapter_no", 99);
        final int book_id=intent.getIntExtra("book_id",99);

        topic=(TextView)findViewById(R.id.topic);
        content=(TextView)findViewById(R.id.content);

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                getChapter(book_id+1,chapter_no+1);
            }
        });t.start();
        while(processing);

        topic.setText(topicString);
        content.setText(contentString);

        topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(topic.getText().toString().compareToIgnoreCase("acid")==0)
                    new_chapter_no=0;
                else if(topic.getText().toString().compareToIgnoreCase("base")==0)
                    new_chapter_no=1;
                else if(topic.getText().toString().compareToIgnoreCase("salt")==0)
                    new_chapter_no=2;
                else if(topic.getText().toString().compareToIgnoreCase("chemical reactions")==0)
                    new_chapter_no=3;
                Intent i=new Intent(Chapter.this,References.class);
                i.putExtra("chapter_no",new_chapter_no);
                startActivity(i);

            }
        });
    }
    public void getChapter(int book_id,int chapter_no){
        db=openOrCreateDatabase("mylibrary",MODE_WORLD_WRITEABLE,null);
        Cursor c=db.rawQuery("SELECT * FROM chapters",null);
        while(c.moveToNext()){
            if(c.getString(1).compareToIgnoreCase(String.valueOf(book_id))==0&&c.getString(2).compareToIgnoreCase(String.valueOf(chapter_no))==0){
                topicString=c.getString(3);
                contentString=c.getString(4);
            }
        }

        processing=false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
