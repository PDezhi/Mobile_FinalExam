package com.example.dezhi_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dezhi_final.model.Account;
import com.example.dezhi_final.model.Customer;

import java.io.Serializable;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextAccNo, editTextOpenDate, editTextBalance, editTextName, editTextFamily, editTextPhone, editTextSIN;
    Button btnAdd, btnFind, btnRemove, btnClear, btnUpdate, btnShowAll;
    final static String ALERTDIALOG_MESSAGE_OF_ADD = "Do you want to add this customer?";
    final static String ALERTDIALOG_MESSAGE_OF_DELETE = "Do you want to delete this customer?";
    final static String ALERTDIALOG_MESSAGE_OF_UPDATE = "Do you want to update this customer?";

    Customer currentCustomer;

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
        currentCustomer = new Customer();
//        customer = (Customer) getIntent().getSerializableExtra("CurrentFlowerObject");
//        setValuesToElements();
        if (getIntent().hasExtra("intentExtra")) {
            Bundle bundle = getIntent().getBundleExtra("intentExtra");
            Serializable bundledCustomer = bundle.getSerializable("bundleExtra");
            currentCustomer = (Customer) bundledCustomer;
            if (currentCustomer != null)
                setValuesToElements();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnUpdate:
//                if (editTextSIN.getText().toString().equals("") || editTextName.getText().toString().equals("") || editTextFamily.getText().toString().equals(""))
//                    Toast.makeText(this, "You must enter SIM, Name, Family!", Toast.LENGTH_LONG).show();
//                else
//                getExistCustomerInfoFromPage();
                update();
//                showAlertDialogCRUD(view, ALERTDIALOG_MESSAGE_OF_UPDATE);
                break;
            case R.id.btnRemove:
//                if (editTextSIN.getText().toString().equals(""))
//                    Toast.makeText(this, "You must enter SIN!", Toast.LENGTH_LONG).show();
//                else
//                getExistCustomerInfoFromPage();
                remove();
//                showAlertDialogCRUD(view, ALERTDIALOG_MESSAGE_OF_DELETE);
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
        getNewCustomerInfoFromPage();

        if (currentCustomer != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("AlertDialog")
                    .setMessage(ALERTDIALOG_MESSAGE_OF_ADD)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_info)

                    // We just define event listener for yes button,
                    // but it can be defined for 'No' and 'Cancel' as well
                    .setPositiveButton("Yes",
                            (dialogInterface, i) ->
                            {
                                Customer.customers.add(currentCustomer);
                                Toast.makeText(this, "This customer was added!", Toast.LENGTH_LONG).show();
                                clear();
                            })
                    .setNegativeButton("No", null)
                    .setNeutralButton("Cancel", null);

            builder.show();
        }
    }

    private void remove() {
        getExistCustomerInfoFromPage();
        if (currentCustomer != null) {
            int index = Customer.customers.indexOf(currentCustomer);

            if (index != -1) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("AlertDialog")
                        .setMessage(ALERTDIALOG_MESSAGE_OF_DELETE)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_info)

                        // We just define event listener for yes button,
                        // but it can be defined for 'No' and 'Cancel' as well
                        .setPositiveButton("Yes",
                                (dialogInterface, i) ->
                                {
                                    Customer.customers.remove(index);
                                    Toast.makeText(DetailActivity.this, "The customer was Deleted!", Toast.LENGTH_SHORT).show();
                                    clear();
                                })
                        .setNegativeButton("No", null)
                        .setNeutralButton("Cancel", null);

                builder.show();

            } else {
                Toast.makeText(this, "The customer is not exist!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void update() {
        getExistCustomerInfoFromPage();
        if (currentCustomer != null) {
            int index = Customer.customers.indexOf(currentCustomer);
            if (index != -1) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("AlertDialog")
                        .setMessage(ALERTDIALOG_MESSAGE_OF_UPDATE)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_info)

                        // We just define event listener for yes button,
                        // but it can be defined for 'No' and 'Cancel' as well
                        .setPositiveButton("Yes",
                                (dialogInterface, i) ->
                                {
                                    Customer.customers.set(index, currentCustomer);
                                    Toast.makeText(this, "This customer was updated!", Toast.LENGTH_LONG).show();
                                })
                        .setNegativeButton("No", null)
                        .setNeutralButton("Cancel", null);

                builder.show();

            } else {
                Toast.makeText(this, "The customer is not exist!", Toast.LENGTH_LONG).show();
            }

        }
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

    private void find() {
        String SIN = editTextSIN.getText().toString();
        currentCustomer = null;
        if (!SIN.equals("")) {
            for (Customer one : Customer.customers) {
                if (one.getSIN().equals(SIN)) {
                    currentCustomer = one;
                }
            }
            if (currentCustomer != null)
                setValuesToElements();
            else
                Toast.makeText(this, "Do not exist the customer with the SIN !", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Please enter the SIN !", Toast.LENGTH_LONG).show();
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
        editTextAccNo.setText(currentCustomer.getAccount().getAccountNo());
        editTextOpenDate.setText(currentCustomer.getAccount().getOpenDate());
        editTextBalance.setText(String.valueOf(currentCustomer.getAccount().getBalance()));
        editTextName.setText(currentCustomer.getFirstName());
        editTextFamily.setText(currentCustomer.getFamily());
        editTextPhone.setText(currentCustomer.getPhone());
        editTextSIN.setText(currentCustomer.getSIN());
    }

    private void getNewCustomerInfoFromPage() {
        String accountNo = editTextAccNo.getText().toString();
        String openDate = editTextOpenDate.getText().toString();
        String strBalance = editTextBalance.getText().toString();
        float balance = 0;
        if (!strBalance.equals("")) {
            balance = Float.parseFloat(editTextBalance.getText().toString());
        }
//        float balance = Float.parseFloat(editTextBalance.getText().toString());
        String sin = editTextSIN.getText().toString();
        String firstName = editTextName.getText().toString();
        String family = editTextFamily.getText().toString();
        String phone = editTextPhone.getText().toString();

        if (accountNo.equals("") || openDate.equals("") || strBalance.equals("") || sin.equals("") || firstName.equals("") || family.equals("") || phone.equals("")) {
            currentCustomer = null;
            Toast.makeText(this, "You must enter data for all the fields ", Toast.LENGTH_LONG).show();
        } else if (containsAccountNo(Customer.customers, accountNo)) {
            currentCustomer = null;
            Toast.makeText(this, "The Account Number You entered has already exists!", Toast.LENGTH_LONG).show();
        } else if (containsSIN(Customer.customers, sin)) {
            currentCustomer = null;
            Toast.makeText(this, "The SIN You entered already exists!", Toast.LENGTH_LONG).show();
        } else {
            currentCustomer = new Customer(new Account(accountNo, openDate, balance), firstName, family, phone, sin);
        }
    }

    private void getExistCustomerInfoFromPage() {
        String accountNo = editTextAccNo.getText().toString();
        String openDate = editTextOpenDate.getText().toString();
        float balance = Float.parseFloat(editTextBalance.getText().toString());
        String sin = editTextSIN.getText().toString();
        String firstName = editTextName.getText().toString();
        String family = editTextFamily.getText().toString();
        String phone = editTextPhone.getText().toString();

        if (accountNo.equals("") || openDate.equals("") || sin.equals("") || firstName.equals("") || family.equals("") || phone.equals("")) {
            currentCustomer = null;
            Toast.makeText(this, "You must enter data for all the fields ", Toast.LENGTH_LONG).show();
        } else if (!containsSIN(Customer.customers, sin)) {
            currentCustomer = null;
            Toast.makeText(this, "The SIN You entered do not exists!", Toast.LENGTH_LONG).show();
        } else if (!containsAccountNo(Customer.customers, accountNo)) {
            currentCustomer = null;
            Toast.makeText(this, "The Account Number You entered do not exists!", Toast.LENGTH_LONG).show();
        } else {
            currentCustomer = new Customer(new Account(accountNo, openDate, balance), firstName, family, phone, sin);
        }
    }

    public boolean containsAccountNo(final List<Customer> list, final String accountNo) {
        list.stream().findFirst().filter(o -> o.getSIN().equals(accountNo));
        return list.stream().anyMatch(o -> o.getAccount().getAccountNo().equals(accountNo));
    }

    public boolean containsSIN(final List<Customer> list, final String sin) {
        return list.stream().anyMatch(o -> o.getSIN().equals(sin));
    }

//    public Customer getCustomerBySIN(final List<Customer> list, final String sin) {
//        return list.stream().findFirst().filter(o -> o.getSIN().equals(sin)).get();
//    }

}