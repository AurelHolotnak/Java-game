package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class AlienMother extends Alien{

    private Behaviour<? super AlienMother> behaviour;


    public AlienMother(Behaviour<? super Alien> behaviour) {
        super(behaviour);
        this.behaviour = behaviour;
        super.getHealth().setMax(200);
        super.getHealth().restore();
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }
}
