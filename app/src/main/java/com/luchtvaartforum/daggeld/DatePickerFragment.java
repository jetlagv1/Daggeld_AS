package com.luchtvaartforum.daggeld;

import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.*;
import java.util.Calendar;
import android.view.View;
import android.content.*;
import java.text.SimpleDateFormat;

public class DatePickerFragment extends DialogFragment 
implements DatePickerDialog.OnDateSetListener {
	private Calendar selected_datetime;
	private TextView  selected_textview;
	private SimpleDateFormat date_format;
//	StopOver stop;

	public DatePickerFragment(TextView sltv, Calendar sldt, SimpleDateFormat sldf) {
		this.selected_datetime = sldt;
		this.selected_textview = sltv;
		this.date_format = sldf;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		int year = selected_datetime.get(Calendar.YEAR);
		int month = selected_datetime.get(Calendar.MONTH);
		int day = selected_datetime.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker p1, int year, int month, int day)
	{
//		Toast.makeText(context, "on blocks date clicked & set", Toast.LENGTH_SHORT).show();
		selected_datetime.set(year, month, day);
		selected_textview.setText(date_format.format(selected_datetime.getTime()));
	}

	//public void  view, int y, int m, int d) {
		//selected_datetime.set(y, m, d);
		//updateValues();
		//testClickButton();
	//}
}
