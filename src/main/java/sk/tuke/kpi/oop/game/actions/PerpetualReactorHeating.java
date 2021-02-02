package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;
import java.util.Objects;

public class PerpetualReactorHeating extends AbstractAction<Reactor>{
    private int incr;
    public PerpetualReactorHeating(int increment){
        this.incr=increment;
    }

    @Override
    public void execute(float deltaTime) {
        if(this.getActor()!=null ) Objects.requireNonNull(getActor()).increaseTemperature(incr);
    }


}

