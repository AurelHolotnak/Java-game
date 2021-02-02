package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;


public class Hammer extends BreakableTool<Repairable> implements Collectible, Usable<Repairable>{
    public Hammer(){
        super(1);
        setAnimation(new Animation("sprites/hammer.png"));
    }
    public Hammer(int uses){
        super(uses);
        setAnimation(new Animation("sprites/hammer.png"));
    }

    //public int getRemainingUses(){return super.getRemainingUses();}

    @Override
    public void useWith(Repairable actor) {
        if (actor != null && actor.repair()) {
            super.useWith(actor);
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}
