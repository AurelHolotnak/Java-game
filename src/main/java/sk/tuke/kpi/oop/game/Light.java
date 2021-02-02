package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer{
    private boolean flow;
    private boolean stav;
    private Animation on=new Animation("sprites/light_on.png");
    private Animation off=new Animation("sprites/light_off.png");
    public Light(){
        flow=false;
        stav=false;
        setAnimation(off);
    }

    @Override
    public void setPowered(boolean a) {
        this.setElectricityFlow(a);
    }

    public void toggle(){
        if(isOn()){
            turnOff();

        }
        else{
            turnOn();
        }
    }
    @Override
    public boolean isOn(){
        return stav;
    }

    @Override
    public void turnOn(){
        this.stav=true;
        if (flow) {
            setAnimation(on);
        }
        else{
            setAnimation(off);
        }
    }
    @Override
    public void turnOff(){
        this.stav=false;
        setAnimation(off);
    }
    public void setElectricityFlow(boolean tecie){
        this.flow=tecie;
        if(isOn()){ turnOn();}

    }
}
