package com.luchtvaartforum.daggeld;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.*;
import android.os.Bundle;

public class StopOver {
	private String airport;
	private Calendar onBlocksDateTime = Calendar.getInstance();
	private Calendar offBlocksDateTime = Calendar.getInstance();
	private Time onBlocksTime = new Time();
	private Time offBlocksTime = new Time();
	private Double sundries_24 = 0d;
	private Double lunchAllowance = 0d;
	private Double dinerAllowance = 0d;
	
	public StopOver(String airport, Calendar on_blocks, Calendar off_blocks, 
			Double sundries_24, Double lunch_allowance, Double diner_allowance) {
		this.airport = airport;
		this.sundries_24 = sundries_24;
		this.lunchAllowance = lunch_allowance;
		this.dinerAllowance = diner_allowance;
		setOnBlocks(on_blocks);
		setOffBlocks(off_blocks);
		onBlocksTime.set(0, onBlocksDateTime.get(Calendar.MINUTE), onBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		offBlocksTime.set(0, offBlocksDateTime.get(Calendar.MINUTE), offBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		
	}
	
	public StopOver() {
		onBlocksTime.set(0, onBlocksDateTime.get(Calendar.MINUTE), onBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		offBlocksTime.set(0, offBlocksDateTime.get(Calendar.MINUTE), offBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		
	}
	
//	protected void onCreate(Bundle savedInstanceState) {
//		onBlocksDateTime = Calendar.getInstance();
//		onBlocksDateTime = Calendar.getInstance();
//		onblockstime.setToNow();//(0, onblocksdatetime.get(Calendar.MINUTE), onblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
//		offblockstime.setToNow();//(0, offblocksdatetime.get(Calendar.MINUTE), offblocksdatetime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
//	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//// SET METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setStopOver(String airport, Calendar on_blocks, Calendar off_blocks, 
							Double sundries_24, Double lunch_allowance, Double diner_allowance) {

		onBlocksDateTime.setTime(on_blocks.getTime());
		offBlocksDateTime.setTime(off_blocks.getTime());
		onBlocksTime.set(0, onBlocksDateTime.get(Calendar.MINUTE), onBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		offBlocksTime.set(0, offBlocksDateTime.get(Calendar.MINUTE), offBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
		this.airport = airport;
		this.sundries_24 = sundries_24;
		this.lunchAllowance = lunch_allowance;
		this.dinerAllowance = diner_allowance;
	}
		
	public void setAirport(String airport) {
		this.airport = airport;
	}
	
	public void setOnBlocks(Calendar on_blocks) {
		onBlocksDateTime.setTime(on_blocks.getTime());
		onBlocksTime.set(0, onBlocksDateTime.get(Calendar.MINUTE), onBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
	}
	
	public void setOnBlocksTimeHour(int onbth) {
		onBlocksDateTime.set(Calendar.HOUR_OF_DAY, onbth);
		onBlocksTime.set (0, onBlocksDateTime.get(Calendar.MINUTE), onbth, 0, 0, 0);
	}
	
	public void setOnBlocksTimeMinute(int onbtm) {
		onBlocksDateTime.set(Calendar.MINUTE, onbtm);
		onBlocksTime.set (0, onbtm, onBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
	}
	
	public void setOffBlocks(Calendar off_blocks) {
		offBlocksDateTime.setTime(off_blocks.getTime());
		offBlocksTime.set(0, offBlocksDateTime.get(Calendar.MINUTE), offBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
	}

	public void setOffBlocksTimeHour(int offbth) {
		offBlocksDateTime.set(Calendar.HOUR_OF_DAY, offbth);
		offBlocksTime.set (0, offBlocksDateTime.get(Calendar.MINUTE), offbth, 0, 0, 0);
	}

	public void setOffBlocksTimeMinute(int offbtm) {
		offBlocksDateTime.set(Calendar.MINUTE, offbtm);
		offBlocksTime.set (0, offbtm, offBlocksDateTime.get(Calendar.HOUR_OF_DAY), 0, 0, 0);
	}
	
	public void setSundries_24(Double sundries_24) {
		this.sundries_24 = sundries_24;
	}
	
	public void setLunchAllowance(Double lunch_allowance) {
		this.lunchAllowance = lunch_allowance;
	}
	
	public void setDinerAllowance(Double diner_allowance) {
		this.dinerAllowance = diner_allowance;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//// GET METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getAirport() {
		return airport;
	}
	
	public Calendar getOnBlocksDatetime() {
		return onBlocksDateTime;
	}
	
	public Integer getOnBlocksTime() {
		return onBlocksTime.hour;
	}
	
	public int getOnBlockTimeHour() {
		return onBlocksDateTime.get(Calendar.HOUR_OF_DAY);
	}

	public int getOnBlockTimeMinute() {
		return onBlocksDateTime.get(Calendar.MINUTE);
	}
	
	public Calendar getOffBlocksDatetime() {
		return offBlocksDateTime;
	}
	
	public Integer getOffBlocksTime() {
		return offBlocksTime.hour;
	}
	
	public int getOffBlockTimeHour() {
		return offBlocksDateTime.get(Calendar.HOUR_OF_DAY);
	}

	public int getOffBlockTimeMinute() {
		return offBlocksDateTime.get(Calendar.MINUTE);
	}
	
	public Double getAllowance() {
		return getSundries() + (calcNrLunch() * lunchAllowance) + (calcNrDiner() * dinerAllowance);		
	}

	protected Double getSundries_24() {
		return sundries_24;
	}

	protected Double getLunchAllowance() {
		return lunchAllowance;
	}

	protected Double getDinerAllowance() {
		return dinerAllowance;
	}

	protected Double getSundries() {
		return calcSundries();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	//// CALCULATING GET METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean isLunch() {
			
		if ((onBlocksDateTime.get(Calendar.HOUR_OF_DAY) < 13) && (offBlocksDateTime.get(Calendar.HOUR_OF_DAY) >= 13) && ((calcMinutesMinusDaysOnGround() > 180) || (calcDaysOnGround() > 0)))
			return true;
			
		else if ((onBlocksDateTime.get(Calendar.HOUR_OF_DAY) < 13) && (offBlocksDateTime.get(Calendar.HOUR_OF_DAY) < 13) && onBlocksDateTime.before(offBlocksDateTime) && offBlocksTime.before(onBlocksTime))
			return true;
			
		else if ((onBlocksDateTime.get(Calendar.HOUR_OF_DAY) >= 13) && (offBlocksDateTime.get(Calendar.HOUR_OF_DAY) >= 13) && (calcHoursMinusDaysOnGround() > 13))
			return true;
			
		return false;
	}

	private boolean isDiner() {
		if ((onBlocksDateTime.get(Calendar.HOUR_OF_DAY) < 20) && (offBlocksDateTime.get(Calendar.HOUR_OF_DAY) >= 20) && ((calcMinutesMinusDaysOnGround() > 180) || (calcDaysOnGround() > 0)))
			return true;

		else if ((onBlocksDateTime.get(Calendar.HOUR_OF_DAY) < 20) && (offBlocksDateTime.get(Calendar.HOUR_OF_DAY) < 20) && onBlocksDateTime.before(offBlocksDateTime) && offBlocksTime.before(onBlocksTime))
			return true;
			
		else if ((onBlocksDateTime.get(Calendar.HOUR_OF_DAY) >= 20) && (offBlocksDateTime.get(Calendar.HOUR_OF_DAY) >= 20) && (calcHoursMinusDaysOnGround() > 4))
			return true;

		return false;
	}

	private Double calcSundries() {
		Double s;
		switch(calcHoursMinusDaysOnGround()) {
			case 0: case 1: case 2:
				s = 0.0;
				break;
			case 3: case 4: case 5:
				s = 0.25;
				break;
			case 6: case 7: case 8: case 9: case 10: case 11:
				s = 0.50;
				break;
			case 12: case 13: case 14: case 15: case 16: case 17:
				s = 0.75;
				break;
			case 18: case 19: case 20: case 21: case 22: case 23:
				s = 1.0;
				break;
			default:
				throw new IllegalArgumentException();
		}
		return (s + calcDaysOnGround()) * sundries_24;
	}
	
	public Integer calcHoursMinusDaysOnGround() {
		return calcMinutesMinusDaysOnGround() / 60;
	}
	
	public Integer calcDaysOnGround() {		
		return (calcMinutesOnGround() / 1440);
	}
	
	public Integer calcMinutesMinusDaysOnGround() {
		return (calcMinutesOnGround() - (calcDaysOnGround() * 1440));
	}
	
	public Integer calcMinutesOnGround() {
		if (offBlocksDateTime.before(onBlocksDateTime))
			return 0;
		return (int) (offBlocksDateTime.getTimeInMillis() - onBlocksDateTime.getTimeInMillis()) / 60000;
	}
	
	public Integer calcNrLunch() {
		if(isLunch())
			return calcDaysOnGround() + 1;
		else 
			return calcDaysOnGround();
	}
	
	public Integer calcNrDiner() {
		if(isDiner())
			return calcDaysOnGround() + 1;
		else 
			return calcDaysOnGround();
	}
}
