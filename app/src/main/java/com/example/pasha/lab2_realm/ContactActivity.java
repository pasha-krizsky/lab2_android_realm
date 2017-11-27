package com.example.pasha.lab2_realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.example.pasha.lab2_realm.adapter.ContactAdapter;
import com.example.pasha.lab2_realm.model.Contact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.grid_view)
    GridView gridView;

    private Realm realm;
    private ContactAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        realm = Realm.getDefaultInstance();

        ButterKnife.bind(this);

        fillGridView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @OnClick(R.id.back_button)
    public void onBackClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void fillGridView() {
        adapter = new ContactAdapter(this);
        adapter.setRealmContacts(realm.where(Contact.class).findAll());
        gridView.setAdapter(adapter);
    }
}
