package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
//import sk.tuke.kpi.oop.game.Reactor;
//import sk.tuke.kpi.oop.game.Repairable;


import java.util.Objects;

public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A>{
    private int remainingUses;

    public BreakableTool(int pocet){
        remainingUses = pocet;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    @Override
    public void useWith(A actor) {
        if(actor==null) return;
        if(this.remainingUses>0) this.remainingUses -= 1;
        if(this.remainingUses==0){
            Objects.requireNonNull(getScene()).removeActor(this);
        }
    }
}
