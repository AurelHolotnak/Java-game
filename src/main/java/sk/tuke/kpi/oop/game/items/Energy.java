package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

import java.util.Objects;

public class Energy extends AbstractActor implements Usable<Alive>, Collectible {

    public Energy(){
        setAnimation(
            new Animation(
                "sprites/energy.png",
                16,
                16,
                0.1f,
                Animation.PlayMode.ONCE
            )
        );

    }
    @Override
    public void useWith(Alive actor) {
        if(actor == null) return;
        actor.getHealth().restore();
        Objects.requireNonNull(getScene()).removeActor(this);
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
