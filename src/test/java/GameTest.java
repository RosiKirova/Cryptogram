import Controller.Game;
import Model.Cryptogram;
import View.GamePanel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

public class GameTest {
    Game testGame;
    Cryptogram cryptogram;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private GamePanel view;

    @Before
    public void setUp()throws Exception {
        view=spy(new GamePanel());
        testGame = new Game("A random phrase to be tested", 0, view);
        cryptogram = testGame.getCryptogram();
    }

    @Test
    public void makeGuess() {
        testGame.makeGuess('a',cryptogram.getEncrypted().charAt(4));
        assertEquals(testGame.getUsersGuess().charAt(4), 'a');
    }

    @Test
    public void changeGuess() {
        testGame.makeGuess('z',cryptogram.getEncrypted().charAt(4));
        testGame.changeGuess('a','z');
        assertEquals(testGame.getUsersGuess().charAt(4), 'a');
    }
}