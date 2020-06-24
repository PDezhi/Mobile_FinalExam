package com.example.dezhi_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
    final static String ALERTDIALOG_MESSAGE_OF_ADD = "Do you want to add this customer?";
    final static String ALERTDIALOG_MESSAGE_OF_DELETE = "Do you want to delete this customer?";
    final static String ALERTDIALOG_MESSAGE_OF_UPDATE = "Do you want to update this customer?";

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
        btnShowAll = findViewById(R.id.btnShowAll);
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
                if (editTextSIN.getText().toString().equals("") || editTextName.getText().toString().equals("") || editTextFamily.getText().toString().equals(""))
                    Toast.makeText(this, "You must enter SIM, Name, Family!", Toast.LENGTH_LONG).show();
                else
                    showAlertDialogCRUD(view, ALERTDIALOG_MESSAGE_OF_ADD);
                break;
            case R.id.btnUpdate:
                if (editTextSIN.getText().toString().equals("") || editTextName.getText().toString().equals("") || editTextFamily.getText().toString().equals(""))
                    Toast.makeText(this, "You must enter SIM, Name, Family!", Toast.LENGTH_LONG).show();
                else
                    showAlertDialogCRUD(view, ALERTDIALOG_MESSAGE_OF_UPDATE);
                break;
            case R.id.btnRemove:
                if (editTextSIN.getText().toString().equals(""))
                    Toast.makeText(this, "You must enter SIN!", Toast.LENGTH_LONG).show();
                else
                    showAlertDialogCRUD(view, ALERTDIALOG_MESSAGE_OF_DELETE);
                break;
            case R.id.btnClear:
                clear();
                break;
            case R.id.btnFind:
                find();
                break;
            case R.id.btnShowAll:
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
        if (!customer.getSIN().equals("")) {
            Customer.customers.add(customer);
            Toast.makeText(this, "This customer was added!", Toast.LENGTH_LONG).show();
            clear();
        } else
            Toast.makeText(this, "You must enter the SIN!", Toast.LENGTH_LONG).show();

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
            Toast.makeText(DetailActivity.this, "The customer was Deleted!", Toast.LENGTH_SHORT).show();
            clear();
        } else {
            Toast.makeText(this, "The customer is not exist!", Toast.LENGTH_LONG).show();
        }
    }

    private void find() {
        String SIN = editTextSIN.getText().toString();
        if (!SIN.equals("")) {
            for (Customer one : Customer.customers) {
                if (one.getSIN().equals(SIN)) {
                    customer = one;
                }
            }
            setValuesToElements();
        } else
            Toast.makeText(this, "Please enter the SIN !", Toast.LENGTH_LONG).show();
    }

    private void update() {
        setElementToObject();
        int index = Customer.customers.indexOf(customer);
        Customer.customers.set(index, customer);
        Toast.makeText(this, "This customer was updated!", Toast.LENGTH_LONG).show();
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

    public void showAlertDialogCRUD(View view, String dialogMessage) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // type 2
        builder.setTitle("AlertDialog")
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_info)

                // We just define event listener for yes button,
                // but it can be defined for 'No' and 'Cancel' as well
                .setPositiveButton("Yes",
                        (dialogInterface, i) ->
                        {
                            switch (view.getId()) {
                                case R.id.btnAdd:
                                    add();
                                    break;
                                case R.id.btnUpdate:
                                    update();
                                    break;
                                case R.id.btnRemove:
                                    remove();
                                    break;
                            }
                        })
                .setNegativeButton("No", null)
                .setNeutralButton("Cancel", null);

        builder.show();
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