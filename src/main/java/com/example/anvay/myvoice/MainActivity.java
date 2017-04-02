package com.example.anvay.myvoice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected static final int RESULT_SPEECH=1;

    private TextView txtText;
    private ImageButton btnSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtText=(TextView)findViewById(R.id.txtText);
        btnSpeak=(ImageButton)findViewById(R.id.btnSpeak);



        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-UK");

                try {
                    startActivityForResult(i, RESULT_SPEECH);
                    txtText.setText("");
                }catch (ActivityNotFoundException a){
                    Toast t=Toast.makeText(getApplicationContext(), "Ooops! Fuck Your Device",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case RESULT_SPEECH:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));
                }
                break;
            }
        }
    }
}
