package com.dpm.duc.hoccontentprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDocDanhBa, btnDocTinNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();
    }

    private void addEvents() {
        btnDocDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DanhBaActivity.class);
                startActivity(intent);
            }
        });

        btnDocTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DocTinNhanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        btnDocDanhBa = (Button) findViewById(R.id.btnDocDanhBa);
        btnDocTinNhan = (Button) findViewById(R.id.btnDocTinNhan);
    }
}
