package com.example.deveyracantoine.labyrinthefinal;

/**
 * Created by vincent.dubois on 25/01/16.
 **/
public class Maze {
    private int[][] array = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,5,1,0,0,0,0,4,0,1},
            {1,0,1,1,1,0,1,1,0,1},
            {1,4,1,0,1,0,0,1,1,1},
            {1,0,0,0,4,0,0,1,0,2},
            {1,1,1,0,1,1,4,1,0,1},
            {1,0,1,0,0,0,0,1,4,1},
            {1,4,0,0,1,1,0,1,0,1},
            {1,0,1,0,1,0,0,4,0,1},
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

    public void changeValue(int i, int j, int n) {
        array[i][j] = n;
    }

}
