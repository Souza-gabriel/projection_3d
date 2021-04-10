package Vectors;

public class Vector2D {
    private double x;
    private double y;
    private double w = 1;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setW(double w) {
        this.w = w;
    }
}
