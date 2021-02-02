package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class FireExtinguisher<A extends Reactor> extends BreakableTool<Reactor> implements Collectible, Usable<Reactor>{

    public FireExtinguisher(){
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png"));
    }
    //public int getRemainingUses(){return super.getRemainingUses();}
    @Override
    public void useWith(Reactor actor) {
        if (actor != null && actor.extinguish()) {
            super.useWith(actor);
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}
