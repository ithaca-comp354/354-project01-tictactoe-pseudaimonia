package edu.ithaca.dragon.games.tictactoe.player;

import java.util.Scanner;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.GameStatus;
import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentEmma implements TicTacToePlayer{
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {

        String boardSpacesByRow = curBoard.buildSquaresString();
        String boardSpacesByCol = new String(new char[] {boardSpacesByRow.charAt(0), boardSpacesByRow.charAt(3), boardSpacesByRow.charAt(6), 
            boardSpacesByRow.charAt(1),boardSpacesByRow.charAt(4), boardSpacesByRow.charAt(7),
            boardSpacesByRow.charAt(2),boardSpacesByRow.charAt(5),boardSpacesByRow.charAt(8)});
        String diagonals = new String(new char[] {boardSpacesByRow.charAt(0), boardSpacesByRow.charAt(4), boardSpacesByRow.charAt(8),
            boardSpacesByRow.charAt(2), boardSpacesByRow.charAt(4), boardSpacesByRow.charAt(6)});

        char otherSymbol = "XO".split(Character.toString(yourSymbol))[0].charAt(0);
        try{
            return winOrBlock(curBoard, boardSpacesByRow, boardSpacesByCol, diagonals, yourSymbol);
        }catch(RuntimeException e){}

        try{
            return winOrBlock(curBoard, boardSpacesByRow, boardSpacesByCol, diagonals, otherSymbol);
        }catch(RuntimeException e){}

        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
                if (curBoard.isSquareOpen(new Pair<>(x,y))){
                    return new Pair<>(x,y);
                }
            }
        }

        throw new IllegalArgumentException("No move to make");
        
    }

    private Pair<Integer,Integer> winOrBlock( TicTacToeBoard curBoard,String rows, String cols, String diagonals, char symbol){

        for(int i=0;i<=6;i+=3){
            if(rows.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                return new Pair<Integer,Integer>(2,i/3);
            }
            if(rows.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                return new Pair<Integer,Integer>(0,i/3);
            }
            if(rows.substring(i,i+3).compareTo(new String(new char[] {symbol, ' ' ,symbol }))==0){
                return new Pair<Integer,Integer>(1,i/3);
            }
        }
        for(int i=0;i<=6;i+=3){
            if(cols.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                return new Pair<Integer,Integer>(i/3,2);
            }
            if(cols.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                return new Pair<Integer,Integer>(i/3,0);
            }
            if(cols.substring(i,i+3).compareTo(new String(new char[] {symbol, ' ' ,symbol }))==0){
                return new Pair<Integer,Integer>(i/3,1);
            }
        }
        for(int i=0;i<6;i+=3){
            if(i<3){
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                    return new Pair<Integer,Integer>(2,2);
                }
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                    return new Pair<Integer,Integer>(0,0);
                }
            }
            else{
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                    return new Pair<Integer,Integer>(0,2);
                }
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                    return new Pair<Integer,Integer>(2,0);
                }
            }
            if(diagonals.substring(i,i+3).compareTo( new String(new char[] {symbol, ' ' ,symbol }))==0){
                return new Pair<Integer,Integer>(1,1);
            }
        }
        
        throw new RuntimeException("no winning/blocking move");
    }

}
