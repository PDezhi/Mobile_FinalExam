package com.example.dezhi_final;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
final int REQUEST_CODE
    ListView listView;
    Button btnAdd, btnWithdraw;


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
        btnWithdraw = findViewById(R.id.btnWithDraw);
        btnWithdraw.setOnClickListener(this);
        if (Customer.customers.size() == 0) {
            Customer customer1 = new Customer("1001", "2020-06-18", 1000, "Dezhi", "Ding", "4377666666", "1001");
            Customer customer2 = new Customer("1002", "2020-06-18", 1000, "Mark", "Tem", "4377666666", "1002");
            Customer customer3 = new Customer("1003", "2020-06-18", 1000, "York", "Smith", "4377666666", "1003");
            Customer.customers.add(customer1);
            Customer.customers.add(customer2);
            Customer.customers.add(customer3);
        }
        ArrayAdapter<Customer> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                Customer.customers);
        // assign the adaptor to the list view
        listView.setAdapter(listAdapter);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listViewOfFlowers, View itemView, int position, long id) {


                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("CurrentFlowerObject", Customer.customers.get(position));
                startActivityForResult(intent,1);
            }
        };
        listView.setOnItemClickListener(onItemClickListener);
        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(MainActivity.this, WithdrawActivity.class);
                intent.putExtra("CurrentFlowerObject", Customer.customers.get(i));
                startActivity(intent);
                return true;
            }
        };
        listView.setOnItemLongClickListener(onItemLongClickListener);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.btnWithDraw:

                Intent intent1 = new Intent(MainActivity.this, WithdrawActivity.class);
                intent1.putExtra("CurrentFlowerObject", Customer.customers.get(1));
                startActivity(intent1);
                break;

        }

    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, int l) {

        return true;
    }
}