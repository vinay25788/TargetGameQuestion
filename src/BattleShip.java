import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    Player player = new Player();
    player.setName("Player 1");
    Ship q = new ShipQ();
    configureShip(q, new Cell(5, 'E'), new Cell(1, 'A'), 1, 1);
    Ship p = new ShipP();
    configureShip(p, new Cell(5, 'E'), new Cell(4, 'D'), 2, 1);
    player.setShips(Arrays.asList(q, p));
    List<Cell> missileTargets = new ArrayList<>();
    missileTargets.add(new Cell(1, 'A'));
    missileTargets.add(new Cell(2, 'B'));
    missileTargets.add(new Cell(2, 'B'));
    missileTargets.add(new Cell(3, 'B'));
    player.setMissileTargets(missileTargets);
    return player;
  }

  public Player setupPlayer2(){

    Player player = new Player();
    player.setName("Playe 2");
    Ship q = new ShipQ();
    configureShip(q, new Cell(5, 'E'), new Cell(2, 'B'), 1, 1);
    Ship p = new ShipP();
    configureShip(p, new Cell(5, 'E'), new Cell(3, 'C'), 2, 1);
    player.setShips(Arrays.asList(q, p));
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
}
