package com.example.sun.bai01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> notes= new ArrayList<>();
    private ArrayAdapter<String> adapterNote ;
    ListView lvNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0; i < 20; i++){
            notes.add("Note" + (i + 1));
        }

        lvNote = (ListView) findViewById(R.id.lvNote);
        adapterNote = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                notes);
        lvNote.setAdapter(adapterNote);
        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EditNote.class);
                String value = (String)adapterNote.getItem(position);
                intent.putExtra("Position", position);
                intent.putExtra("Title",value);
                startActivityForResult(intent,1);
            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            String title = data.getStringExtra("Title");
            String position = data.getStringExtra("Position");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnNew:
                Intent intent = new Intent(getApplicationContext(),EditNote.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
