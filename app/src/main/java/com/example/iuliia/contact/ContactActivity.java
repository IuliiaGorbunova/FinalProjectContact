package com.example.iuliia.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    EditText contactNameBox;
    EditText phoneNumberBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.phone_book_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_contact);

        contactNameBox = (EditText) findViewById(R.id.contactName);
        phoneNumberBox = (EditText) findViewById(R.id.contactPhoneNumber);
    }

    public void newContact(View view) {
        long phoneNumber = Long.parseLong(phoneNumberBox.getText().toString());
        Contact contact = new Contact(contactNameBox.getText().toString(), phoneNumber);

        getDbHandler().addContact(contact);

        String message = "Contact " + contactNameBox.getText().toString() + " is added!";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        contactNameBox.setText("");
        phoneNumberBox.setText("");
    }

    public void lookupContact(View view) {
        Contact contact = getDbHandler().findContact(contactNameBox.getText().toString());

        if (contact != null) {
            phoneNumberBox.setText(String.valueOf(contact.getPhoneNumber()));
        } else {
            Toast.makeText(this, "Contact is not found!", Toast.LENGTH_LONG).show();
        }
    }

    public void removeContact(View view) {
        boolean result = getDbHandler().deleteContact(contactNameBox.getText().toString());

        if (result)
        {
            String message = "Contact " + contactNameBox.getText().toString() + " is deleted!";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            contactNameBox.setText("");
            phoneNumberBox.setText("");
        }
        else {
            String message = "No contact " + contactNameBox.getText().toString() + " is found!";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    private MyDBHandler getDbHandler() {
        return new MyDBHandler(this, null, null, 1);
    }

}
