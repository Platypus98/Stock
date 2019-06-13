package com.example.stock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateGoodActivity extends AppCompatActivity {
    TextView goodNameView;
    TextView goodQuantityView;
    TextView purchasePriceView;
    TextView sellingPriceView;

    String oldGoodName;
    Good good;

    Button btnUpdate;
    MyDatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_good);

        Bundle b = getIntent().getExtras();
        oldGoodName = b.getString("oldGoodName");

        db = new MyDatabaseHelper(this);

        good = db.getGoodByName(oldGoodName);

        btnUpdate =  findViewById(R.id.btnUpdate);

        goodNameView = findViewById(R.id.goodName);
        goodNameView.setText(good.getName());

        goodQuantityView = findViewById(R.id.goodQuantity);
        goodQuantityView.setText(good.getQuantity());

        purchasePriceView = findViewById(R.id.purchasePrice);
        purchasePriceView.setText(good.getPurchasePrice());

        sellingPriceView = findViewById(R.id.sellingPrice);
        sellingPriceView.setText(good.getSellingPrice());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateGood(oldGoodName, goodNameView.getText().toString(), goodQuantityView.getText().toString(), purchasePriceView.getText().toString(), sellingPriceView.getText().toString());
                Log.i("Da", sellingPriceView.getText().toString());
                Toast.makeText(getApplicationContext(), "Товар успешно обновлен!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
