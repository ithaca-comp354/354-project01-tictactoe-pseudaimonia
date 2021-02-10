package edu.ithaca.dragon.games.tictactoe.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.games.tictactoe.board.TwoDArrayBoard;

public class RuleBasedAgentEmmaTest {
    
    @Test
    public void chooseSquaresTest(){
        RuleBasedAgentEmma player = new RuleBasedAgentEmma();
        assertEquals(new Pair<>(0,0), player.chooseSquare(new TwoDArrayBoard("         "), 'X'));
        assertEquals(new Pair<>(2, 0), player.chooseSquare(new TwoDArrayBoard("OO   X   "), 'O'));
        assertEquals(new Pair<>(2, 2), player.chooseSquare(new TwoDArrayBoard(" X   XOO "), 'O'));
        assertEquals(new Pair<>(0, 2), player.chooseSquare(new TwoDArrayBoard("O  O X   "), 'O'));
        assertEquals(new Pair<>(2, 2), player.chooseSquare(new TwoDArrayBoard("O   OX   "), 'O'));
        assertEquals(new Pair<>(0, 2), player.chooseSquare(new TwoDArrayBoard("OX  O  XX"), 'O'));
        

        assertThrows(IllegalArgumentException.class, ()-> player.chooseSquare(new TwoDArrayBoard("OOXOOXXXX"), 'O'));
    }
}
