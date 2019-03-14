package com.example.kapitanbombastik;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {

    private static String fileName = "characters";
    public static boolean isLoaded = false;

    public static ArrayList<Player> characters = new ArrayList<>();

    public static void deleteCharacter(Player p , Context context){

        characters.remove(p);
        saveCharacters(context);

    }

    public static void saveCharacters(Context context){

        try{

            FileOutputStream fo = context.openFileOutput(fileName , Context.MODE_PRIVATE);
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            oo.writeInt(characters.size());

            for(Player p : characters){
                oo.writeObject(p);
            }

            oo.close();
            fo.close();


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void loadCharacters(Context context) {

        try {

            FileInputStream fi = context.openFileInput(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

            int objectsCount = oi.readInt();

            for(int i = 0 ; i < objectsCount ; i++){
                characters.add((Player) oi.readObject());
            }

            oi.close();
            fi.close();

        }catch(Exception e) {
            e.printStackTrace();
        }

        isLoaded = true;

    }

    private void loadEnemies(){



    }

}
