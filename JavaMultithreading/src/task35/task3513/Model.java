package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

//будет содержать игровую логику и хранить игровое поле.
public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    public int score;
    public int maxTile;
    //предыдущие состояния игрового поля
    private Stack<Tile[][]> previousStates = new Stack<>();
    // предыдущие счета
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded= true;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private boolean compressTiles(Tile[] tiles){
        boolean isChange = false;
        for (int i = 0; i < tiles.length; i++) {
            if(tiles[i].value==0&&i<tiles.length-1&&tiles[i+1].value!=0){
                Tile temp = tiles[i];
                tiles[i] = tiles[i+1];
                tiles[i+1] = temp;
                i=-1;
                isChange = true;
            }
        }
        return isChange;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i+1].value && !tiles[i].isEmpty() && !tiles[i+1].isEmpty()) {
                change = true;
                tiles[i].value *= 2;
                tiles[i+1] = new Tile();
                maxTile = maxTile > tiles[i].value ? maxTile : tiles[i].value;
                score += tiles[i].value;
                compressTiles(tiles);
            }
        }
        return change;
    }

    public void resetGameTiles(){
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        this.score = 0;
        this.maxTile = 2;
        addTile();
        addTile();
    }

    private void addTile(){
        if (!getEmptyTiles().isEmpty()) {
            ArrayList<Tile> emptyTiles = getEmptyTiles();
            int randomTileIndex = (int) (Math.random() * emptyTiles.size());
            emptyTiles.get(randomTileIndex).value = (Math.random() < 0.9) ? 2 : 4;
        }
    }

    private ArrayList<Tile> getEmptyTiles(){
        ArrayList<Tile> result = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if(gameTiles[i][j].isEmpty()){
                    result.add(gameTiles[i][j]);
                }
            }
        }
        return result;
    }

    public void left() {
        if (isSaveNeeded == true)
            saveState(gameTiles);
        boolean isCange = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) || mergeTiles(gameTiles[i]))
                isCange = true;
        }
        if (isCange)
            addTile();
        isSaveNeeded = true;
    }

     void right() {
        saveState(gameTiles);
        rotation();
        rotation();
        left();
        rotation();
        rotation();
    }

     void up() {
         saveState(gameTiles);
        rotation();
        rotation();
        rotation();
        left();
        rotation();
    }

     void down() {
         saveState(gameTiles);
        rotation();
        left();
        rotation();
        rotation();
        rotation();
    }

    private void rotation() {
        Tile[][] result = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; ++i) {
            for (int j = 0; j < FIELD_WIDTH; ++j) {
                result[i][j] = gameTiles[FIELD_WIDTH - j - 1][i];
            }
        }
        gameTiles = result;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 1; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 1; j < FIELD_WIDTH; j++) {
                if (gameTiles[j][i].value == gameTiles[j-1][i]. value)
                    return true;
            }
        }

        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] newTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                newTile[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(newTile);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty()) 
            gameTiles = previousStates.pop();

         if (!previousScores.empty())
             score = previousScores.pop();
    }

    //который будет вызывать один из методов движения случайным образом
    public void randomMove() {
        //Это число будет содержать целое псевдослучайное число в диапазоне [0..3], по каждому из которых можешь вызывать один из методов left, right, up, down.
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left(); break;
            case 1:
                right(); break;
            case 2:
                up(); break;
            case 3:
                down(); break;
        }
    }

    private boolean hasBoardChanged() {
        int sum1 = 0;
        int sum2 = 0;
        if(!previousStates.isEmpty()) {
            Tile[][] prevGameTiles = previousStates.peek();
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    sum1 += gameTiles[i][j].value;
                    sum2 += prevGameTiles[i][j].value;
                }
            }
        }
        return sum1 != sum2;
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if(hasBoardChanged()){
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }else{
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return moveEfficiency;
    }

    public void autoMove() {
        //Создадим локальную PriorityQueue с параметром Collections.reverseOrder()
        PriorityQueue<MoveEfficiency> moves = new PriorityQueue<>(4, Collections.reverseOrder());
        //Заполним PriorityQueue четырьмя объектами типа MoveEfficiency (по одному на каждый вариант хода).
        moves.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                left();
            }
        }));
        moves.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                right();
            }
        }));
        moves.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                up();
            }
        }));
        moves.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                down();
            }
        }));
        //Возьмем верхний элемент и выполним ход связанный с ним.
        moves.peek().getMove().move();
    }
}
