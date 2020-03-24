package com.oc.rss.deminor;

public class Case {
    public boolean bomb;
    public boolean flaged;
    public boolean pushed;
    public int nbNeighbours;


    public Case(boolean bomb) {
        this.bomb = bomb;
        this.flaged = false;
        this.pushed = false;
    }

    public Case() {
        this(false);
    }

}
