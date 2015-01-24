
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
	Joystick DriveStick = new Joystick(1);
	Double DrivePower;
	DriveControl speedControl = new DriveControl();
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

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
    	
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
