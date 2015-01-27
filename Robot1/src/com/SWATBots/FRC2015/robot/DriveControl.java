package com.SWATBots.FRC2015.robot;
import edu.wpi.first.wpilibj.*;


//This class is for drive speed control calculations with speed limit buttons


public class DriveControl {

	public RobotDrive DriveTrain; // robot drive system
	double Kp = 0.03;

	private double Power = 0.0; 
	
DriveControl (Gyro gyro)	
{	
}
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
