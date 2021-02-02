package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.Invoke;


public class Cooler extends AbstractActor implements Switchable{
    private Reactor reactor;
    private Animation animation;
    private boolean stav;

    public Cooler(Reactor reaktor){

        animation = new Animation("sprites/fan.png",
            32,
            32,
            0.2f,
            Animation.PlayMode.LOOP);
        setAnimation(animation);
        stav=false;
        animation.stop();
        this.reactor= reaktor;
    }

    protected void coolReactor(){
        if(isOn() && reactor!=null){
            reactor.decreaseTemperature(1);
        }
    }
    @Override
    public void turnOff(){
        this.stav=false;
        animation.stop();
    }
    @Override
    public void turnOn(){
        this.stav=true;
        animation.play();
    }
    @Override
    public boolean isOn(){
        return stav;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }


    public int getReactTemp(){
        if(this.reactor!=null) return this.reactor.getTemperature();
        return -1;
    }
}
