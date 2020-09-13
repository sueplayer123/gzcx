package com.example.guzhang;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        Bundle bundle = getIntent().getExtras();
        String dataString3 = bundle.getString("data3");
        textView1.setText(dataString3);
        textView1.setMovementMethod(new ScrollingMovementMethod());
    }
}