package com.dpm.duc.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dpm.duc.model.Contact;

import java.util.ArrayList;

public class DanhBaActivity extends AppCompatActivity {

    ListView lvDanhBa;
    ArrayList<Contact> arrContact;
    ArrayAdapter<Contact> adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        addControls();
        addEvents();
        showAllContactFromDevice();
    }

    private void showAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        arrContact.clear();
        while (cursor.moveToNext()) {
            //Get name
            String idName = ContactsContract.Contacts.DISPLAY_NAME;
            int colNameIndex = cursor.getColumnIndex(idName);
            String name = cursor.getString(colNameIndex);

            //Get phone
            String idPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int colPhoneIndex = cursor.getColumnIndex(idPhone);
            String phone = cursor.getString(colPhoneIndex);

            arrContact.add(new Contact(name, phone));
        }
        cursor.close();
        adapterContact.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        arrContact = new ArrayList<>();
        adapterContact = new ArrayAdapter<Contact>(DanhBaActivity.this, android.R.layout.simple_list_item_1, arrContact);
        lvDanhBa.setAdapter(adapterContact);
    }
}
