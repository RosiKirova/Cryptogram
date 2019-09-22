import Model.LetterCrypto;

import static org.junit.Assert.*;

public class LetterCryptoTest {

    @org.junit.Test
    public void encrypt() {
        LetterCrypto phraseTest = new LetterCrypto("A random phrase to be tested.");
        assertEquals(29, phraseTest.getEncrypted().length());
    }
}