package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestingComputerMoveTest {

    TestingComputerMove testingComputerMove = new TestingComputerMove();


    @Test
    public void computerBestMoveTest1(){
        String[][] gameTab1 = {{"O", "X", "O"},{"X", "X", "O"},{"O", "", ""}};
        assertEquals(2, testingComputerMove.makeComputerBestMove(gameTab1)[0]);
        assertEquals(1, testingComputerMove.makeComputerBestMove(gameTab1)[1]);
    }

    @Test
    public void computerBestMoveTest2(){
        String[][] gameTab1 = {{"", "", ""},{"", "", ""},{"", "", ""}};
        assertEquals(0, testingComputerMove.makeComputerBestMove(gameTab1)[0]);
        assertEquals(0, testingComputerMove.makeComputerBestMove(gameTab1)[1]);
    }

    @Test
    public void computerBestMoveTest3(){
        String[][] gameTab1 = {{"O","X","O"},{"X","X",""},{"O","",""}};
        assertEquals(1, testingComputerMove.makeComputerBestMove(gameTab1)[0]);
        assertEquals(2, testingComputerMove.makeComputerBestMove(gameTab1)[1]);
    }

    @Test
    public void computerBestMoveTest4(){
        String[][] gameTab1 = {{"O","X","O"},{"X","X","O"},{"O","","X"}};
        assertEquals(2, testingComputerMove.makeComputerBestMove(gameTab1)[0]);
        assertEquals(1, testingComputerMove.makeComputerBestMove(gameTab1)[1]);
    }

    @Test
    public void computerBestMoveTest5(){
        String[][] gameTab1 = {{"X", "O", "X"},{"O", "X", "O"},{"", "", ""}};
        assertEquals(2, testingComputerMove.makeComputerBestMove(gameTab1)[0]);
        assertEquals(0, testingComputerMove.makeComputerBestMove(gameTab1)[1]);
    }

    @Test
    public void checkWinner1(){
        String[][] gameTab1 = {{"X", "O", "X"},{"O", "X", "O"},{"", "", "X"}};
        assertEquals("X", testingComputerMove.checkIfWin(gameTab1));
    }

    @Test
    public void checkWinner2(){
        String[][] gameTab1 = {{"X", "O", "X"},{"O", "X", "O"},{"X", "", ""}};
        assertEquals("X", testingComputerMove.checkIfWin(gameTab1));
    }

    @Test
    public void checkWinner3(){
        String[][] gameTab1 = {{"X", "X", "X"},{"O", "X", "O"},{"O", "", ""}};
        assertEquals("X", testingComputerMove.checkIfWin(gameTab1));
    }

    @Test
    public void checkWinner4(){
        String[][] gameTab1 = {{"X", "O", "X"},{"X", "O", "O"},{"X", "", ""}};
        assertEquals("X", testingComputerMove.checkIfWin(gameTab1));
    }

    @Test
    public void checkWinner5(){
        String[][] gameTab1 = {{"X", "O", "X"},{"", "O", "O"},{"X", "", ""}};
        assertEquals("", testingComputerMove.checkIfWin(gameTab1));
    }


}