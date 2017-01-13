package com.honeybin.sketchu;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnStartTimePicker, btnEndTimePicker, addButton;
    private int startHour, startMinute;
    private int endHour, endMinute;
    private int mHour, mMinute;
    private EventsDBHelper dbHelper;
    private EditText txtStart, txtEnd, txtName, txtDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        dbHelper = new EventsDBHelper(this);

        btnStartTimePicker = (Button) findViewById(R.id.setStartTimeButton);
        btnEndTimePicker = (Button) findViewById(R.id.setEndTimeButton);
        addButton = (Button) findViewById(R.id.addDecideButton);


        txtName = (EditText)findViewById(R.id.nameTxt);
        txtDetail = (EditText) findViewById(R.id.detailTxt);
        txtStart=(EditText)findViewById(R.id.startTimeText);
        txtEnd=(EditText)findViewById(R.id.endTimeText);

        btnStartTimePicker.setOnClickListener(this);
        btnEndTimePicker.setOnClickListener(this);
        addButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnStartTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            startHour = c.get(Calendar.HOUR_OF_DAY);
            startMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtStart.setText(hourOfDay + ":" + minute);
                        }
                    }, startHour, startMinute, false);
            timePickerDialog.show();
        }

        if(v == btnEndTimePicker) {
            final Calendar c = Calendar.getInstance();
            endHour = c.get(Calendar.HOUR_OF_DAY);
            endMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtEnd.setText(hourOfDay + ":" + minute);
                        }
                    }, endHour, endMinute, false);
            timePickerDialog.show();
        }
        if(v == addButton) {
            String name = txtName.getText().toString();
            String detail = txtDetail.getText().toString();


            dbHelper.insertEvent(name, 0, detail);
            //EventListActivity.eventList.add(new Event(name, startHour, startMinute, endHour, endMinute, detail));
            Intent i = new Intent(getApplicationContext(), EventListActivity.class);
            startActivity(i);
        }
    }
}
