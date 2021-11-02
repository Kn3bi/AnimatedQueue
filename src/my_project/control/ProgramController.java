package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.CarRepresentation;
import my_project.view.TrafficLight;
import my_project.view.WasherGUI;

import java.awt.event.MouseEvent;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute
    private double timer;

    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private TrafficLight trafficLight;
    private Queue<CarRepresentation> carQueue;
    private CarRepresentation lastCar;
    private WasherGUI washer;

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen. Achtung: funktioniert nicht im Szenario-Modus
     */
    public void startProgram() {
        timer = 0;
        //Traffic-Light
        trafficLight = new TrafficLight();
        viewController.draw(trafficLight);
        viewController.register(trafficLight);
        // Car-Queue
        carQueue = new Queue<>();
        // Washer
        washer = new WasherGUI(this);
        viewController.draw(washer);
        viewController.register(washer);
    }

    /**
     * Sorgt dafür, dass zunächst gewartet wird, damit der SoundController die
     * Initialisierung abschließen kann. Die Wartezeit ist fest und damit nicht ganz sauber
     * implementiert, aber dafür funktioniert das Programm auch bei falscher Java-Version
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        timer += dt;
        if (timer >= 8){
            createCar();
            timer = 0;
        }
    }


    /**
     * Verarbeitet einen Mausklick.
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){
        createCar();
    }

    private void createCar(){
        CarRepresentation car;
        if(carQueue.isEmpty()) {
            car = new CarRepresentation(1010, 150, 1, null, this);
            lastCar = car;
        } else {
            car = new CarRepresentation(1010, 150, lastCar.getIndex()+1, lastCar, this);
            lastCar = car;
        }
        carQueue.enqueue(car);
        viewController.draw(car);
    }

    public void wash(){
        if(!carQueue.isEmpty()) {
            if (carQueue.front().getX() < 120) {
                carQueue.front().setWashed();
            }
        }
    }

    public void removeCar(){
        viewController.removeDrawable(carQueue.front());
        carQueue.dequeue();
    }

    public boolean getRedLight(){
        return trafficLight.isRedLight();
    }

}
