package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;


public class Wrench<A extends DefectiveLight> extends BreakableTool<DefectiveLight> implements Collectible, Usable<DefectiveLight>{

    public Wrench(){
        super(2);
        setAnimation(new Animation("sprites/wrench.png"));
    }
    @Override
    public void useWith(DefectiveLight actor) {
        if (actor != null && actor.repair()) {
            super.useWith(actor);
        }

    }

    @Override
    public Class<DefectiveLight> getUsingActorClass() {
        return DefectiveLight.class;
    }
    //public int getRemainingUses(){return super.getRemainingUses();}
}
