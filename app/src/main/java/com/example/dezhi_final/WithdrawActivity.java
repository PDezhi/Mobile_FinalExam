package com.example.dezhi_final;

import androidx.appcompat.app.AppCompatActivity;

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
    Button btnWithDraw;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        initialize();
    }
    public void initialize(){
        editTextAmount=findViewById(R.id.editTextAmount);
        textViewBalance =findViewById(R.id.textViewBalance);
        btnWithDraw = findViewById(R.id.btnWithDraw);
        btnWithDraw.setOnClickListener(this);

//        Bundle bundle = getIntent().getBundleExtra("intentExtra");
//        Serializable bundledListOfCustomer = bundle.getSerializable("bundleExtra");
//        customer = (Customer) bundledListOfCustomer;
        customer = (Customer) getIntent().getSerializableExtra("CurrentFlowerObject");

        setValuesToElement(customer);
    }

    private void setValuesToElement(Customer customer){
        textViewBalance.setText(String.valueOf(customer.getBalance()));
    }

    @Override
    public void onClick(View view) {
        float amount = Float.valueOf(editTextAmount.getText().toString());

        float oldBalance = customer.getBalance();


        float newBalance = oldBalance-amount;
        if(newBalance>0){
            customer.setBalance(newBalance);
        }
        int index = Customer.customers.indexOf(customer);
        Customer.customers.set(index,customer);

        textViewBalance.setText(String.valueOf(newBalance));
    }
}