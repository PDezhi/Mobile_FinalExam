package com.example.dezhi_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dezhi_final.model.Customer;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextAccNo, editTextOpenDate, editTextBalance, editTextName, editTextFamily, editTextPhone, editTextSIN;
    Button btnAdd, btnFind, btnRemove, btnClear, btnUpdate, btnShowAll;

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialize();
    }

    private void initialize() {
        editTextAccNo = findViewById(R.id.editTextAccNo);
        editTextOpenDate = findViewById(R.id.editTextOpenDate);
        editTextBalance = findViewById(R.id.editBalance);
        editTextName = findViewById(R.id.editTextName);
        editTextFamily = findViewById(R.id.editTextFamily);
        editTextPhone = findViewById(R.id.editPhone);
        editTextSIN = findViewById(R.id.editTextSIN);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(this);
        btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(this);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnShowAll = findViewById(R.id.btnWithShowAll);
        btnShowAll.setOnClickListener(this);
        customer = new Customer();
//        customer = (Customer) getIntent().getSerializableExtra("CurrentFlowerObject");
//        setValuesToElements();
        if (getIntent().hasExtra("intentExtra")) {
            Bundle bundle = getIntent().getBundleExtra("intentExtra");
            Serializable bundledCustomer = bundle.getSerializable("bundleExtra");
            customer = (Customer) bundledCustomer;
            if (!customer.equals(null))
                setValuesToElements();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnFind:
                find();
                break;
            case R.id.btnRemove:
                remove();
                break;
            case R.id.btnClear:
                clear();
                break;
            case R.id.btnUpdate:
                update();
                break;
            case R.id.btnWithShowAll:
                showAll();
                break;
        }
    }

    private void add() {
        String a = editTextAccNo.getText().toString();
        customer.setAccount(editTextAccNo.getText().toString());
        customer.setBalance(Float.valueOf(editTextBalance.getText().toString()));
        customer.setFirstName(editTextName.getText().toString());
        customer.setFamily(editTextFamily.getText().toString());
        customer.setPhone(editTextPhone.getText().toString());
        customer.setSIN(editTextSIN.getText().toString());
        Customer.customers.add(customer);
        Toast.makeText(this,"Customer was added!",Toast.LENGTH_LONG).show();
        clear();
    }

    private void clear() {
        editTextAccNo.setText("");
        editTextOpenDate.setText("");
        editTextBalance.setText("");
        editTextName.setText("");
        editTextFamily.setText("");
        editTextPhone.setText("");
        editTextSIN.setText("");
    }

    private void remove() {
        setElementToObject();
        int index = Customer.customers.indexOf(customer);
        if (index != -1) {
            Customer.customers.remove(index);
            Toast.makeText(this,"Customer was deleted!",Toast.LENGTH_LONG).show();
            clear();
        } else {
            Toast.makeText(this, "The customer is not exist!", Toast.LENGTH_LONG).show();
        }
    }

    private void find() {
        String SIN = editTextSIN.getText().toString();
        for (Customer one : Customer.customers) {
            if (one.getSIN().equals(SIN)) {
                customer = one;
            }
        }
        setValuesToElements();
    }

    private void update() {
        setElementToObject();
        int index = Customer.customers.indexOf(customer);
        Customer.customers.set(index, customer);
        Toast.makeText(this,"Customer was updated!",Toast.LENGTH_LONG).show();
        clear();
    }

    private void showAll() {
        String strResult = "show_all";
        //------------------------------------ Create an intent and putExtra result string
        Intent intent = new Intent();
        intent.putExtra("action", strResult);

        //------------------------------------ Set Result for MainActivity
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setValuesToElements() {
        editTextAccNo.setText(customer.getAccount());
        editTextOpenDate.setText(customer.getOpenDate());
        editTextBalance.setText(String.valueOf(customer.getBalance()));
        editTextName.setText(customer.getFirstName());
        editTextFamily.setText(customer.getFamily());
        editTextPhone.setText(customer.getPhone());
        editTextSIN.setText(customer.getSIN());
    }

    private void setElementToObject() {
        customer.setAccount(editTextAccNo.getText().toString());
        customer.setBalance(Float.valueOf(editTextBalance.getText().toString()));
        customer.setFirstName(editTextName.getText().toString());
        customer.setFamily(editTextFamily.getText().toString());
        customer.setPhone(editTextPhone.getText().toString());
        customer.setSIN(editTextSIN.getText().toString());
    }
}