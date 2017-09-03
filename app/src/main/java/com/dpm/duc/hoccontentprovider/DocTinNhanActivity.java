package com.dpm.duc.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DocTinNhanActivity extends AppCompatActivity {
    ListView lvTinNhan;
    ArrayList<String> arrTinNhan;
    ArrayAdapter<String> adapterTinNhan;
    Date date = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_tin_nhan);
        addControls();
        addEvents();
        loadTinNhanFromDevide();
    }

    private void loadTinNhanFromDevide() {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        arrTinNhan.clear();
        while (cursor.moveToNext()) {
            //get index collum
            int indexPhoneNumber = cursor.getColumnIndex("address");
            int indexTimeStamp = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");

            String phone = cursor.getString(indexPhoneNumber);
            long timeMilisecond = cursor.getLong(indexTimeStamp);
            String body = cursor.getString(indexBody);

            date = new Date(timeMilisecond);
            String timeStamp = dateFormat.format(date);

            String message = phone + "\n" + timeStamp + ":\t" + body;
            arrTinNhan.add(message);
        }
        cursor.close();
        adapterTinNhan.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvTinNhan = (ListView) findViewById(R.id.lvTinNhan);
        arrTinNhan = new ArrayList<>();
        adapterTinNhan = new ArrayAdapter<String>(DocTinNhanActivity.this, android.R.layout.simple_list_item_1, arrTinNhan);
        lvTinNhan.setAdapter(adapterTinNhan);

    }
}
