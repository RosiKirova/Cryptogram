package Model;

public class Pair
{
  private char left;
  private char right;
  private int rint;
  
  public Pair(char left, char right)
  {
    this.left = left;
    this.right = right;
  }

  public Pair(char left, int right)
  {
    this.left = left;
    this.rint = right;
  }
  
  public char first(){
    return left;
  }
  
  public char second(){
    return right;
  }

  public int secondint() { return rint; }

  public void incrementSecond() { rint++; }
  
}
