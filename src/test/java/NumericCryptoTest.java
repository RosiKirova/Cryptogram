import Model.LetterCrypto;
import Model.NumericCrypto;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumericCryptoTest {

    @Test
    public void encrypt() {
        NumericCrypto phraseTest = new NumericCrypto("a");
        String encryptedPhraseTest = phraseTest.getEncrypted();
        assertTrue(Integer.parseInt("1") < Integer.parseInt(encryptedPhraseTest) && Integer.parseInt(encryptedPhraseTest) < Integer.parseInt("26"));
    }
}