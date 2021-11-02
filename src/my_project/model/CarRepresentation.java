package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.util.Random;

public class CarRepresentation extends GraphicalObject {

    private int index;
    private boolean washed;
    private int[] color;

    private CarRepresentation previousCar;
    private ProgramController programController;

    public CarRepresentation(double x, double y, int index, CarRepresentation previousCar, ProgramController programController){
        color = new int[3];
        Random r = new Random();
        if( r.nextInt(10) >= 3 ){
            washed = false;
            for (int i = 0; i < color.length; i++) color[i] = r.nextInt(256);
        } else {
            washed = true;
        }
        this.x = x;
        this.y = y;
        this.index = index;
        this.previousCar = previousCar;
        this.programController = programController;
        if(this.previousCar != null && x < this.previousCar.getX() +70){
            this.x = this.previousCar.getX() +70;
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(!washed) {
            drawTool.setCurrentColor(color[0], color[1], color[2], 255);
        } else {
            drawTool.setCurrentColor(0, 0, 0, 255);
        }
        drawTool.drawRectangle(x,y,60,20);
        drawTool.drawText(x,y, ""+index);
    }

    @Override
    public void update(double dt){
        if(previousCar != null && previousCar.getX()>25) {
            if (x > -300 && x > previousCar.getX() + 70) {
                move(dt);
            }
        } else {
            move(dt);
        }
    }

    private void move(double dt){
        if (x > 100) {
            x -= dt * 100;
        } else if ((!programController.getRedLight() || x < 96) && washed) {
            x -= dt * 100;
        }
        if(x < 25){
            programController.removeCar();
        }
    }

    public int getIndex(){
        return index;
    }

    public void setWashed(){
        washed = true;
    }

}
