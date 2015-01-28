package com.SWATBots.FRC2015.robot;

import edu.wpi.first.wpilibj.*;

// A class for controlling the lift mechanism

public class LiftControl {

	private int High = 3, Mid = 2, Low = 1;
	private DigitalInput Holding_Switch, Release_Switch, Top_Switch, Bottom_Switch;
	private byte lift_Direction = 0;

	private Victor lift_motor;

	
	LiftControl (Victor Motor, DigitalInput holdingSwitch, DigitalInput releaseSwitch, DigitalInput TopSwitch, DigitalInput BottomSwitch)
	{
		Holding_Switch = holdingSwitch;
		Release_Switch = releaseSwitch;
		
		Top_Switch = TopSwitch;
		Bottom_Switch = BottomSwitch;
		
		lift_motor=Motor;
	}
	
	public void LiftUp(double power)
	{
		lift_motor.set(power);
		lift_Direction = 1;
	}
	
	public void LiftDown(double power)
	{
		lift_motor.set(-power);
		lift_Direction = -1;
	}
	
	public void LiftStop()
	{
		lift_motor.set(0);
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
	
	public boolean getReleaseSwitch()
	{
		return !(Release_Switch.get());
	}
	
	public boolean getTopSwitch()
	{
		return !(Top_Switch.get());
	}
	
	public boolean getBottomSwitch()
	{
		return !(Bottom_Switch.get());
	}
	
	
	
	public void setPosition(int Position)
	{
		if(Position == High)
		{
			this.setTop();
		}
		
		if(Position == Mid)
		{
			this.setMiddle();
		}
		
		if(Position == Low)
		{
			this.setBottom();
		}
	}
	
	
	
	public void setTop()
	{
		
	}
	
	public void setMiddle()
	{
		
	}
	
	public void setBottom()
	{
		
	}
	
	
}
