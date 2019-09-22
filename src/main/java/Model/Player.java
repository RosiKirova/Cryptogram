package Model;

public class Player
{
  /***************************
   * Model of the player class
   ***************************/
  
  // Declare variables used, saved game loaded from file and not handled here
  private String username;
  private int
      playedGames,
      successfulGames,
      accuracy,
      totalGuesses;
  
  /***************
   * Constructors
   **************/
  
  public Player(String username)
  {
    this.username = username;
    this.playedGames = 0;
    this.successfulGames = 0;
    this.accuracy = 0;
    this.totalGuesses = 0;
  }
  
  public Player(String username, int playedGames, int successfulGames, int accuracy, int totalGuesses)
  {
    this.username = username;
    this.playedGames = playedGames;
    this.successfulGames = successfulGames;
    this.accuracy = accuracy;
    this.totalGuesses = totalGuesses;
  }
  
  /**********
   * Setters
   *********/
  public void updateAccuracy(int accuracy)
  {
    this.accuracy+= accuracy;
  }
  
  public void incrementPlayedGames()
  {
    this.playedGames++;
  }
  
  public void incrementSuccessfulGames()
  {
    this.successfulGames++;
  }
  
  public void incrementTotalGuesses(int totalGuesses)
  {
    this.totalGuesses = totalGuesses++;
  }
  
  /**********
   * Getters
   *********/
  
  public String getUsername(){return username;}
  
  public int getAccuracy()
  {
    return accuracy;
  }
  
  public int getPlayedGames()
  {
    return playedGames;
  }
  
  public int getSuccessfulGames()
  {
    return successfulGames;
  }
  
  public int getTotalGuesses() { return totalGuesses; }
  
  @Override
  public String toString()
  {
    return "Player{" +
        "username='" + username + '\'' +
        ", playedGames=" + playedGames +
        ", successfulGames=" + successfulGames +
        ", accuracy=" + accuracy +
        ", totalGuesses=" + totalGuesses +
        '}';
  }
}
