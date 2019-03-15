package com.example.kapitanbombastik;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterStatsActivity extends AppCompatActivity {

    int index;
    Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_stats);

        index = (int) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        currentPlayer = Data.characters.get(index);

        updateStats(currentPlayer);

    }

    @Override
    protected void onStop() {
        super.onStop();

        Data.saveCharacters(this);

    }

    public void increaseExperience(View view){

        EditText exp = findViewById(R.id.input_exp_amount);
        int expAmount = Integer.parseInt(exp.getText().toString());

        if(expAmount > 0) {
            currentPlayer.addExp(expAmount);
            Data.characters.set(index, currentPlayer);
            updateStats(currentPlayer);
        }

    }

    public void upgradeStat(View view){
        if(currentPlayer.getSkillPoints() > 0) {
            switch (view.getId()) {

                case R.id.add_str:
                    currentPlayer.addStr();
                    break;
                case R.id.add_dex:
                    currentPlayer.addDex();
                    break;
                case R.id.add_def:
                    currentPlayer.addDef();
                    break;
                case R.id.add_res:
                    currentPlayer.addResist();
                    break;
                case R.id.add_int:
                    currentPlayer.addInt();
                    break;
                case R.id.add_knowledge:
                    currentPlayer.addKnowledge();
                    break;
                case R.id.add_charisma:
                    currentPlayer.addCharisma();
                    break;

            }

            currentPlayer.calcMaxValues();
            currentPlayer.fullHeal();
            Data.characters.set(index , currentPlayer);

        }

        updateStats(currentPlayer);

    }

    private void updateStats(Player player){

        TextView txt = findViewById(R.id.name);
        txt.setText(player.getName());
        txt = findViewById(R.id.level_value);
        txt.setText(Integer.toString(player.getLevel()) );
        txt = findViewById(R.id.value_str);
        txt.setText(Integer.toString(player.getStr()) );
        txt = findViewById(R.id.value_dex);
        txt.setText(Integer.toString(player.getDex()) );
        txt = findViewById(R.id.value_def);
        txt.setText(Integer.toString(player.getDef()) );
        txt = findViewById(R.id.value_resist);
        txt.setText(Integer.toString(player.getResist()) );
        txt = findViewById(R.id.value_int);
        txt.setText(Integer.toString(player.getInteligence()) );
        txt = findViewById(R.id.value_knowledge);
        txt.setText(Integer.toString(player.getKnowledge()) );
        txt = findViewById(R.id.value_charisma);
        txt.setText(Integer.toString(player.getCharisma()) );
        txt = findViewById(R.id.value_skill_points);
        txt.setText(Integer.toString(player.getSkillPoints()) );

        ProgressBar bar = findViewById(R.id.bar_hp);
        bar.setMax(player.getHpMax());
        bar.setProgress(player.getHp());
        txt = findViewById(R.id.hp_progress);
        txt.setText(Integer.toString(player.getHp()) + "/" + Integer.toString(player.getHpMax()));
        bar = findViewById(R.id.bar_armour);
        bar.setMax(player.getArmorMax());
        bar.setProgress(player.getArmor());
        txt = findViewById(R.id.armor_progress);
        txt.setText(Integer.toString(player.getArmor()) + "/" + Integer.toString(player.getArmorMax()));
        bar = findViewById(R.id.bar_kutas);
        bar.setMax(player.getKutasMax());
        bar.setProgress(player.getKutas());
        txt = findViewById(R.id.kutas_progress);
        txt.setText(Integer.toString(player.getKutas()) + "/" + Integer.toString(player.getKutasMax()));
        bar = findViewById(R.id.bar_exp);
        bar.setMax(player.getExpMax());
        bar.setProgress(player.getExp());
        txt = findViewById(R.id.exp_progress);
        txt.setText(Integer.toString(player.getExp()) + "/" + Integer.toString(player.getExpMax()));

        updateSkillsView((LinearLayout) findViewById(R.id.layout_skills));

    }

    private void updateSkillsView(LinearLayout layout){

        layout.removeAllViews();

        for(Skills.Skill s : currentPlayer.skills.skillArrayList){

            //Making ConstraintLayout

            ConstraintLayout constraintLayout = new ConstraintLayout(this);
            constraintLayout.setId(R.id.skillLayout);
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.GRAY));
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0 , 0 , 0 , 16);
            constraintLayout.setLayoutParams(layoutParams);

            ConstraintSet constraintSet = new ConstraintSet();

            //Adding name

            TextView textName = new TextView(this);
            textName.setText(s.getName());
            textName.setId(R.id.skillName);
            textName.setTextColor(getResources().getColor(R.color.ASS));
            textName.setTypeface(null , Typeface.BOLD);
            textName.setTextSize(16);
            constraintLayout.addView(textName);

            constraintSet.constrainHeight(textName.getId() , ConstraintSet.WRAP_CONTENT);
            constraintSet.constrainWidth(textName.getId() , ConstraintSet.WRAP_CONTENT);
            constraintSet.connect(textName.getId() , ConstraintSet.LEFT , ConstraintSet.PARENT_ID , ConstraintSet.LEFT , convertDpToPx(8));
            constraintSet.connect(textName.getId() , ConstraintSet.TOP , ConstraintSet.PARENT_ID , ConstraintSet.TOP , convertDpToPx(8));
            constraintSet.connect(textName.getId() , ConstraintSet.BOTTOM , ConstraintSet.PARENT_ID , ConstraintSet.BOTTOM , convertDpToPx(8));

            //Adding Upgrade Button
            ImageButton imageButton = new ImageButton(this);
            imageButton.setId(R.id.skillUpgradeButton);
            imageButton.setBackgroundColor(Color.TRANSPARENT);
            imageButton.setImageResource(R.drawable.plus);
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageButton.setPadding(0,0,0,0);



            constraintLayout.addView(imageButton);

            constraintSet.constrainHeight(imageButton.getId() , convertDpToPx(19) );
            constraintSet.constrainWidth(imageButton.getId() , convertDpToPx(19) );
            constraintSet.connect(imageButton.getId() , ConstraintSet.RIGHT , ConstraintSet.PARENT_ID , ConstraintSet.RIGHT , convertDpToPx(8));
            constraintSet.connect(imageButton.getId() , ConstraintSet.TOP , ConstraintSet.PARENT_ID , ConstraintSet.TOP , convertDpToPx(8));
            constraintSet.connect(imageButton.getId() , ConstraintSet.BOTTOM , ConstraintSet.PARENT_ID , ConstraintSet.BOTTOM , convertDpToPx(8));

            //Adding Level Value
            TextView textLevelValue = new TextView(this);
            textLevelValue.setText(Integer.toString(s.getLevel()));
            textLevelValue.setId(R.id.skillLevelValue);
            textLevelValue.setTextColor(getResources().getColor(R.color.BADASS));
            textLevelValue.setTextSize(16);
            constraintLayout.addView(textLevelValue);

            constraintSet.constrainHeight(textLevelValue.getId() , ConstraintSet.WRAP_CONTENT);
            constraintSet.constrainWidth(textLevelValue.getId() , ConstraintSet.WRAP_CONTENT);
            constraintSet.connect(textLevelValue.getId() , ConstraintSet.RIGHT, imageButton.getId() , ConstraintSet.LEFT, convertDpToPx(8));
            constraintSet.connect(textLevelValue.getId() , ConstraintSet.TOP , ConstraintSet.PARENT_ID , ConstraintSet.TOP , convertDpToPx(8));
            constraintSet.connect(textLevelValue.getId() , ConstraintSet.BOTTOM , ConstraintSet.PARENT_ID , ConstraintSet.BOTTOM , convertDpToPx(8));

            //Adding Level Text
            TextView textLevel = new TextView(this);
            textLevel.setText(R.string.text_level);
            textLevel.setId(R.id.skillLevelText);
            textLevel.setTextSize(16);
            textLevel.setTextColor(getResources().getColor(R.color.ASS));
            constraintLayout.addView(textLevel);

            constraintSet.constrainHeight(textLevel.getId() , ConstraintSet.WRAP_CONTENT);
            constraintSet.constrainWidth(textLevel.getId() , ConstraintSet.WRAP_CONTENT);
            constraintSet.connect(textLevel.getId() , ConstraintSet.RIGHT , textLevelValue.getId() , ConstraintSet.LEFT , convertDpToPx(8));
            constraintSet.connect(textLevel.getId() , ConstraintSet.TOP , ConstraintSet.PARENT_ID , ConstraintSet.TOP , convertDpToPx(8));
            constraintSet.connect(textLevel.getId() , ConstraintSet.BOTTOM , ConstraintSet.PARENT_ID , ConstraintSet.BOTTOM , convertDpToPx(8));

            //Adding Skill Description
            TextView textDescription = new TextView(this);
            textDescription.setText(s.getDescription());
            textDescription.setId(R.id.skillDescription);
            textDescription.setTextSize(16);
            textDescription.setTextColor(getResources().getColor(R.color.BADASS));
            constraintLayout.addView(textDescription);

            constraintSet.constrainHeight(textDescription.getId() , ConstraintSet.WRAP_CONTENT);
            constraintSet.constrainWidth(textDescription.getId() , ConstraintSet.MATCH_CONSTRAINT);
            constraintSet.connect(textDescription.getId() , ConstraintSet.RIGHT ,textLevel.getId() , ConstraintSet.LEFT , convertDpToPx(8));
            constraintSet.connect(textDescription.getId() , ConstraintSet.LEFT , textName.getId() , ConstraintSet.RIGHT , convertDpToPx(8));
            constraintSet.connect(textDescription.getId() , ConstraintSet.TOP , ConstraintSet.PARENT_ID , ConstraintSet.TOP , convertDpToPx(8));
            constraintSet.connect(textDescription.getId() , ConstraintSet.BOTTOM , ConstraintSet.PARENT_ID , ConstraintSet.BOTTOM , convertDpToPx(8));






            constraintSet.applyTo(constraintLayout);
            layout.addView(constraintLayout);

        }

    }

    public void openInventory(View view){

        Intent intent = new Intent(this , InventoryActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE , index);
        startActivity(intent);

    }

    private int convertDpToPx(int dp){
        return Math.round(dp*(getResources().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));

    }

    private int convertPxToDp(int px){
        return Math.round(px/(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
    }


}
