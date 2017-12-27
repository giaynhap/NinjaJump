package Core;

import java.util.ArrayList;
import java.util.Random;

import Controller.MoveController;
import Math.Vector;
import Object.Camera;
import Object.Item;
import Object.ItemJump;
import Object.ItemSpeed;
import Object.Land;
import Object.Player;
import resource.Resource;

public class GameCore implements Runnable {
	static GameCore gameCore;
	public ArrayList<Player> lstPlayer = new ArrayList();
	public ArrayList<Item> lstItem = new ArrayList();
	public float evm_vec_Y = 0.2f;
	public Camera MainCamera = new Camera(800,600);
	public ArrayList<Land> lstMap = new ArrayList();
	public int yLand[] = new int[]{900,800,700,600,500,400,300};
	public MoveController moveHostController = new MoveController();
	public int GameState = 0;
	public int TimeStartGame;
	public int StLength = 0;
	public Timmer time = new Timmer();
	public Timmer Gametime = new Timmer();
	public static GameCore instance()
	{
		
		if (gameCore==null) gameCore = new GameCore();
		return gameCore;
	}
	Random ran = new Random();
	public GameCore()
	{
		Thread th = new Thread(this,"GameCore");
		th.start();
		gameCore = this;
		Player player = new Player();
		player.setSpriteIdle(Resource.sprNinjaIdle);
		player.setSpriteRun(Resource.sprNinjaRun);
		player.setSpriteJump(Resource.sprNinjaJump);
		player.play();
		player.setAvatar(Resource.spriteAvatar);
		moveHostController.setPlayer(player);
		MainCamera.setObject(player);
		
		lstPlayer.add(player);
		int addpos = 0;
		int currenpost = 0;
		for (int i=0;i<200;i++)
		{
			if(i>1)
			if (ran.nextInt(10)==1) addpos+=200;
			Land land = new Land();
			land.setIndexLand(ran.nextInt(2));
			land.getPosition().setX(i*300+addpos);
			 
			
			if (currenpost==0) currenpost=1;
			else
			if (currenpost== yLand.length-1 )  currenpost= yLand.length-2;
			else currenpost = 1-ran.nextInt(3)+currenpost;
			
			land.getPosition().setY(yLand[currenpost]);
			
			if (ran.nextInt(5)==1)
			{
				Item speed= (ran.nextInt(5)==1)?(new ItemJump()):new ItemSpeed();
				speed.getPosition().setX(land.getPosition().getX()+ran.nextInt(200));
				speed.getPosition().setY(land.getPosition().getY());
				lstItem.add(speed);
			}
			
			lstMap.add(land);
		}
		StLength= 300*201+addpos;
	 
		time.init();
		
	}
	public void move()
	{
		
		 if(this.time.getDelta()>10&&GameCore.instance().GameState==0)
		 {
			 	Gametime.init();
				GameCore.instance().GameState=1;
				for (Player player : lstPlayer) player.getVelocity().setX(5);
		 }
		for (Player player : lstPlayer)
		{
			player.move();
			if (!player.isAlive)  continue;
			float vecy = player.getVelocity().getY()+evm_vec_Y;
		
			float x = player.getPosition().getX()+player.getVelocity().getX();
			for (Land land : lstMap)
			{
				if (Common.CollisionRect(player,land,-1,(int)vecy)) vecy = 0;
				if (Common.CollisionRect(player,land,player.getVelocity().getIX(),-1)) 
					 x = player.getPosition().getX();
			}
			player.getVelocity().setY(vecy);
			
		
			float y = player.getPosition().getY()+vecy;
			player.getPosition().setX(x);
			player.getPosition().setY( y);
		 
			 
		
			Vector pos = player.getPosition();
			for (Item item : lstItem)
			{
				if (Common.CollisionRect(player,item,1,1)) item.add(player);
			}
			if (player.getPosition().getY()>1000)
			{
				
				int indexland =(int) ((player.getPosition().getX() )/300)-3 ; 
				if (indexland>=this.lstMap.size() ) indexland = this.lstMap.size() -2;
				
				//.reset(this.lstMap.get(indexland).getPosition().getX()+100,this.lstMap.get(indexland).getPosition().getX()-100);
				player.reset(this.lstMap.get(indexland).getPosition().getX()+100,this.lstMap.get(indexland).getPosition().getY()-100);
			}
		}
		for (Land land : lstMap)
			land.move();
		
		MainCamera.move();
	}
	@Override
	public void run() {
		 while (true)
		 {
			 try {
				Thread.sleep(10);
				
			} catch (InterruptedException e) {
			 
				e.printStackTrace();
			}
			 move();
		 }
		
	}
	
	
}
