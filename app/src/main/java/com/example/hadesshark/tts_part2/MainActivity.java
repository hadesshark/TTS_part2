package com.example.hadesshark.tts_part2;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText mShowEditText;
    private Button mTalkBtn;

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowEditText = this.findViewById(R.id.etShow);
        mTalkBtn = this.findViewById(R.id.talkBtn);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.getDefault());
                    tts.setPitch(5);
                    tts.setSpeechRate(2);

                    if ((result == TextToSpeech.LANG_MISSING_DATA) ||
                            (result == TextToSpeech.LANG_NOT_SUPPORTED)) {
                        Toast.makeText(MainActivity.this,
                                "TTS no supported.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "TTS open.",
                                Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(MainActivity.this,
                            "TTS open unsuccess.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        String talkString = mShowEditText.getText().toString();
        tts.speak(talkString, TextToSpeech.QUEUE_FLUSH, null, "Test");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
