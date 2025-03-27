package com.example.mobile;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    // onCreate

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TextView aluno = new TextView(this);
        aluno.setText("Allison");
        setContentView(aluno);*/
        setContentView(R.layout.activity_main);
    }
}
