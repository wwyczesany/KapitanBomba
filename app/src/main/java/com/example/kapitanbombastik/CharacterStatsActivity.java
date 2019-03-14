package com.example.kapitanbombastik;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterStatsActivity extends AppCompatActivity {

    int index;
    Player currentPlayer;

    ConstraintLayout skillLayout;
    TextView skillName;
    TextView skillDescription;
    TextView skillLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_stats);

        index = (int) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        currentPlayer = Data.characters.get(index);

        skillLayout = findViewById(R.id.template_skill);
        skillName = findViewById(R.id.text_skill_name);
        skillDescription = findViewById(R.id.text_skill_description);
        skillLevel = findViewById(R.id.value_skill_level);

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

            ConstraintLayout constraintLayout = new ConstraintLayout(this);
            ConstraintSet constraintSet = new ConstraintSet();

            skillName.setText(s.getName());
            skillDescription.setText(s.getDescription());
            skillLevel.setText(Integer.toString(s.getLevel()) );

            constraintSet.clone(skillLayout);
            constraintSet.applyTo(constraintLayout);
            constraintLayout.setVisibility(View.VISIBLE);
            layout.addView(constraintLayout);

        }

    }

    public void openInventory(View view){

        Intent intent = new Intent(this , InventoryActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE , index);
        startActivity(intent);

    }


}
