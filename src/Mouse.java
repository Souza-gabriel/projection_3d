import Vectors.Vector2D;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse{
    private Vector2D click;
    private Vector2D cursor;
    private MouseListener clickListner;
    private MouseMotionListener cursorListener;
    private boolean clicked = false;

    public Mouse(){
        this.cursor = new Vector2D(0,0);
        this.click = new Vector2D(0,0);
    }

    public Mouse (MouseListener clickListner, MouseMotionListener cursorListener){
        this.clickListner = clickListner;
        this.cursorListener = cursorListener;
    }

    public void click(){
        clicked = !clicked;
    }

    public boolean isClicked() {
        return clicked;
    }

    public Vector2D getClick() {
        return click;
    }

    public Vector2D getCursor() {
        return cursor;
    }

    public MouseListener getClickListner() {
        return clickListner;
    }

    public MouseMotionListener getCursorListener() {
        return cursorListener;
    }

    public void setClickListner(MouseListener clickListner) {
        this.clickListner = clickListner;
    }

    public void setCursorListener(MouseMotionListener cursorListener) {
        this.cursorListener = cursorListener;
    }

    public void setClick(Vector2D click) {
        this.click = click;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setCursor(Vector2D cursor) {
        this.cursor = cursor;
    }

}
