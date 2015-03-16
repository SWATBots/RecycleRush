package com.SWATBots.FRC2015.robot;

import edu.wpi.first.wpilibj.*;

// A class for controlling the lift mechanism

public class LiftControl implements Runnable {

	private DigitalInput Holding_Switch, Top_Switch, Bottom_Switch;
	private byte lift_Direction = 0;
	private Thread liftThread;
	private double Lift_Full_Power = 0.8, Lift_Standard_Power = 0.5;
	private double Lift_Power_Limit = Lift_Standard_Power;

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
	
	public void DecideMaxPower(boolean full_power)
	{
		if(full_power == true)
		{
			Lift_Power_Limit = Lift_Full_Power;
		}
		else{
			Lift_Power_Limit = Lift_Standard_Power;
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
			this.LiftUp(power*Lift_Power_Limit);
		}
		else if(power < 0)
		{
			this.LiftDown(Math.abs(power)*Lift_Standard_Power);
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
