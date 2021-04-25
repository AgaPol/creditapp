package pl.calculator.creditapp;

public class Credit {

    public Long id;
    private int l; // credit amount - money which was borrowed
    private int y ; // okres kredytowania w latach
    private double p ; // stopy procentowe plus marża banku
    int n; // liczba rat

    public Credit(){

    };

    public Credit(int l, int y, double p ){
        this.l = l;
        this.p = p;
        this.y = y;
        n =y*12;
    }

    public int getL() {
        return l;
    }

    public double getP() {
        return p;
    }

    public int getY() {
        return y; }

    public void setL(int l) {
        this.l = l;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setY(int y) {
        this.y = y;
    }
}
