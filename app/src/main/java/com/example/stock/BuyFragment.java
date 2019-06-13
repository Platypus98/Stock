package com.example.stock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class BuyFragment extends Fragment {
    AutoCompleteTextView goodName;
    EditText goodQuantity;
    EditText purchasePrice;
    Button purchaseBtn;


    MyDatabaseHelper db;
    Good good;

    List<String> mGoods = new ArrayList<String>();

    private Context mContext;

    // Initialise it from onAttach()
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        db = new MyDatabaseHelper(this.getActivity());
    }

    public static BuyFragment newInstance() {
        BuyFragment fragment = new BuyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_buy, container, false);

        goodName = RootView.findViewById(R.id.autoCompleteGoodNamePurchase);
        goodQuantity = RootView.findViewById(R.id.goodQuantityPurchase);
        purchasePrice = RootView.findViewById(R.id.goodPurchasePrice);
        purchaseBtn = RootView.findViewById(R.id.purchaseBtn);

        goodName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                /* When focus is lost check that the text field
                 * has valid values.
                 */
                if (!hasFocus) {
                    try
                    {
                        good = db.getGoodByName(goodName.getText().toString());
                        purchasePrice.setText(good.getPurchasePrice());
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(mContext, "Такого товара не существует!", Toast.LENGTH_SHORT).show();
                        purchasePrice.setText("");
                    }
                }
            }
        });

        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    db.purchaseGood(good.getName(), goodQuantity.getText().toString());
                    Toast.makeText(mContext, "Товар успешно закуплен!", Toast.LENGTH_SHORT).show();
                    goodName.setText("");
                    goodQuantity.setText("");
                    purchasePrice.setText("");
                }
                catch (Exception e)
                {
                    Toast.makeText(mContext, "Такого товара не существует!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setGoods();
        goodName.setAdapter(new ArrayAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, mGoods));

        return RootView;
    }

    public void setGoods()
    {
        List<Good> goods = db.getAllGoods();
        if (goods.size() > 0)
        {
            for (Good good:goods)
            {
                mGoods.add(good.getName());
            }
        }
    }
}
