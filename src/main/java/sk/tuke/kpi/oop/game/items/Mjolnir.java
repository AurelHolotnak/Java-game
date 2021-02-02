package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Repairable;


public class Mjolnir extends Hammer implements Collectible, Usable<Repairable>{
    public Mjolnir(){
        super(4);
        setAnimation(new Animation("sprites/hammer.png"));
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}
