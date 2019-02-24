import java.util.List;


public class Player {

  private String name;
  private List<Ship> ships;
  private List<Cell> missileTargets;

  public List<Ship> getShips() {
    return ships;
  }

  public void setShips(List<Ship> ships) {
    this.ships = ships;
  }

  public Cell getNextTarget() {
    Cell nextTarget = null;
    if(missileTargets.size() > 0){
      nextTarget = missileTargets.get(0);
      missileTargets.remove(0);
    }
    return nextTarget;
  }

  public void setMissileTargets(List<Cell> missileTargets) {
    this.missileTargets = missileTargets;
  }

  public boolean hit(Cell missileTarget){
    boolean hit = false;
    for (Ship ship : ships){
      hit = ship.hit(missileTarget);
      if(hit){
        break;
      }
    }
    return hit;
  };

  public boolean hasMoreMissiles(){
    return (missileTargets.size() > 0);
  }

  public boolean isDefeated(){
    boolean defeated = true;
    for (Ship ship : ships){
      defeated = ship.isDestroyed();
      if(!defeated){
        break;
      }
    }
    return defeated;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
