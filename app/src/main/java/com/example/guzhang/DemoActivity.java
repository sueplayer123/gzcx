package com.example.guzhang;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        Bundle bundle = getIntent().getExtras();
        String dataString2 = bundle.getString("data2");
        textView1.setText(dataString2);
        textView1.setMovementMethod(new ScrollingMovementMethod());
    }
}