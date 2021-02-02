package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.Objects;

public class Bullet extends AbstractActor implements Fireable {
    private int speed;
    private Animation animation;
    public Bullet(){
        super();
        this.animation = new Animation("sprites/bullet.png", 16, 16, 0.1f);
        setAnimation(this.animation);
        this.speed = 4;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.animation.setRotation(direction.getAngle());
        this.animation.play();
    }

    @Override
    public void stoppedMoving() {
        this.animation.stop();
    }

    @Override
    public void collidedWithWall() {
        Objects.requireNonNull(getScene()).removeActor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        /*for (Actor actor:scene.getActors()) {
            new Loop<>(
            //if(actor instanceof Alive && !(actor instanceof Ripley)) {
                new When<>(
                    () -> actor.intersects(this) && actor instanceof Alive && !(actor instanceof Ripley),
                    new ActionSequence<>(
                    new Invoke<>(() -> ((Alive) actor).getHealth().drain(50)),
                    new Invoke<>(() -> scene.removeActor(this))
                    )
                )).scheduleFor(this);
            //}
        }*/
        new Loop<>(new Invoke<>(() -> scene.getActors().forEach(
            actor -> {
                if (!(actor instanceof Ripley) && actor instanceof Alive && this.intersects(actor)) {
                    ((Alive) actor).getHealth().drain(50);
                    scene.removeActor(this);
                }
            }
        ))).scheduleFor(this);

    }
}
