package com.example.fariz.iakkopi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText Snama;
    Button Border, mButtonPlus, mButtonMin;
    TextView Sharga, mTextQty;
    int harga = 0;
    int qty = 0;
    //spinner
    Spinner mSpinnerMenu;
    List<String> mListMenu = new ArrayList<>();
    RadioGroup mRadiogroup;
    RadioButton mRadioItem1, mRadioItem2;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Sharga = (TextView) findViewById(R.id.Sharga);
        Snama = (EditText) findViewById(R.id.Snama);
        Border = (Button) findViewById(R.id.Border);
        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mRadiogroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioItem1 = (RadioButton) findViewById(R.id.mRadioButtonItem1);
        mRadioItem2 = (RadioButton) findViewById(R.id.mRadioButtonItem2);

        mRadioItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item 1 di klik", Toast.LENGTH_SHORT).show();
            }
        });

        mRadioItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item 2di klik", Toast.LENGTH_SHORT).show();
            }
        });


        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mListMenu.add("-----------");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok Bakar");
        mListMenu.add("Ice Cream Sandwich");
        mListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);

        // Border.setOnClickListener(new View.OnClickListener() {
        //   @Override
        // public void onClick(View v) {
        //   Toast.makeText(getApplicationContext(),"Hi nama saya "+Snama.getText(),Toast.LENGTH_LONG).show();
        //Sharga.setText(Snama.getText());
        // }
        // });

    }

    //public void onClickOrder(View view) {
    //  Toast.makeText(getApplicationContext(), "Hi nama saya " + Snama.getText(), Toast.LENGTH_LONG).show();
    //Sharga.setText(Snama.getText());
    //  }
    public void onClickOrder(View view) {
        Intent emailintent = new Intent(Intent.ACTION_SEND);
        emailintent.setData(Uri.parse("mailto :"));
        emailintent.setType("text/plain");
        emailintent.putExtra(Intent.EXTRA_EMAIL, "arieridwansyah@gmail.com");
        emailintent.putExtra(Intent.EXTRA_SUBJECT, Snama.getText());
        emailintent.putExtra(Intent.EXTRA_TEXT,
                "Saya "
                        + Snama.getText()
                        + "Pesan"
                        + mSpinnerMenu.getSelectedItem()
                        + "Sebanyak"
                        + mTextQty.getText()
                        + "Seharga"
                        + Sharga.getText());
        try {
            startActivity(Intent.createChooser(emailintent, "send mail ....."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, " There is no email client installed", Toast.LENGTH_SHORT).show();
        }


    }

    public void onClickPlus(View view) {
        harga = harga + 5;
        qty = qty + 1;
        mTextQty.setText(qty + "");
        Sharga.setText("$" + harga);
    }

    public void onClickMin(View v) {
        if (harga != 0) {
            harga = harga - 5;
            qty = qty - 1;
            mTextQty.setText(qty + "");
            Sharga.setText("$" + harga);
        } else {
            harga = 0;
            qty = 0;
            mTextQty.setText(qty + "");
            Sharga.setText("$" + harga);
        }


    }

    public void onClickReset(View view) {
        harga = 0;
        qty = 0;
        Snama.setText("");
        Sharga.setText("$" + harga);
        mTextQty.setText(qty + "");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}