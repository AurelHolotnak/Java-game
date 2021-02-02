package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class SmartCooler extends Cooler{

    public SmartCooler(Reactor reaktor) {
        super(reaktor);
    }

    @Override
    protected void coolReactor() {
        if(super.getReactTemp()==-1) return;
        if(super.getReactTemp()<1500) turnOff();
        if(super.getReactTemp()>2500) turnOn();
        super.coolReactor();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
