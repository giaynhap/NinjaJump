package Controller;

import Core.GameCore;
import Object.Player;

public class MoveController extends BaseController{
	
	public static MoveController moveController ;
	public static MoveController instance()
	{
		if (moveController==null) moveController = new MoveController();
		return moveController;
	}
	public MoveController()
	{
		moveController = this;
	}
	public void onKeyBoardEvent(int Key,int type)
	{
		// type == 0 key down
	    // type == 1 key up
		if (Key==32 && type==0) // space
		{
			if(GameCore.instance().GameState==0)
			{
			 
			
				return;
			}
			if (Math.abs(this.mPlayer.getVelocity().getY())<=0)
				this.mPlayer.getVelocity().setY(-7);
		}
	}
}
