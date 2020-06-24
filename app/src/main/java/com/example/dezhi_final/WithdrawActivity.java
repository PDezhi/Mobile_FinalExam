package com.example.dezhi_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dezhi_final.model.Customer;

import java.io.Serializable;

public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextAmount;
    TextView textViewBalance;
    Button btnWithDraw, btnGoback;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        initialize();
    }

    public void initialize() {
        editTextAmount = findViewById(R.id.editTextAmount);
        textViewBalance = findViewById(R.id.textViewBalance);
        btnWithDraw = findViewById(R.id.btnWithDraw);
        btnWithDraw.setOnClickListener(this);
        btnGoback = findViewById(R.id.btnGoback);
        btnGoback.setOnClickListener(this);
//        Bundle bundle = getIntent().getBundleExtra("intentExtra");
//        Serializable bundledListOfCustomer = bundle.getSerializable("bundleExtra");
//        customer = (Customer) bundledListOfCustomer;
        customer = (Customer) getIntent().getSerializableExtra("CurrentFlowerObject");

        setValuesToElement(customer);
    }

    private void setValuesToElement(Customer customer) {
        textViewBalance.setText(String.valueOf(customer.getBalance()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWithDraw:
                withDraw();
                break;
            case R.id.btnGoback:
                goBack();
                break;
        }
    }

    private void withDraw() {
        float amount = Float.valueOf(editTextAmount.getText().toString());

        float oldBalance = customer.getBalance();


        float newBalance = oldBalance - amount;
        if (newBalance > 0) {
            customer.setBalance(newBalance);
        }
        int index = Customer.customers.indexOf(customer);
        Customer.customers.set(index, customer);

        textViewBalance.setText(String.valueOf(newBalance));
    }
    private void goBack(){
        String strResult = "show_all";
        //------------------------------------ Create an intent and putExtra result string
        Intent intent = new Intent();
        intent.putExtra("action", strResult);

        //------------------------------------ Set Result for MainActivity
        setResult(RESULT_OK, intent);
        finish();
    }
}