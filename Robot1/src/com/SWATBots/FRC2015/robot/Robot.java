
package com.SWATBots.FRC2015.robot;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	Victor liftMotorA = new Victor(2); 
	Victor liftMotorB = new Victor(3); 

	DigitalInput HoldingPositionSwitch = new DigitalInput(2);
	DigitalInput ReleasePositionSwitch = new DigitalInput(1);
	
	DigitalInput MaxSwitch = new DigitalInput(3);
	DigitalInput MinSwitch = new DigitalInput(0);
	
	Encoder liftEncoder = new Encoder(8, 9, false, Encoder.EncodingType.k4X);

	Encoder DriveLeft = new Encoder(6, 7, false, Encoder.EncodingType.k4X);
	Encoder DriveRight = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
	
	
	LiftControl lift = new LiftControl(liftMotorA, liftMotorB, HoldingPositionSwitch, ReleasePositionSwitch, MaxSwitch, MinSwitch);
	
	int High = 3, Mid = 2, Low = 1;
	

	DoubleSolenoid SolenoidLeft = new DoubleSolenoid(0,1);
	DoubleSolenoid SolenoidRight = new DoubleSolenoid(2,3);
	DoubleSolenoid SolenoidBrake = new DoubleSolenoid(4,5);
	
	ClawControl Claw = new ClawControl(SolenoidLeft, SolenoidRight);

    CameraServer server;

    int Vision_session;
    Image Vision_frame;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	
    public void robotInit() {
    	liftEncoder.reset();
    	DriveRight.reset();
    	DriveLeft.reset();
    	
    	//Vision inits
        Vision_frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        Vision_session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(Vision_session);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    
    /**
     * This function is called periodically during operator control
     */
    public void teleopInit()
    {
    	DriveRight.reset();
    	DriveLeft.reset();
    	
        NIVision.IMAQdxStartAcquisition(Vision_session);
    }
    
    public void teleopPeriodic() {	
    	
    	SmartDashboard.putNumber("Gyro Value", driveCorrection.getAngle());
    	SmartDashboard.putNumber("Right Drive Encoder", DriveRight.getRaw());
    	SmartDashboard.putNumber("Left Drive Encoder", DriveLeft.getRaw());

    	
    	speedControl.choosePower(DriveStick.getRawButton(1));
    	DriveTrain.arcadeDrive(speedControl.calculateSpeed(DriveStick.getRawAxis(1)), speedControl.calculateSpeed(DriveStick.getRawAxis(4)));

    	
        NIVision.IMAQdxGrab(Vision_session, Vision_frame, 1);
        CameraServer.getInstance().setImage(Vision_frame);
    	
        
    	lift.JoystickControl(LiftStick.getRawAxis(1));
    	SmartDashboard.putNumber("Lift Motor", liftMotorA.get());
    	if(LiftStick.getRawButton(2))
    	{
    	Claw.open();
    	}
    	else if(LiftStick.getRawButton(3))
    	{
    		Claw.close();
    	}
    }
    
    public void disabledInit()
    {
        NIVision.IMAQdxStopAcquisition(Vision_session);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
