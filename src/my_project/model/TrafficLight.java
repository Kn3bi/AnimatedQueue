package my_project.model;

import KAGO_framework.control.Interactable;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TrafficLight extends GraphicalObject implements Interactable {

    private boolean redLight = true;

    @Override
    public void draw(DrawTool drawTool) {
        if (redLight){
            drawTool.setCurrentColor(255,0,0,255);
        } else{
            drawTool.setCurrentColor(0,255,0,255);
        }
        drawTool.drawLine(95,55,95,400);
    }

    public boolean isRedLight(){
        return redLight;
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_SPACE){
            redLight = !redLight;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
