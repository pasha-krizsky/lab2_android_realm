package com.example.pasha.lab2_realm.model;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

/** Represents Contact entity in Realm database */
@Getter
@Setter
public class Contact extends RealmObject {

    /** The name of contact */
    private String name;
    /** Phone number */
    private String phone;
}
