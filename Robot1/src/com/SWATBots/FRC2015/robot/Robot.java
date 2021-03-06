
package com.SWATBots.FRC2015.robot;

import java.math.*;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class Robot extends IterativeRobot {
	
	SendableChooser autoChooser;
	Auto_Command autonomousCommand;
	
	Talon leftDrive = new Talon(0);
	Talon rightDrive = new Talon(1);
	RobotDrive DriveTrain = new RobotDrive(leftDrive, rightDrive);
	Joystick DriveStick = new Joystick(0);
	Encoder DriveLeft = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
	Encoder DriveRight = new Encoder(6, 7, false, Encoder.EncodingType.k4X);
	Double DrivePower;
	
	Gyro driveCorrection = new Gyro(0);
	DriveControl speedControl = new DriveControl(DriveTrain, driveCorrection, DriveLeft, DriveRight);
	
	Joystick LiftStick = new Joystick(1);
	Victor liftMotorA = new Victor(2); 
	Victor liftMotorB = new Victor(3); 

	DigitalInput HoldingPositionSwitch = new DigitalInput(1);
	DigitalInput MaxSwitch = new DigitalInput(2);
	DigitalInput MinSwitch = new DigitalInput(0);
	
	Encoder liftEncoder = new Encoder(8, 9, false, Encoder.EncodingType.k4X);
	
	
	LiftControl lift = new LiftControl(liftMotorA, liftMotorB, MinSwitch, HoldingPositionSwitch, MaxSwitch);
	
	int High = 3, Mid = 2, Low = 1;
	

	DoubleSolenoid SolenoidLeft = new DoubleSolenoid(0,1);
	DoubleSolenoid SolenoidRight = new DoubleSolenoid(2,3);
	DoubleSolenoid SolenoidBrake = new DoubleSolenoid(4,5);
	
	ClawControl Claw = new ClawControl(SolenoidLeft, SolenoidRight);

 //   int Vision_session;
   //Image Vision_frame;
   
   double wheelPower = 0.0;
   Victor Wheel = new Victor(4);
   
   Timer autoTime = new Timer();
   
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	
    public void robotInit() {
    	liftEncoder.reset();
    	DriveRight.reset();
    	DriveLeft.reset();
    	DriveRight.setReverseDirection(true);
    	
    	autoChooser = new SendableChooser();
    	autoChooser.addDefault("Auto One", new Auto_One());
    	autoChooser.addObject("Auto Two", new Auto_Two());
    	autoChooser.addObject("Drive Backward Auto", new Auto_Three());
    	SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
    	
    	Claw.open();
    	
    	driveCorrection.initGyro();
    }
    

    /**
     * This function is called periodically during autonomous
     */
    
    Boolean autoSteponecomplete = false;
    int Auto_Mode;
    public void autonomousInit(){
    	DriveLeft.reset();
    	DriveRight.reset();
    	driveCorrection.reset();
    	autoTime.start();
    	autoTime.reset();
    	Claw.close();
    	autoSteponecomplete = false;
    	autonomousCommand = (Auto_Command) autoChooser.getSelected();
    	Auto_Mode = autonomousCommand.execute();
    }
    
   
    public void autonomousPeriodic() {
    	
    	 switch(Auto_Mode)
    	 {
    	 case 1:
    	    	SmartDashboard.putBoolean("Auto One", true);
    	    	SmartDashboard.putBoolean("Auto Two", false);
    	    	SmartDashboard.putBoolean("Auto Three", false); 
    	    	Auto_Mode_One();
    		 break;
    		 
    	 case 2:
    	    	SmartDashboard.putBoolean("Auto One", false);
    	    	SmartDashboard.putBoolean("Auto Two", true);
    	    	SmartDashboard.putBoolean("Auto Three", false);
    		 break;
    		 
    	 case 3:
 	    	SmartDashboard.putBoolean("Auto One", false);
 	    	SmartDashboard.putBoolean("Auto Two", false);
 	    	SmartDashboard.putBoolean("Auto Three", true);
 	    	Backward_Auto();
    		 break;
    	 }
    }

    public void Auto_Mode_One()
    {
    	SmartDashboard.putNumber("Right Drive Encoder", DriveRight.getRaw());
    	SmartDashboard.putNumber("Left Drive Encoder", DriveLeft.getRaw());
    	
    	if(DriveLeft.getRaw() > 7300)
    	{
    		autoSteponecomplete = true;
    	}
    	
    	if(autoSteponecomplete == false)
    	{
    		speedControl.gyroDrive(0.35);
    		if(autoTime.get()< 2)
    		{
    			lift.LiftUp(0.35);
    		}
    		else{
    		lift.LiftStop();
    		}
    	}
    	else{
    		if(DriveLeft.getRaw() > 7300)
    		{
    		DriveLeft.reset();
    		DriveRight.reset();
    		}

    			if(DriveLeft.getRaw() > -30)
    			{
    			 speedControl.gyroDrive(-0.25);
    			}
    			else{
    			autoTime.stop();
    			DriveTrain.arcadeDrive(0, 0);
    			lift.LiftStop();
    			}
    	}
    }
    
    boolean Backward_Step_One;
    
    public void Backward_Auto()
    {
    	SmartDashboard.putNumber("Right Drive Encoder", DriveRight.getRaw());
    	SmartDashboard.putNumber("Left Drive Encoder", DriveLeft.getRaw());
    	
    	if(DriveLeft.getRaw() < -7200)
    	{
    		autoSteponecomplete = true;
    	}
    	
    	if(autoSteponecomplete == false)
    	{
    		if(autoTime.get() < 3)
    		{
    			lift.LiftUp(0.35);
    		}
    		else{
    		lift.LiftStop();
    		speedControl.gyroDrive(-0.35);
    		}
    	}
    	else{
    			autoTime.stop();
    			DriveTrain.arcadeDrive(0, 0);
    			lift.LiftStop();
    	}
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopInit()
    {
    	lift.HallEffectTest = false;
    	
    	DriveRight.reset();
    	DriveLeft.reset();
    	
    /*	//Vision inits
    	try{
        Vision_frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        Vision_session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(Vision_session);
    	}
    	catch(Exception ex)
    	{  		
    	}
    	
    	try{
        NIVision.IMAQdxStartAcquisition(Vision_session);
    	}
    	catch(Exception ex)
    	{
    	}*/
    }
  
    
    public void teleopPeriodic() {	
    	   	
    	SmartDashboard.putNumber("Gyro Value", driveCorrection.getAngle());
    	SmartDashboard.putNumber("Right Drive Encoder", DriveRight.getRaw());
    	SmartDashboard.putNumber("Left Drive Encoder", DriveLeft.getRaw());
    	SmartDashboard.putNumber("Lift Encoder", liftEncoder.get());
    	SmartDashboard.putNumber("Lift Motor", liftMotorB.get());
    	SmartDashboard.putNumber("Turn Axis",DriveStick.getRawAxis(2));
    	SmartDashboard.putBoolean("Top Switch", lift.getTopSwitch());
		SmartDashboard.putBoolean("Hall Effect Sensor", lift.getHoldingSwitch());
		SmartDashboard.putBoolean("Bottom Switch", lift.getBottomSwitch());
		
    	SmartDashboard.putBoolean("Hall Effect Test", lift.HallEffectTest);
    	
    	if(lift.getHoldingSwitch())
    	{
    		lift.HallEffectTest = true;
    	}
    	
    	
    	if((DriveStick.getRawButton(2) != true) && (DriveStick.getRawButton(4) != true))
    	{

        speedControl.choosePower(DriveStick.getRawButton(8));
    	DriveTrain.arcadeDrive(speedControl.calculateSpeed(DriveStick.getRawAxis(1)), speedControl.calculateSpeed(DriveStick.getRawAxis(2)));

    	}
    	else{
    		if(DriveStick.getRawButton(2))
    		{
    			speedControl.gyroDrive(0.5);
    		}
    		else{
    			speedControl.gyroDrive(-0.5);
    		}
    	}
    	

        	//DriveTrain.arcadeDrive(-0.75*DriveStick.getRawAxis(1), -0.75*DriveStick.getRawAxis(2), true);
        	SmartDashboard.putNumber("Left Drive", leftDrive.get());
        	SmartDashboard.putNumber("Right Drive", rightDrive.get());

    /*	try{
        NIVision.IMAQdxGrab(Vision_session, Vision_frame, 1);
        CameraServer.getInstance().setImage(Vision_frame);
    	}
    	catch(Exception ex)
    	{
    	}*/
    	
    	lift.DecideMaxPower(LiftStick.getRawButton(6));
        
    	if(LiftStick.getRawButton(4) == true)
    	{
    		if(lift.getHoldingSwitch() == false)
    		{
    		lift.LiftUp(0.5);
    		}
    		else{
    			lift.LiftStop();
    		}
    	}
    	else{
        	lift.JoystickControl(LiftStick.getRawAxis(1));
    	}

    	
    	if(LiftStick.getRawButton(2))
    	{
    	Claw.open();
    	}
    	else if(LiftStick.getRawButton(3))
    	{
    		Claw.close();
    	}
    	
    	
    	if(LiftStick.getRawButton(4) && wheelPower < 1.0)
    	{
    		wheelPower += 0.0015;
    	}
    	else if(LiftStick.getRawButton(1) && wheelPower > -1.0)
    	{
    		wheelPower -= 0.0015;
    	}
    	
    	Wheel.set(wheelPower);
    	
    	Timer.delay(0.002);
    }
    
    public void disabledInit()
    {
    	/*try{
        NIVision.IMAQdxStopAcquisition(Vision_session);
    	}
    	catch(Exception e)
    	{}*/
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
