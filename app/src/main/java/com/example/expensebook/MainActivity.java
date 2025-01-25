package com.example.expensebook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView mainBalance,totalExpense,addExpense,showExpense,totalIncome,addIncome,showIncome;


    ExpenseSqlite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sqlite = new ExpenseSqlite(MainActivity.this);

        mainBalance = findViewById(R.id.mainBalance);
        //===expense
        totalExpense = findViewById(R.id.totalExpense);
        addExpense = findViewById(R.id.addExpense);
        showExpense = findViewById(R.id.expenseShow);
        //========income
        totalIncome = findViewById(R.id.totalIncome);
        addIncome = findViewById(R.id.addIncome);
        showIncome = findViewById(R.id.incomeShow);



        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AddActivity.EXPENSE=true;
                startActivity(new Intent(MainActivity.this,AddActivity.class));




            }
        });


        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddActivity.EXPENSE=false;
                startActivity(new Intent(MainActivity.this,AddActivity.class));



            }
        });
        showExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            RecyclerViewActivity.REC_VIEW=true;
            startActivity(new Intent(MainActivity.this,RecyclerViewActivity.class));

                showData();



            }
        });



        showIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RecyclerViewActivity.REC_VIEW=false;
                startActivity(new Intent(MainActivity.this,RecyclerViewActivity.class));

                showData();



            }
        });













    }

    public void showData(){


        totalExpense.setText("INR: "+sqlite.showExpense());
        totalIncome.setText("INR: "+sqlite.showIncome());

        double balance = sqlite.showIncome() - sqlite.showExpense();

        mainBalance.setText("INR: "+balance);




    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();



    }
}