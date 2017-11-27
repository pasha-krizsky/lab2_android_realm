package com.example.pasha.lab2_realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pasha.lab2_realm.model.Contact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private Realm realm;

    @BindView(R.id.name_text_input)
    EditText nameText;

    @BindView(R.id.phone_text_input)
    EditText phoneText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: connecting to Realm");
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        Log.i(TAG, "onCreate: setting up ButterKnife");
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: closing Realm");
        realm.close();
    }

    @OnClick(R.id.show_contacts_button)
    public void onShowContactsClick(View view) {

        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.delete_contacts_button)
    public void onDeleteContactsClick(View view) {

        Log.i(TAG, "onDeleteContactsClick: start deleting contacts");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

        Log.i(TAG, "onDeleteContactsClick: contacts was deleted");
        makeToast("Contacts was deleted");
    }

    @OnClick(R.id.add_contact_button)
    public void onAddContactClick(View view) {

        Log.i(TAG, "onAddContactClick: adding new contact");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contact contact = realm.createObject(Contact.class);
                contact.setName(nameText.getText().toString());
                contact.setPhone(phoneText.getText().toString());
            }
        });

        Log.i(TAG, "onAddContactClick: contact was saved");
        makeToast("Contact was saved");
    }

    private void makeToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
