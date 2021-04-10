import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard {
    private KeyListener keyListener;
    private Matriz translateMatriz = new Matriz(4);
    private boolean moving;
    public Keyboard(){

    }

    public Matriz getTranslateMatriz() {
        return translateMatriz;
    }

    public boolean isMoving() {
        return moving;
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public void setTranslateMatriz(Matriz translateMatriz) {
        this.translateMatriz = translateMatriz;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void movementRule(KeyEvent e, Algorithms alg){
        int a = 0, b = 0, c = 0;
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                a -= 5;
                break;
            case KeyEvent.VK_D:
                a += 5;
                break;
            case KeyEvent.VK_W:
                b -= 5;
                break;
            case KeyEvent.VK_S:
                b += 5;
                break;
            case KeyEvent.VK_Z:
                System.out.println("TESTE");
                c += 5;
                break;
            case KeyEvent.VK_C:
                System.out.println("TESTE");
                c -= 5;
                break;
        }
        moving = true;
        translateMatriz = alg.setTranslate3D(a, b, c);
    }
}
