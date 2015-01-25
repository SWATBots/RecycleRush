package com.SWATBots.FRC2015.robot;

import edu.wpi.first.wpilibj.*;

// A class for controlling the lift mechanism

public class LiftControl {

	private int High = 3, Mid = 2, Low = 1;
	DigitalInput topSwitch, bottomSwitch, holdingSwitch, releasingSwitch;


	Victor lift_motor;
	
	LiftControl (Victor Motor)
	{
		lift_motor=Motor;
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
