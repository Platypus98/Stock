package com.example.stock;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

public class AddGoodActivity extends AppCompatActivity {

    TextView goodNameView;
    TextView goodQuantityView;
    TextView purchasePriceView;
    TextView sellingPriceView;

    Button btnAdd;
    MyDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_good);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        goodNameView = (TextView) findViewById(R.id.goodName);
        goodQuantityView = findViewById(R.id.goodQuantity);
        purchasePriceView = findViewById(R.id.purchasePrice);
        sellingPriceView = findViewById(R.id.sellingPrice);

        db = new MyDatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addGood(goodNameView.getText().toString(), goodQuantityView.getText().toString(), sellingPriceView.getText().toString(), purchasePriceView.getText().toString());
                Toast.makeText(getApplicationContext(), "Товар успешно добавлен!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
