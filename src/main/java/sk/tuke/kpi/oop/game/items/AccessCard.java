package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor>{

    public AccessCard() {
        this.setAnimation(
            new Animation("sprites/key.png",
                16,
                16,
                0.1f)
        );
    }

    @Override
    public void useWith(@NotNull LockedDoor actor) {
        if(actor.isLocked()) actor.unlock();
    }

    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }
}
