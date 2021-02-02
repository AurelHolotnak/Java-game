package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;


public class PowerSwitch extends AbstractActor{

    private Switchable device;

    public PowerSwitch(Switchable s){
        setAnimation(new Animation("sprites/switch.png"));
        device = s;
    }

    public Switchable getDevice(){
        return device;
    }

    public void switchOn(){
        if(this.device!=null) {
            getAnimation().setTint(Color.WHITE);
            this.device.turnOn();
        }
    }

    public void switchOff(){
        if(this.device!=null) {
            getAnimation().setTint(Color.GRAY);
            this.device.turnOff();
        }
    }


    /*private Reactor reactor;
    public PowerSwitch(Reactor reaktor){
        setAnimation(new Animation("sprites/switch.png"));
        reactor = reaktor;
    }
    public void toggle(){
        if(this.reactor.isOn()){
            this.reactor.turnOff();
        }
        else{
            this.reactor.turnOn();
        }
    }*/
}
