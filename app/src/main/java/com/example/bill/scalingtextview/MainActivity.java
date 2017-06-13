package com.example.bill.scalingtextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ExpandableTextView expandableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableTextView = (ExpandableTextView) findViewById(R.id.expand);

        expandableTextView.setText("发色纺纱的方式的方式的方式的方式的发色纺纱的方式的方式的方式的方式的发色纺纱的方式的发色纺纱的方式的的方式的");
        expandableTextView.setShowUpFoldBtn(true);
    }
}
