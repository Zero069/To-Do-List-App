package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemEditText;
    private Button btn;
    private ListView listOfItem;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemEditText = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);
        listOfItem = findViewById(R.id.list);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items);
        listOfItem.setAdapter(adapter);

        btn.setOnClickListener(this);
        listOfItem.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered = itemEditText.toString();
                adapter.add(itemEntered);
                itemEditText.setText("");

                FileHelper.saveData(items, this);
                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
            break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        items.remove(i);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }
}
