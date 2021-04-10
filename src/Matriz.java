import Vectors.Vector2D;
import Vectors.Vector3D;

public class Matriz {
    private double[][] matriz;

    public Matriz(int size){
        matriz = new double[size][size];
    }

    public void setIdentity(){
        int aux;
        for (int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz.length; j++){
                aux = i==j? 1 : 0;
                matriz[i][j] = aux;
            }
        }
    }

    public void showMatriz(){
        int cont = 1;
        for (double[] line : matriz) {
            for (double col : line) {
                System.out.print(col + " ");
            }
            System.out.println(cont++);
        }
    }

    public double[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(double[][] matriz) {
        this.matriz = matriz;
    }

    public Vector2D multiplyVector2D(Vector2D pos){
        Vector2D returnPos = new Vector2D();

        returnPos.setX(pos.getX()* matriz[0][0] + pos.getY()* matriz[0][1] + pos.getW()* matriz[0][2]);
        returnPos.setY(pos.getX()* matriz[1][0] + pos.getY()* matriz[1][1] + pos.getW()* matriz[1][2]);
        returnPos.setW(pos.getX()* matriz[2][0] + pos.getY()* matriz[2][1] + pos.getW()* matriz[2][2]);
        return returnPos;
    }

    public Vector3D multiplyVector3D(Vector3D pos){
        Vector3D returnPos = new Vector3D();

        returnPos.setX(pos.getX()* matriz[0][0] + pos.getY()* matriz[0][1] + pos.getZ()* matriz[0][2] + pos.getW()* matriz[0][3]);
        returnPos.setY(pos.getX()* matriz[1][0] + pos.getY()* matriz[1][1] + pos.getZ()* matriz[1][2] + pos.getW()* matriz[1][3]);
        returnPos.setZ(pos.getX()* matriz[2][0] + pos.getY()* matriz[2][1] + pos.getZ()* matriz[2][2] + pos.getW()* matriz[2][3]);
        returnPos.setW(pos.getX()* matriz[3][0] + pos.getY()* matriz[3][1] + pos.getZ()* matriz[3][2] + pos.getW()* matriz[3][3]);

        return returnPos;
    }

    public void setPerspectivaSimples(double d){
        setIdentity();
        matriz[2][2] = 0;
        matriz[3][2] = 1/d;
    }
}
