package com.SWATBots.FRC2015.robot;

import edu.wpi.first.wpilibj.*;

// A class for controlling the lift mechanism

public class LiftControl {

	private int High = 3, Mid = 2, Low = 1;
	DigitalInput top_Switch, bottom_Switch, holding_Switch, release_Switch, Top_Switch, Bottom_Switch;


	Victor lift_motor;
	
	LiftControl (Victor Motor, DigitalInput holdingSwitch, DigitalInput releaseSwitch, DigitalInput TopSwitch, DigitalInput BottomSwitch)
	{
		holding_Switch = holdingSwitch;
		release_Switch = releaseSwitch;
		
		Top_Switch = TopSwitch;
		Bottom_Switch = BottomSwitch;
		
		lift_motor=Motor;
	}
	
	public void LiftUp(double power)
	{
		lift_motor.set(power);
	}
	
	public void LiftDown(double power)
	{
		lift_motor.set(-power);
	}
	
	public void LiftStop()
	{
		lift_motor.set(0);
	}
	
	
	
	public boolean getHoldingSwitch()
	{
		return holding_Switch.get();
	}
	
	public boolean getReleaseSwitch()
	{
		return release_Switch.get();
	}
	
	public boolean getTopSwitch()
	{
		return Top_Switch.get();
	}
	
	public boolean getBottomSwitch()
	{
		return Bottom_Switch.get();
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
