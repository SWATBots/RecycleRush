package com.SWATBots.FRC2015.robot;
import edu.wpi.first.wpilibj.*;

public class ClawControl {
	
	DoubleSolenoid Left_Solenoid, Right_Solenoid;
	
	ClawControl(DoubleSolenoid Left, DoubleSolenoid Right)
	{
		Left_Solenoid = Left;
		Right_Solenoid = Right;
	}
	
	public void open()
	{
		Left_Solenoid.set(DoubleSolenoid.Value.kReverse);
		Right_Solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void close()
	{
		Left_Solenoid.set(DoubleSolenoid.Value.kForward);
		Right_Solenoid.set(DoubleSolenoid.Value.kForward);
	}
}
