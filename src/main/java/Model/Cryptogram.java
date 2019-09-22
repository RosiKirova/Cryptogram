package Model;

public interface Cryptogram {
  
  
  
  // TODO  Frequency, Phrase, encryptedPhrase,
  String getPhrase();
  String getEncrypted();
  String encrypt(String phrase);
  int getLength();
  String getFreq();
  
}
