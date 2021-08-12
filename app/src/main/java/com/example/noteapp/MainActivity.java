package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn_copy  , btn_pasete;
    TextView textView;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // view
        editText = findViewById(R.id.edit);
        btn_copy = findViewById(R.id.btn_copy);
        btn_pasete = findViewById(R.id.paste_btn);
        textView = findViewById(R.id.text);


        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        if (!(clipboardManager).hasPrimaryClip()){
            btn_pasete.setEnabled(false);
        }

        btn_copy.setOnClickListener(v -> {
            String text = editText.getText().toString();

            if (!text.equals("")){
                ClipData clipData =ClipData.newPlainText("text" , text);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                btn_pasete.setEnabled(true);
            }
        });

        btn_pasete.setOnClickListener(v -> {
            ClipData clipData = clipboardManager.getPrimaryClip();
            ClipData.Item item = clipData.getItemAt(0);
            textView.setText(item.getText().toString());
            Toast.makeText(MainActivity.this, "paseted", Toast.LENGTH_SHORT).show();
        });


    }
}