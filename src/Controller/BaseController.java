package Controller;

import Object.Player;

public class BaseController {
	protected Player mPlayer ; 
	public void setPlayer(Player player)
	{
		this. mPlayer = player;
	}
	public Player getPlayer( )
	{
		return this. mPlayer ;
	}
}
