package com.SWATBots.FRC2015.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// A class for controlling the lift mechanism

public class LiftControl implements Runnable {

	private int High = 3, Mid = 2, Low = 1;
	private DigitalInput Holding_Switch, Release_Switch, Top_Switch, Bottom_Switch;
	private byte lift_Direction = 0, lastSwitch;
	private Thread liftThread;

	private Victor lift_motor;

	public void run()
	{
		while (true)
		{
			SmartDashboard.putNumber("Switch", this.getSwitches());
		}
	}
	
	LiftControl (Victor Motor, DigitalInput holdingSwitch, DigitalInput releaseSwitch, DigitalInput TopSwitch, DigitalInput BottomSwitch)
	{
		Holding_Switch = holdingSwitch;
		Release_Switch = releaseSwitch;
		
		Top_Switch = TopSwitch;
		Bottom_Switch = BottomSwitch;
		
		lift_motor=Motor;
		
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
		lift_motor.set(power);
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
		lift_motor.set(-power);
		lift_Direction = -1;
		}
		else{
			this.LiftStop();
		}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public byte getSwitches()
	{
		byte sensor = 0;
		
		if(getBottomSwitch())
		{
			sensor = 1;
		}
		else{
			if(getReleaseSwitch())
			{
				sensor = 2;
			}
			else{
				if(getHoldingSwitch())
				{
					sensor = 3;
				}
				else{
					if(getTopSwitch())
					{
						sensor = 4;
					}
					else{
						sensor = 0;
					}
				}
			}
		}
		
		return sensor;
	}
	
	
	
	public byte findZone()
	{
		byte zone = -1;
		byte Switch = getSwitches();
		
		if(Switch != 0)
		{
			lastSwitch = Switch;
			zone = (byte) ((Switch - 1)*2);
		}
		else{
			zone = (byte) ((lastSwitch - 1)*2);
			zone += this.getDirection();
		}
		
		return zone;
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
