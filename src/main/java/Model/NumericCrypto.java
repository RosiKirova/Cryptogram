package Model;

import java.util.*;

public class NumericCrypto implements Cryptogram {
  
  /**************************************
   * A cryptogram Generated with numbers
   *************************************/
  
  // Cryptogram variables
  private String phrase = "";
  private String encrypted ="";
  private Pair[] numberfreq;
  public NumericCrypto(String phrase)
  {
    this.phrase = phrase.toLowerCase();
    this.encrypted = encrypt(this.phrase);
    this.numberfreq = new Pair[encrypted.length()-1];
  }
  
  
  public String getPhrase()
  {
    return this.phrase;
  }
  
  public String getEncrypted() {
    return this.encrypted;
  }
  
  public String encrypt(String phrase) {

    int j = 0;
    phrase=phrase.toLowerCase();

    List<String> num = Arrays.asList("01", "02", "03","04", "05", "06","07", "08", "09","10", "11", "12","13", "14", "15","16", "17", "18","19", "20", "21","22", "23", "24","25","26");

    Collections.shuffle(num);

    StringBuilder encrypt = new StringBuilder();

    LinkedHashMap<Character, String> cipher = new LinkedHashMap<>();

    for(int i=0; i<phrase.length(); i++)
    {

      char ch = phrase.charAt(i);

      String value = cipher.get(ch);

      if(ch!=' ' && value==null && ch!=',' && ch!='.' && ch!='\'' && ch!= ';' && ch!=':')
      {
        cipher.put(phrase.charAt(i), num.get(j));
        j++;
      }
      if(ch!=' ' && ch!=',' && ch!='.' && ch!='\'' && ch!= ';' && ch!=':')
      {
        String val = cipher.get(ch);
        encrypt.append(val);
        //encrypt.append(' ');//to distinguish between 1 and 2 digit numbers in display, for testing only
      }
      else if (ch == ' ')
      {
        encrypt.append("  ");
      }
    }

    encrypted = encrypt.toString();

    System.out.println(phrase);
    System.out.println(encrypted);

    return encrypted;

  }
  
  public int getLength()
  {
    return phrase.length();
  }

  public String getFreq() {
    String freqs = "";

    for(int i = 0; i < encrypted.length() -1; i++){
      this.numberfreq[i] = new Pair(encrypted.charAt(i), 0);
    }

    int len = numberfreq.length -1;
    for (int i = 0; i < encrypted.length() -1; i++) {
      for (int j = 0; j < len; j++) {
        if (numberfreq[j].first() == encrypted.charAt(i)) {
          numberfreq[j].incrementSecond();
        }
      }
    }

    for (Pair pair : numberfreq) {
      freqs += "Char: " + pair.first()
              + " Frequency: " + pair.secondint()
              + " out of: " + encrypted.length()
              + "\n";
    }

    freqs += "The English alphabet:\n" +
            "E:12\n"+
            "T:9\n"+
            "A:8\n"+
            "O:8\n"+
            "I:7\n"+
            "N:7\n"+
            "S:6\n"+
            "R:6\n"+
            "H:6\n"+
            "D:4\n"+
            "L:3\n"+
            "U:3\n"+
            "C:3\n"+
            "M:3\n"+
            "F:2\n"+
            "Y:2\n"+
            "W:2\n"+
            "G:2\n"+
            "P:2\n"+
            "B:1\n"+
            "V:1\n"+
            "K:1\n"+
            "X:1\n"+
            "Q:1\n"+
            "J:1\n"+
            "Z:1\n";
    return freqs;
  }
}
