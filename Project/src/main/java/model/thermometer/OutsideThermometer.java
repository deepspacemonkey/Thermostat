package model.thermometer;

import utility.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OutsideThermometer implements Runnable, PropertyChangeSubject {
    private PropertyChangeSupport property;
    private double prevTemp = 14;

    public OutsideThermometer() {
        property = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        while (true) {
            double newTemp = externalTemperature(prevTemp, -20, 20);
            property.firePropertyChange("t0", prevTemp, newTemp);
            prevTemp = newTemp;
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addListener(String evtid, PropertyChangeListener listener) {
        property.addPropertyChangeListener(evtid, listener);
    }

    @Override
    public void removeListener(String evtid, PropertyChangeListener listener) {
        property.removePropertyChangeListener(evtid, listener);
    }

    /*** Calculating the external temperature.
     * Values are only valid if the temperature is being measured
     * approximately every 10th second.
     * @param t0  the last measured external temperature*
     * @param min a lower limit (may temporally be deceeded)*
     * @param max an upper limit (may temporally be exceeded)*
     * @return an updated external temperature
     * */

    public double externalTemperature(double t0, double min, double max) {
        double left = t0 - min;
        double right = max - t0;
        int sign = Math.random() * (left + right) > left ? 1 : -1;
        t0 += sign * Math.random();
        return t0;
    }
}
