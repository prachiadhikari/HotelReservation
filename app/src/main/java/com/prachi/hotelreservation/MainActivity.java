package com.prachi.hotelreservation;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner spinroom;
    TextView tvindate, tvoutdate;
    TextView tvroom, tvadult, tvkids;
    Button btncalc;
    int year2, year3;
    int month2, month3;
    int day2, day3;
    Button simpledatepicker1, simpledatepicker2;
    int no_of_room;
    TextView tvdayofstay, totalsuite, tvtotal, tvVat, tvroomno,tverr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinroom = findViewById(R.id.spinroom);
        totalsuite = findViewById(R.id.totalsuite);
        tvtotal = findViewById(R.id.tvtotal);
        tvVat = findViewById(R.id.tvVat);
        tvroomno = findViewById(R.id.tvroomno);
        tverr = findViewById(R.id.tverr);
        tvindate = findViewById(R.id.tvindate);
        tvdayofstay = findViewById(R.id.tvdayofstay);
        tvoutdate = findViewById(R.id.tvoutdate);
        simpledatepicker1 = findViewById(R.id.simpledatepicker1);
        simpledatepicker2 = findViewById(R.id.simpledatepicker2);
        tvadult = findViewById(R.id.tvAdult);
        tvkids = findViewById(R.id.tvChild);
        tvroom = findViewById(R.id.tvRoom);
        btncalc = findViewById(R.id.btncalc);
        String room[] = {"Deluxe", "Presendential", "Premium"};


        ArrayAdapter adapter= new ArrayAdapter<>
                (
                        this,
                        android.R.layout.simple_list_item_1, room
                );
        spinroom.setAdapter(adapter);

        simpledatepicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar1();
            }
        });

        simpledatepicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar2();

            }
        });
    }

    private void loadCalendar1() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = month + "/" + dayOfMonth + "/" + year;
                month2=month;
                day2=dayOfMonth;
                year2=year;
                tvindate.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    private void loadCalendar2() {
        final Calendar c1 = Calendar.getInstance();
        int year= c1.get(Calendar.YEAR);
        final int month = c1.get(Calendar.MONTH);
        int day= c1.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month, int dayOfMonth1) {
                String date = month + "/" + dayOfMonth1 + "/" + year1;
                month3=month;
                day3=dayOfMonth1;
                year3=year1;
                tvoutdate.setText(date);

            }
        }, year, month, day);

        datePickerDialog.show();

        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(tvindate.getText())) {
                    tverr.setError("Please enter Checked in Date ");
                    return;
                }

                else if (TextUtils.isEmpty(tvkids.getText())) {
                    tvkids.setError("Please enter number of Kids ");
                    return;
                }
                else if (TextUtils.isEmpty(tvadult.getText())) {
                    tvadult.setError("Please enter number of adults ");
                    return;
                }

                else if (TextUtils.isEmpty(tvoutdate.getText())) {
                    tvoutdate.setError("Please enter Checked out Date ");
                    return;
                }

                else if (TextUtils.isEmpty(tvroom.getText())) {
                    tvroom.setError("Please enter Number of Rooms ");
                    return;
                }

                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(year2, month2, day2);
                cal2.set(year3, month3, day3);
                long millis1 = cal1.getTimeInMillis();
                long millis2 = cal2.getTimeInMillis();
                long diff = millis2 - millis1;
                long diffDays = (diff / (86400000));
                no_of_room = Integer.parseInt(tvroom.getText().toString());
                double price;
                double Total_Price;
                double Grand_Total;


                String suite = spinroom.getSelectedItem().toString();

                if (suite == "Deluxe") {

                    price = 2000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;

                    tvdayofstay.setText("Total Days:"+diffDays);
                    tvroomno.setText(("Number of Room(s):"+no_of_room));
                    totalsuite.setText("Room Price Per Night:"+"2000");
                    tvtotal.setText("Total:"+Total_Price);
                    tvVat.setText("Grand Total:"+Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();


                } else if (suite == "Premium") {

                    price = 4000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvdayofstay.setText("Total Days:"+diffDays);
                    tvroomno.setText(("Number of Room(s):"+no_of_room));
                    totalsuite.setText("Room Price Per Night:"+"4000");
                    tvtotal.setText("Total:"+Total_Price);
                    tvVat.setText("Grand Total:"+Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();

                } else if (suite == "Presendential") {
                    price = 5000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvdayofstay.setText("Total Days:"+diffDays);
                    tvroomno.setText(("Number of Room(s):"+no_of_room));
                    totalsuite.setText("Room Price Per Night:"+"5000");
                    tvtotal.setText("Total:"+Total_Price);
                    tvVat.setText("Grand Total:"+Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

}