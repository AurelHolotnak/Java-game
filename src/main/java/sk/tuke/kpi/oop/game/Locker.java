package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class Locker extends AbstractActor implements Usable<Ripley> {

    private boolean opened;

    public Locker(){
        super();
        opened = false;
        setAnimation(new Animation(
            "sprites/locker.png",
            16,
            16,
            0.1f,
            Animation.PlayMode.ONCE
            )
        );
    }

    @Override
    public void useWith(Ripley actor) {
        if(!opened) {
            opened = true;
            Hammer hammer = new Hammer();
            Objects.requireNonNull(getScene()).addActor(hammer, this.getPosX()-2, this.getPosY()-2);
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
