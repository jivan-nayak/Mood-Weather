package com.example.registerationandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NoteTaking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ImageView imageView;
    private Button noteTakingSave, button;
    private String choice, para, mood;
    private EditText paragraph;
    private NoteTakingDBHelper noteTakingDBHelper;
    private TextView noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking);

        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.noteTakingSave);
        paragraph = findViewById(R.id.noteTakingDesc);
        noteTakingSave = findViewById(R.id.noteTakingSave);
        button = findViewById(R.id.button);
        noteText = findViewById(R.id.noteResult);

        noteTakingDBHelper = new NoteTakingDBHelper(this);

        para = paragraph.getText().toString();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mood, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        noteTakingSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                para = paragraph.getText().toString();
                String mood;

                String happy[] = {"fortunate", "lucky", "convenient", "favorable", "fortuitous", "coincidental", "hopeful",
                        "promising", "unexpected", "chance", "bright", "encouraging", "beneficial", "good"};

                String sad[] = {"hopeless", "depressed", "mournful", "despair", "miserable", "awful", "dishearten"};

                String anger[] = {"angry", "furious", "mad", "upset", "pissed off", "livid", "go off"};

                int index = -1;
                int flag = 0;
                while(true) {
                    for (String word : happy) {
                        para.indexOf(word);
                        if (index != -1) {
                            flag = 1;
                            break;
                        }
                    }
                    if(flag == 1){
                        mood = "Happy";
                        break;
                    }

                    for (String word : sad) {
                        para.indexOf(word);
                        if (index != -1) {
                            flag = 2;
                            break;
                        }
                    }
                    if(flag == 2){
                        mood = "sad";
                        break;
                    }

                    for (String word : anger) {
                        para.indexOf(word);
                        if (index != -1) {
                            flag = 3;
                            break;
                        }
                    }
                    if(flag==3){
                        mood = "anger";
                        break;
                    }
                    else{
                        mood = "happy";
                        break;
                    }
                }


                boolean result = noteTakingDBHelper.insertNote(mood, para);
                if(result){
                    Toast.makeText(NoteTaking.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(NoteTaking.this, para, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(NoteTaking.this, "Failed" + mood, Toast.LENGTH_SHORT).show();
                    Toast.makeText(NoteTaking.this, para, Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor;
                cursor = noteTakingDBHelper.getData();
                StringBuffer stringBuffer = new StringBuffer();
                while(cursor.moveToNext()){
                    stringBuffer.append(cursor.getString(1));
                    stringBuffer.append("\n");
                    stringBuffer.append(cursor.getString(2));
                    stringBuffer.append("\n");
                }
                noteText.setText(stringBuffer);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        choice = adapterView.getItemAtPosition(i).toString();
        switch(choice){
            case "Happy":
                imageView.setImageResource(R.drawable.happy);
                break;
            case "Sad":
                imageView.setImageResource(R.drawable.sad);
                break;
            case "Anger":
                imageView.setImageResource(R.drawable.angry);
                break;
//            case "anxious":
//                break;
        }
    }

//    public String getMood(String para){
//        String happy[] = {"fortunate", "lucky", "convenient", "favorable", "fortuitous", "coincidental", "hopeful",
//                "promising", "unexpected", "chance", "bright", "encouraging", "beneficial", "good"};
//
//        String sad[] = {"hopeless", "depressed", "mournful", "despair", "miserable", "awful", "dishearten"};
//
//        String anger[] = {"angry", "furious", "mad", "upset", "pissed off", "livid", "go off"};
//
//        int index = -1;
//        int flag = 0;
//        while(true) {
//            for (String word : happy) {
//                para.indexOf(word);
//                if (index != -1) {
//                    flag = 1;
//                    break;
//                }
//            }
//            if(flag == 1){
//                return "Happy";
//            }
//
//            for (String word : sad) {
//                para.indexOf(word);
//                if (index != -1) {
//                    flag = 2;
//                    break;
//                }
//            }
//            if(flag == 2){
//                return "sad";
//            }
//
//            for (String word : anger) {
//                para.indexOf(word);
//                if (index != -1) {
//                    flag = 3;
//                    break;
//                }
//            }
//            if(flag==3){
//                return "anger";
//            }
//            else{
//                return "happy";
//            }
//        }
//    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}