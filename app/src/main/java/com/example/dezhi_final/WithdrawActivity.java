package com.example.dezhi_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
    float withdrawAmount = 0;

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
                if (editTextAmount.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter the amount!", Toast.LENGTH_LONG).show();
                } else {
                    withdrawAmount = Float.parseFloat(editTextAmount.getText().toString());
                    if (withdrawAmount > 0 && (customer.getBalance() - withdrawAmount) >= 0)
                        showAlertDialogCRUD(view);
                    else
                        Toast.makeText(this, "The amount is over your balance!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnGoback:
                goBack();
                break;
        }
    }

    private void withDraw() {

        float oldBalance = customer.getBalance();


        float newBalance = oldBalance - withdrawAmount;
        if (newBalance > 0) {
            customer.setBalance(newBalance);
            int index = Customer.customers.indexOf(customer);
            Customer.customers.set(index, customer);

            textViewBalance.setText(String.valueOf(newBalance));
        } else {
            Toast.makeText(this, "the amount is over your balance!", Toast.LENGTH_LONG);
        }
    }

    private void goBack() {
        String strResult = "show_all";
        //------------------------------------ Create an intent and putExtra result string
        Intent intent = new Intent();
        intent.putExtra("action", strResult);

        //------------------------------------ Set Result for MainActivity
        setResult(RESULT_OK, intent);
        finish();
    }

    private void showAlertDialogCRUD(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // type 2
        builder.setTitle("AlertDialog")
                .setMessage("Are you sure to withdraw " + String.valueOf(withdrawAmount) + "$ ?")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)

                // We just define event listener for yes button,
                // but it can be defined for 'No' and 'Cancel' as well
                .setPositiveButton("Yes",
                        (dialogInterface, i) ->
                        {
                            withDraw();
                        })
                .setNegativeButton("No", null)
                .setNeutralButton("Cancel", null);

        builder.show();
    }

}