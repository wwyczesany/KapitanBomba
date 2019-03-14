package com.example.kapitanbombastik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.kapitanbombastik.MESSAGE";
    public static final String EXTRA_SEND_DATA = "com.example.kapitanbombastik.SEND_DATA";

    public static boolean isAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Data.isLoaded == false) Data.loadCharacters(this);

    }

    @Override
    protected void onStart(){
        super.onStart();

        if(isAdded == true){

            isAdded = false;
            Player p = (Player) getIntent().getSerializableExtra(EXTRA_SEND_DATA);
            Data.characters.add(p);

        }


        updateCharacterList();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Data.saveCharacters(this);

    }

    private void updateCharacterList(){

        LinearLayout characterList = findViewById(R.id.characterList);

        characterList.removeAllViews();

        //Making buttons with onClick function to stats
        for(final Player i : Data.characters){

            final Button btn = new Button(this);
            btn.setText(i.getName());
            btn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    openCharacterStats(i);
                }

            } );
            btn.setOnLongClickListener(new View.OnLongClickListener(){


                @Override
                public boolean onLongClick(View v) {

                    PopupMenu popupMenu = new PopupMenu(MainActivity.this , btn);
                    popupMenu.getMenuInflater().inflate(R.menu.delete_menu , popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            Data.deleteCharacter(i , MainActivity.this);
                            updateCharacterList();

                            return true;

                        }
                    });

                    popupMenu.show();

                    return true;

                }
            });

            characterList.addView(btn);

        }

    }

    private void openCharacterStats(Player p){

        Intent intent = new Intent(this , CharacterStatsActivity.class);
        intent.putExtra(EXTRA_MESSAGE , Data.characters.indexOf(p));
        startActivity(intent);

    }

    public void createWyjebunda(View view){

        Data.characters.add(new Player("Egzorcysta Boner" , 666 , 666 , 666 , 666 , 666 , 666 , 666 , 666 , 666));
        Data.saveCharacters(this);
        finish();
        startActivity(getIntent());

    }

    public void createCharacter(View view){

        Intent intent = new Intent(this , CreateCharacterActivity.class);
        startActivity(intent);

    }

}
