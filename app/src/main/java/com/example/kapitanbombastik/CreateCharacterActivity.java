package com.example.kapitanbombastik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

public class CreateCharacterActivity extends AppCompatActivity {

    public static final String EXTRA_CHARACTER_CREATED = "com.example.kapitanbombastik.EXTRA_CHARACTER_CREATED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Data.saveCharacters(this);

    }

    public void create(View view){

        Intent intent = new Intent(this , MainActivity.class);

        EditText name = findViewById(R.id.input_name);
        Player player = new Player(name.getText().toString());
        MainActivity.isAdded = true;

        intent.putExtra(MainActivity.EXTRA_SEND_DATA , player);
        startActivity(intent);

    }

}
