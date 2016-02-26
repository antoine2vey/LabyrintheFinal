package com.example.deveyracantoine.labyrinthefinal;

/**
 * Created by vincent.dubois on 25/01/16.
 */
public class Maze {
    private int[][] array = {
            {1,0,1,1,1,1,1,1,1,1},
            {1,2,1,0,0,0,0,0,0,1},
            {1,0,1,1,1,0,1,1,0,1},
            {1,3    ,1,0,1,0,0,1,1,1},
            {1,0,0,0,0,0,0,1,0,0},
            {1,1,1,0,1,1,0,1,0,1},
            {1,0,1,0,0,0,0,1,0,1},
            {1,0,0,0,1,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1},
    };

    public int getWidth() {
        return array.length;
    }

    public int getHeight() {
        return array[0].length;
    }

    public int getCode(int i, int j) {
        return array[i][j];
    }

}
