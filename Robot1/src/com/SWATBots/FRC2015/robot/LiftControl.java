package com.SWATBots.FRC2015.robot;

import edu.wpi.first.wpilibj.*;

// A class for controlling the lift mechanism

public class LiftControl implements Runnable {

	private int High = 3, Mid = 2, Low = 1;
	private DigitalInput Holding_Switch, Top_Switch, Bottom_Switch;
	private byte lift_Direction = 0, lastSwitch;
	private Thread liftThread;

	private Victor lift_motor_A, lift_motor_B;
	   boolean HallEffectTest = false;

	public void run()
	{

	}
	
	LiftControl (Victor MotorA, Victor MotorB, DigitalInput BottomSwitch, DigitalInput holdingSwitch, DigitalInput TopSwitch)
	{
		Holding_Switch = holdingSwitch;
		
		Top_Switch = TopSwitch;
		Bottom_Switch = BottomSwitch;
		
		lift_motor_A = MotorA;
		lift_motor_B = MotorB;
		
		if(liftThread == null)
		{
			liftThread = new Thread(this);
			liftThread.start();
		}
		
	}
	
	public void LiftUp(double power)
	{
		if(!(this.getTopSwitch()))
		{
		lift_motor_A.set(power);
		lift_motor_B.set(power);

		lift_Direction = 1;
		}
		else{
			this.LiftStop();
		}
	}
	
	public void LiftDown(double power)
	{
		if(!(this.getBottomSwitch()))
		{
		lift_motor_A.set(-power);
		lift_motor_B.set(-power);

		lift_Direction = -1;
		}
		else{
			this.LiftStop();
		}
	}
	
	public void LiftStop()
	{
		lift_motor_A.set(0);
		lift_motor_B.set(0);

		lift_Direction = 0;
	}
	
	public int getDirection()
	{
		return lift_Direction;
	}
	
	public boolean getHoldingSwitch()
	{
		return !(Holding_Switch.get());
	}
	
	public boolean getTopSwitch()
	{
		return !(Top_Switch.get());
	}
	
	public boolean getBottomSwitch()
	{
		return !(Bottom_Switch.get());
	}
	
	public void liftUporDown(double power)
	{
		if(power > 0)
		{
			this.LiftUp(power);
		}
		else if(power < 0)
		{
			this.LiftDown(Math.abs(power));
		}
		else{
			this.LiftStop();
		}
	}
	
	public void JoystickControl(double Joystick_Value)
	{
		if(Math.abs(Joystick_Value) > 0.0001)
		{
			this.liftUporDown(-Joystick_Value);
		}
		else{
			this.LiftStop();
		}
	}
	
	
	public byte getSwitches() {
		byte sensor = 0;

		if (getBottomSwitch()) {
			sensor = 1;
		} else {
			if (getHoldingSwitch()) {
				sensor = 2;
			} else {
				if (getTopSwitch()) {
					sensor = 3;
				} else {
					sensor = 0;
				}
			}
		}

		return sensor;
	}
	
}
