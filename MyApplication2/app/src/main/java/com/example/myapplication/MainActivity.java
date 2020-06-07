package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Metodo para enviar mensajes a la compostera
     *  @param view objeto tipo View
     *  @return void*/
    public void sendMessage(View view){
        EditText editText = findViewById(R.id.userMessage);
        String texto = editText.getText().toString();

        Intent intent = new Intent(this, ClientActivity.class);
        intent.putExtra("EXTRA_MESSAGE",texto);
        startActivity(intent);
    }
}