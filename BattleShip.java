import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class BattleShip {
  public void startGame(Player p1, Player p2){
    Player offender = p1;
    Player defender = p2;
    boolean play = true;
    while(play){
      boolean hit = false;
      Cell target = offender.getNextTarget();
      if(target == null){
        System.out.println(offender.getName() + " has no more missiles");
      } else {
        hit = defender.hit(target);
      }

      if(hit){
        System.out.println(offender.getName() + " fires a missile with target "
                           + (char)target.getY() + "" + target.getX() + " which hit");

      } else {
        if(target != null) {
          System.out.println(offender.getName() + " fires a missile with target "
                             + (char) target.getY() + "" + target.getX() + " which missed");
        }
        //Swap roles
        Player temp = offender;
        offender = defender;
        defender = temp;
      }
      play = (offender.hasMoreMissiles() || defender.hasMoreMissiles());
      if(play){
        play = !defender.isDefeated();
      }
      if(defender.isDefeated()){
        System.out.println(offender.getName() + " won the battle");
      }

    }
  }

  public static void main(String[] abc){
    BattleShip battleShip = new BattleShip();
    battleShip.startGame(battleShip.setupPlayer1(), battleShip.setupPlayer2());
  }

  public Player setupPlayer1(){
	  Scanner sc = new Scanner(System.in);
	  
    Player player = new Player();
    System.out.println(" Please enter the player name");
    String name = sc.next();
    player.setName(name);
	System.out.println(" how many ship you want ");
	int n = sc.nextInt();
	List<Ship> listOfShip = new ArrayList<>();
	for(int i=0;i<n;i++)
	{
		Ship sh=input();
		listOfShip.add(sh);
		
	}
    /*Ship q = new ShipQ();
    configureShip(q, new Cell(5, 'E'), new Cell(1, 'A'), 1, 1);
    Ship p = new ShipP();
    configureShip(p, new Cell(5, 'E'), new Cell(4, 'D'), 2, 1);*/
   // player.setShips(Arrays.asList(q, p));
	player.setShips(listOfShip);
    List<Cell> missileTargets = new ArrayList<>();
    missileTargets.add(new Cell(1, 'A'));
    missileTargets.add(new Cell(2, 'B'));
    missileTargets.add(new Cell(2, 'B'));
    missileTargets.add(new Cell(3, 'B'));
    player.setMissileTargets(missileTargets);
    return player;
  }

  public Player setupPlayer2(){
	  Scanner sc = new Scanner(System.in);
    Player player = new Player();
   // player.setName("Playe 2");
    
   // Ship q = new ShipQ();
    System.out.println(" Please enter the player name");
    String name = sc.next();
    player.setName(name);
    
    //Ship ship, Cell boundary, Cell location, int width, int height
    	//Scanner sc = new Scanner(System.in);
    	System.out.println(" how many ship you want ");
    	int n = sc.nextInt();
    	List<Ship> listOfShip = new ArrayList<>();
    	for(int i=0;i<n;i++)
    	{
    		Ship sh=input();
    		listOfShip.add(sh);
    		
    	}
   
    
  /*  configureShip(q, new Cell(5, 'E'), new Cell(2, 'B'), 1, 1);
    Ship p = new ShipP();
    configureShip(p, new Cell(5, 'E'), new Cell(3, 'C'), 2, 1);*/
    player.setShips(listOfShip);
    List<Cell> missileTargets = new ArrayList<>();
    missileTargets.add(new Cell(1, 'A'));
    missileTargets.add(new Cell(2, 'B'));
    missileTargets.add(new Cell(3, 'B'));
    missileTargets.add(new Cell(1, 'A'));
    missileTargets.add(new Cell(1, 'D'));
    missileTargets.add(new Cell(1, 'E'));
    missileTargets.add(new Cell(4, 'D'));
    missileTargets.add(new Cell(4, 'D'));
    missileTargets.add(new Cell(5, 'D'));
    missileTargets.add(new Cell(5, 'D'));
    player.setMissileTargets(missileTargets);
    return player;

  }

  public void configureShip(Ship ship, Cell boundary, Cell location, int width, int height){
    ship.setWidth(width)
        .setHeight(height)
        .setLocation(location)
        .setBoundary(boundary)
        .build();
  }
  
  
  public Ship input()
  {
	  Scanner sc = new Scanner(System.in);
	  System.out.println(" Please enter the ship ");
	  
	  String shp = sc.next();
	  Ship ship = null;
	  
	  if("p".equalsIgnoreCase(shp))
		  ship = new ShipP();
	  else if("q".equalsIgnoreCase(shp))
		  ship = new ShipQ();
	  
	  int x=0;
	  String y=new String();
	  System.out.println("please enter the boundary of ship, row and col");
	    
	  x = sc.nextInt();
	     y = sc.next();
	    Cell boundary = new Cell(x,y.charAt(0));
	    	
	    System.out.println("please enter the location of ship row(number value) and col(Character)");
	    x = sc.nextInt();
	    y = sc.next();
	   Cell location = new Cell(x,y.charAt(0));
	    
	   System.out.println( " Please enter the width ");
	   int width = sc.nextInt();
	   
	   System.out.println(" Please enter the hight ");
	   int height = sc.nextInt();
	   
	   configureShip(ship, boundary, location, width, height);
	   return ship;
  }
}
