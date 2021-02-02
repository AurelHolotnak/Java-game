package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class TimeBomb extends AbstractActor {
    private float cas;
    private boolean stav;
    private Animation bomb=new Animation("sprites/bomb.png");
    private Animation activated;
    private Animation explo;

    public TimeBomb(float s){
        this.cas=s;
        this.stav=false;
        activated = new Animation("sprites/bomb_activated.png",
            16,
            16,
            cas/6,
            Animation.PlayMode.ONCE);
        setAnimation(bomb);
        explo = new Animation(
            "sprites/small_explosion.png",
            16,
            16,
            0.1f,
            Animation.PlayMode.ONCE
        );

    }
    public void explosion(){
            new Invoke<>(
                ()-> Objects.requireNonNull(getScene()).removeActor(this)
            ).scheduleFor(this);
    }
    public void activate(){
        if(!stav) {
            stav = true;
            setAnimation(activated);
            new ActionSequence<>(
            /*new Invoke<>(
                ()-> setAnimation(activated)
            ),*/
                new Wait<>(cas),
                new Invoke<>(
                    () -> setAnimation(explo)
                ),
                //new Wait<>(0.8f),
                new When<>(
                    () -> this.getAnimation().getCurrentFrameIndex() >= this.getAnimation().getFrameCount() - 1,
                    new Invoke<>(
                        this::explosion
                    )
                )
            ).scheduleFor(this);
        }
    }
    /*public void remove(){
        getScene().removeActor(this);
    }*/
    public boolean isActivated(){ return stav;}
}
