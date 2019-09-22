package Model;

import java.util.ArrayList;

public class Parser {

  public static String parsePlayerListToString(ArrayList<Player> players) {
    StringBuilder buffer = new StringBuilder();
    for (Player p : players) {
      buffer.append(p.getUsername());
      buffer.append("/");
      buffer.append(p.getPlayedGames());
      buffer.append("/");
      buffer.append(p.getSuccessfulGames());
      buffer.append("/");
      buffer.append(p.getAccuracy());
      buffer.append("/");
      buffer.append(p.getTotalGuesses());
      buffer.append("/");
    }
    buffer.append("}");
    return buffer.toString();
  }


  public static ArrayList<Player> parseStringToPlayerList(String fromFile){
    ArrayList<Player> players=new ArrayList<>();
    for(int i=0;i<fromFile.length();i++) {
      StringBuilder name = new StringBuilder();
      StringBuilder played = new StringBuilder();
      StringBuilder succ = new StringBuilder();
      StringBuilder total = new StringBuilder();
      StringBuilder acc= new StringBuilder();
      while (fromFile.charAt(i) != '/') {
        name.append(fromFile.charAt(i));
        i++;
      }
      i++;

      while (fromFile.charAt(i) != '/') {
        played.append(fromFile.charAt(i));
        i++;
      }
      i++;

      while (fromFile.charAt(i) != '/') {
        succ.append(fromFile.charAt(i));
        i++;
      }
      i++;

      while (fromFile.charAt(i) != '/') {
        acc.append(fromFile.charAt(i));
        i++;
      }
      i++;

      while (fromFile.charAt(i) != '/') {
        total.append(fromFile.charAt(i));
        i++;
      }
      Player player=new Player(name.toString(),Integer.parseInt(played.toString()),Integer.parseInt(succ.toString()),Integer.parseInt(acc.toString()), Integer.parseInt(total.toString()));
      players.add(player);
      if(fromFile.charAt(i+1)=='}'){
        break;
      }
    }

    return players;
  }
}

