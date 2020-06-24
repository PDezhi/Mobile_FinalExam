package com.example.dezhi_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dezhi_final.model.Customer;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final static int REQUEST_CODE_DETAIL = 1;
    final static int REQUEST_CODE_WITHDRAW = 2;

    ListView listView;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        listView = findViewById(R.id.listViewOfCustomers);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        initializeCustomersList();
        Collections.sort(Customer.customers);
        ArrayAdapter<Customer> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
               Customer.customers);
        // assign the adaptor to the list view
        listView.setAdapter(listAdapter);

        // OnItemClickListener
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listViewOfFlowers, View itemView, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bundleExtra", Customer.customers.get(position));
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("intentExtra", bundle);
                startActivityForResult(intent, REQUEST_CODE_DETAIL);
            }
        };
        listView.setOnItemClickListener(onItemClickListener);

        // OnItemLongClickListener
        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, WithdrawActivity.class);
                intent.putExtra("CurrentFlowerObject", Customer.customers.get(i));
                startActivityForResult(intent, REQUEST_CODE_WITHDRAW);
                return true;
            }
        };
        listView.setOnItemLongClickListener(onItemLongClickListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd://only one button has onClickListener, so can delete switch
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(intent, REQUEST_CODE_DETAIL);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Collections.sort(Customer.customers);
        ArrayAdapter<Customer> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                Customer.customers);
        // assign the adaptor to the list view
        listView.setAdapter(listAdapter);
    }

    private void initializeCustomersList() {
        if (Customer.customers.size() == 0) {
            Customer customer1 = new Customer("1001", "2020-06-18", 1000, "Dezhi", "Ding", "4377666666", "1001");
            Customer customer2 = new Customer("1002", "2020-06-18", 1000, "Mark", "Tem", "4377666666", "1002");
            Customer customer3 = new Customer("1003", "2020-06-18", 1000, "York", "Smith", "4377666666", "1003");
            Customer.customers.add(customer1);
            Customer.customers.add(customer2);
            Customer.customers.add(customer3);
        }
    }
}