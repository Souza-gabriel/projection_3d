package Vectors;

public class Vector3D {
    private double x;
    private double y;
    private double z;
    private double w;

    public Vector3D(){

    }

    public Vector3D(double x, double y, double z, double w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector3D parseString(String[] cordinates){
        x = Double.parseDouble(cordinates[0]);
        y = Double.parseDouble(cordinates[1]);
        z = Double.parseDouble(cordinates[2]);
        return new Vector3D(x, y, z, 1);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setW(double w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
