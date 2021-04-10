import Vectors.Form;
import Vectors.Vector2D;
import Vectors.Vector3D;

import java.util.List;
import java.util.Random;

public class Algorithms {
    private Canvas canvas = null;
    private int rotateSide = 1;
    public Algorithms(){
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void bresenham(int x1, int y1, int x2, int y2) {
        double e2,
                dx = Math.abs(x2-x1),
                sx = x1 < x2 ? 1 : -1,
                dy = - Math.abs(y2-y1),
                sy = y1 < y2 ? 1 : -1,
                err = dx+dy;
        while (!(x2 == x1 && y2 == y1)) {
            putpixel(x1, y1);
            e2 = 2 * err;

            if (e2 >= dy) {
                err += dy;
                x1 += sx;
            }
            if (e2 <= dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
    private void putpixel(int x, int y) {
        if(x < 0 || y < 0 || x >= Configs.WIDTH || y >= Configs.HEIGHT) return;
        int pos = (x + y * Configs.WIDTH )* 4;

        byte[] newBufferDeVideo = canvas.getBufferDeVideo();
        short[] color = canvas.getCor().getColor(0,0,0);
        newBufferDeVideo[pos] = (byte)255;
        newBufferDeVideo[pos+1] = (byte)color[0];
        newBufferDeVideo[pos+2] = (byte)color[1];
        newBufferDeVideo[pos+3] = (byte)color[2];

        canvas.setBufferDeVideo(newBufferDeVideo);

    }

    public Matriz setTranslate2D(double a, double b){
        Matriz matriz = new Matriz(3);
        matriz.setIdentity();
        matriz.getMatriz()[0][2] = a;
        matriz.getMatriz()[1][2]= b;

        return matriz;
    }
    public Matriz setTranslate3D(double a, double b, double c){
        Matriz matriz = new Matriz(4);
        matriz.setIdentity();
        matriz.getMatriz()[0][3] = a;
        matriz.getMatriz()[1][3]= b;
        matriz.getMatriz()[2][3]= c;

        return matriz;
    }

    public Matriz setRotate3D(double ang){
        double radAng = Math.toRadians(ang) * rotateSide;
        double sin = Math.sin(radAng);
        double cos = Math.cos(radAng);
        Matriz m = new Matriz(4);

        m.setIdentity();

        m.getMatriz()[1][1] = cos;
        m.getMatriz()[1][2] = -sin;

        m.getMatriz()[2][1] = sin;
        m.getMatriz()[2][2] = cos;

        return m;
    }

    public Matriz setScale(double a, double b, double c){
        Matriz m = new Matriz(4);

        m.setIdentity();
        m.getMatriz()[0][0] = a;
        m.getMatriz()[1][1] = b;
        m.getMatriz()[2][2] = c;

        return m;
    }

    public void setRotate(int angle, Vector2D pos, Vector2D click){
        double rad = Math.toRadians(angle * rotateSide);
        Vector2D aux = new Vector2D(pos.getX(), pos.getY());

        pos.setX(aux.getX()*Math.cos(rad) - aux.getY()*Math.sin(rad) + click.getX()*(1-Math.cos(rad))+click.getY()*Math.sin(rad));
        pos.setY(aux.getX()*Math.sin(rad) + aux.getY()*Math.cos(rad) + click.getY()*(1-Math.cos(rad))-click.getX()*Math.sin(rad));
    }

    public void noiseEffect(){
        Random rand = new Random();
        byte[] bufferDeVideo = canvas.getBufferDeVideo();
        for(int i = 0; i < bufferDeVideo.length;i+=4){
            int rr = rand.nextInt(255);
            int gg = rand.nextInt(255);
            int bb = rand.nextInt(255);

            bufferDeVideo[i] = (byte)0x00ff;
            bufferDeVideo[i+1] = (byte)(0x00ff&bb);
            bufferDeVideo[i+2] = (byte)(0x00ff&gg);
            bufferDeVideo[i+3] = (byte)(0x00ff&rr);
        }
    }

    public void drawForm (Form form){
        List<Vector3D> positions = form.getVector3D();
        Vector3D posAux1, posAux2;
        Matriz m = new Matriz(4);
        if(positions.size() > 1){
            m.setIdentity();
            for(int i = 0; i < positions.size(); i++){
                int lastContX = (i + 1) >= positions.size() ? 0 : i + 1;
                posAux1 = m.multiplyVector3D(positions.get(i));
                posAux2 = m.multiplyVector3D(positions.get(lastContX));
                bresenham((int)posAux1.getX(), (int)posAux1.getY(),
                        (int)posAux2.getX(), (int)posAux2.getY());
            }
        }
    }

    public void drawForms (List<Form> formToDraw){
        formToDraw.forEach(this::drawForm);
    }

    public void setRotateSide(int rotateSide) {
        this.rotateSide = rotateSide;
    }
}
