package org.usfirst.frc.team5015.robot;

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
