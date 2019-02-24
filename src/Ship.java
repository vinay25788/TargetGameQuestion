import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Ship {

  private int                width;
  private int                height;
  private Cell               location;
  private Cell               boundary;
  private Map<Cell, Integer> cellStrength;

  abstract int getStrengthFactor();

  private void buildCellStrengthMap(){
    List<Cell> occupiedCells = getOccupiedCells();
    cellStrength = new HashMap<>();
    for(Cell cell : occupiedCells){
      cellStrength.put(cell, 1*getStrengthFactor());
    }
  }

  public Ship setWidth(int width) {
    this.width = width;
    return this;
  }

  public Ship setHeight(int height) {
    this.height = height;
    return this;
  }

  public Ship setLocation(Cell location) {
    this.location = location;
    return this;
  }

  public Ship setBoundary(Cell boundary) {
    this.boundary = boundary;
    return this;
  }

  public boolean isDestroyed(){
    return cellStrength.isEmpty();
  }

  public boolean hit(Cell cell){
    Integer strength = cellStrength.get(cell);
    if(strength == null){
      return false;
    } else {
      strength = strength - 1;
      if(strength == 0){
        cellStrength.remove(cell);
      } else {
        cellStrength.put(cell, strength);
      }
      return true;
    }
  }

  public Ship build(){
    if(boundary == null){
      logError("boundaries are missing");
    }
    if(location == null){
      logError("ship location is missing");
    }
    if(height <= 0 || width <= 0){
      logError("invalid ship dimension  - height  - " + height + " width - " + width);
    }
    this.buildCellStrengthMap();
    return this;
  }

  public List<Cell> getOccupiedCells(){
    List<Cell> cells = new ArrayList<>();

    for(int i = 0; i< width ; i++){
      int x = location.getX() + i;
      for(int j = 0; j<height; j++){
        int y = location.getY() + j;
        Cell c = new Cell(x, y);
        if(c.getX() > boundary.getX() || c.getY() > boundary.getY()){
          throw new RuntimeException("Invalid ship X location - (" + c.getX() + ", " + (char)c.getY() + ")");
        }
        cells.add(c);
      }
    }
    return cells;
  }

  private void logError(String msg){
    throw new RuntimeException(msg);
  }
}
