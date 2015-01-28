
package com.SWATBots.FRC2015.robot;
import edu.wpi.first.wpilibj.*;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class Robot extends IterativeRobot {
	
	Talon leftDrive = new Talon(0);
	Talon rightDrive = new Talon(1);
	RobotDrive DriveTrain = new RobotDrive(leftDrive, rightDrive);
	Joystick DriveStick = new Joystick(0);
	Double DrivePower;
	
	Gyro driveCorrection = new Gyro(0);
	DriveControl speedControl = new DriveControl(driveCorrection);
	
	Joystick LiftStick = new Joystick(1);
	Victor liftMotor = new Victor(2); 
	DigitalInput HoldingPositionSwitch = new DigitalInput(0);
	DigitalInput ReleasePositionSwitch = new DigitalInput(1);
	
	DigitalInput MaxSwitch = new DigitalInput(2);
	DigitalInput MinSwitch = new DigitalInput(3);
	
	Encoder liftEncoder = new Encoder(4, 5, false, Encoder.EncodingType.k4X);

	
	LiftControl lift = new LiftControl(liftMotor, HoldingPositionSwitch, ReleasePositionSwitch, MaxSwitch, MinSwitch);
	
	int High = 3, Mid = 2, Low = 1;
	
	ClawControl Claw = new ClawControl();
	
	Compressor pneumaticCompressor = new Compressor(0);
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	
    public void robotInit() {
    	liftEncoder.reset();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	speedControl.choosePower(DriveStick.getRawButton(1));
    	DriveTrain.arcadeDrive(speedControl.calculateSpeed(DriveStick.getRawAxis(1)), speedControl.calculateSpeed(DriveStick.getRawAxis(4)));
    	
    	
    	
    	if(LiftStick.getRawButton(4))
    	{
    		lift.LiftUp(0.50);
    	}
    	else if(LiftStick.getRawButton(1))
    	{
    		lift.LiftDown(0.50);
    	}
    	else{
    		lift.LiftStop();
    	}
    	
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
