package Controller;

import Model.Parser;
import Model.Player;


import java.io.*;

import java.util.ArrayList;

public class CustomFileParser
{
  private File file;
  private ArrayList<Player> players;

  
  
  public CustomFileParser(File file) throws IOException, NullPointerException
  {
    this.file = file;

    BufferedReader reader=new BufferedReader(new FileReader(file));
    StringBuilder string=new StringBuilder();
    String buffer;
    while((buffer=reader.readLine())!=null){
      string.append(buffer);
      string.append(System.getProperty("line.separator"));
    }
    this.players =Parser.parseStringToPlayerList(string.toString());
    if(players==null){
      players=new ArrayList<>();
    }
  }
  
  
  public Player checkUser(String uname)
  {
    for (Player x : players)
    {
      if(x.getUsername().equals(uname))
      {
        return x;
      }
    }
    return null;
  }
  
  public Player add(String uname) throws IOException
  {
    Player p = new Player(uname, 0,0,0,0);
    players.add(p);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(Parser.parsePlayerListToString(players));
    writer.close();
    
    return p;
  }
  
  
  public void updatePlayer(Player p)
  {
    for (Player x : players)
    {
      if (x.getUsername().equals(p.getUsername()))
      {
        players.set(players.indexOf(x), p);
        break;
      }
    }
  }
  
}
