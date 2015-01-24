package com.SWATBots.FRC2015.robot;


//This class is for drive speed control calculations with speed limit buttons

public class DriveControl {

	private double Power = 0.0; 
	
	public double calculateSpeed(double Stick){
    	return Stick*Power;
    }
	
	public void choosePower (boolean setFast)
	{
	if(setFast)
	{
		Power = 1.0;
		
	}
	else{
		
		Power = 0.5;
	}
	
}

}
