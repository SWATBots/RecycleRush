package com.SWATBots.FRC2015.robot;
import edu.wpi.first.wpilibj.*;
 
 
//This class is for drive speed control calculations with speed limit buttons
 
 
public class DriveControl {
 
       public RobotDrive DriveTrain; // robot drive system
       Gyro correctionGyro;
       Encoder Right_Encoder, Left_Encoder;
       private double Power = 0.0;
      
      
DriveControl (RobotDrive Drive, Gyro gyro, Encoder left, Encoder right)  
{   
	Right_Encoder = right;
	Left_Encoder = left;
	correctionGyro = gyro;
	this.DriveTrain = Drive;
}

public void gyroDrive(double speed)
{
    double Kp_gyro = 0.03;
    double angle = correctionGyro.getAngle(); // get current heading
    this.DriveTrain.drive(speed, -angle*Kp_gyro); // drive towards heading 0
}

public void encoderDrive(double speed)
{	
	double error = 0;
	double Kp_encoder = -0.1;
	error = (Right_Encoder.get()+Left_Encoder.get())/1000;
	this.DriveTrain.drive(speed, error*Kp_encoder); // drive towards heading 0
}


       public double calculateSpeed(double Stick){
       return -Stick*Power;
    }
      
       public void choosePower (boolean setFast)
       {
       if(setFast)
       {
             Power = 0.80;
            
       }
       else{
             Power = 0.50;
    	   }
       }
        
      
  }
