package com.example.pasha.lab2_realm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pasha.lab2_realm.R;
import com.example.pasha.lab2_realm.model.Contact;

import io.realm.RealmResults;

public class ContactAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private RealmResults<Contact> realmContacts;

    public ContactAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        if (realmContacts == null) {
            return 0;
        }

        return realmContacts.size();
    }

    @Override
    public Object getItem(int position) {

        if (realmContacts == null || realmContacts.get(position) == null) {
            return null;
        }

        return realmContacts.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {

        if (currentView == null) {
            currentView = inflater.inflate(R.layout.item, parent, false);
        }

        Contact contact = realmContacts.get(position);

        if (contact != null) {
            ((TextView) currentView.findViewById(R.id.name_grid_text_view))
                    .setText(contact.getName());
            ((TextView) currentView.findViewById(R.id.phone_grid_text_view))
                    .setText(contact.getPhone());
        }

        return currentView;
    }

    public void setRealmContacts(RealmResults<Contact> contacts) {
        this.realmContacts = contacts;
    }
}
