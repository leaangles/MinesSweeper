package com.oc.rss.deminor;
import java.util.Random;

public class Deminor {
    int n;
    int m;
    Case[][] table;
    int nbBombes;
    boolean flag;

    public Deminor(int n, int m, int nbBombes) {
        this.n = n;
        this.m = m;
        this.table = new Case[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                table[x][y] = new Case();
            }
        }
        this.nbBombes = nbBombes;
        this.flag = false;
    }

    public void init() {
        int k = 0;
        while (k < nbBombes) {
            Random r = new Random();
            int i = r.nextInt(n);
            int j = r.nextInt(m);
            if ( !table[i][j].bomb) {
                table[i][j].bomb = true;
                k++;
            }
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                table[x][y].nbNeighbours = getNeighbours(x, y);
            }
        }
    }

    public boolean click(int i, int j) {
        if (flag) {
            table[i][j].flaged = !table[i][j].flaged;
        } else {
            if (table[i][j].bomb) {
                return false;
            } else {
                table[i][j].pushed = true;
            }
        }
        return true;
    }

    public int getNeighbours(int i, int j) {
        int res = 0;
        int[][] neighbours = {{i+1, j-1}, {i+1, j}, {i+1, j+1}, {i, j-1}, {i, j+1},
                {i-1, j-1}, {i-1, j}, {i-1, j+1}};
        for (int[] pos : neighbours) {
            int x = pos[0];
            int y = pos[1];
            if (0 < x && x < n && 0 < y && y < m && table[x][y].bomb) {
                res++;
            }
        }
        return res;
    }

}
