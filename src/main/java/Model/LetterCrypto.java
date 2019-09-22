package Model;

import java.util.*;

public class LetterCrypto implements Cryptogram{
  
  /**************************************
   * A cryptogram Generated with letters
   *************************************/
  
  // Cryptogram variables
  private String phrase;
  private String encrypted;
  private Pair[] numberfreq;
  
  public LetterCrypto(String phrase)
  {
    this.phrase = phrase.toLowerCase();
    this.encrypted = encrypt(this.phrase);
    this.numberfreq = new Pair[encrypted.length()-1];
  }

  public String getEncrypted() {
    return encrypted;
  }

  public String getPhrase()
  {
    return this.phrase;
  }
  
  public String encrypt(String phrase) {
    int j = 0;

    List<Character> alph = Arrays.asList('a', 'b', 'c','d', 'e', 'f','g', 'h', 'i','j', 'k', 'l','m', 'n', 'o','p', 'q', 'r','s', 't', 'u','v', 'w', 'x','y','z');

    Collections.shuffle(alph);

    StringBuilder encrypt =new StringBuilder(phrase);

    LinkedHashMap<Character, Character> cipher = new LinkedHashMap<>();

    for(int i=0; i<phrase.length(); i++)
    {
      char ch = phrase.charAt(i);

      Character value = cipher.get(ch);

      if(ch!=' ' && value==null && ch!=',' && ch!='.' && ch!='\'')
      {
        cipher.put(phrase.charAt(i), alph.get(j));
        j++;
      }
      if(ch!=' ' && ch!=',' && ch!='.' && ch!='\'')
      {
        char val = cipher.get(ch);
        encrypt.setCharAt(i, val);
      }

    }

    encrypted=encrypt.toString();

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
