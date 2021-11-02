package my_project.view;

import KAGO_framework.control.Interactable;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class WasherGUI extends GraphicalObject implements Interactable {

    private ProgramController programController;

    public WasherGUI(ProgramController programController){
        this.programController = programController;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(150,150,150,200);
        drawTool.drawRectangle(110,120,40,60);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawText(30,30,"Knebi-Wash! Wasche die Autos mit \"w\" und schalte die Ampel mit der Leertaste!");
        drawTool.drawText(30,50,"Mehr Autos kommen mit jedem Mausklick! Nur schwarze Autos sind gute Autos!");
        drawTool.drawText(125,120,"W");
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_W){
            programController.wash();
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
