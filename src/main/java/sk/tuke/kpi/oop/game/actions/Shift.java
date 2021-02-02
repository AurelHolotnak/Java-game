package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;


public class Shift<K extends Keeper> extends AbstractAction<K> {
    //private K player;

    public Shift(){}
    /*public Shift(K player){
        this.player = player;
    }*/

    @Override
    public void execute(float deltaTime) {
        if (getActor() != null) getActor().getBackpack().shift();
        setDone(true);
    }
}
