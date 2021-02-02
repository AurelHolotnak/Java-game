package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;


import java.util.Objects;

public class Ventilator extends AbstractActor implements Repairable {
    private boolean repaired;
    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("VENTILATOR_REPAIRED", Ventilator.class);
    public Ventilator(){
        repaired = false;
        setAnimation(new Animation(
            "sprites/ventilator.png",
            32,
            32,
            0.1f,
            Animation.PlayMode.LOOP
            )
        );
        this.getAnimation().stop();
    }

    @Override
    public boolean repair() {
        if(!repaired) {
            repaired = true;
            Objects.requireNonNull(getScene()).getMessageBus().publish(VENTILATOR_REPAIRED, this);
            getAnimation().play();
            return true;
        }
        return false;
    }
}
