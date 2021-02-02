package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.List;

public class Alien extends AbstractActor implements Movable, Enemy, Alive{
    private Animation animation;
    private int speed;
    private Direction randdir;
    private List<Direction> smery;
    private Health health;
    private Behaviour<? super Alien> behaviour;
    public Alien(){
        this.behaviour = null;
        this.health=new Health(100);
        this.animation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animation);
        this.stoppedMoving();
        this.smery = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST,Direction.EAST);
        this.speed = 2;
    }
    public Alien(Behaviour<? super Alien> behaviour){
        this.behaviour = behaviour;
        this.health=new Health(100);
        this.animation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animation);
        this.stoppedMoving();
        this.smery = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST,Direction.EAST);
        this.speed = 2;
    }
    public Alien(int health, Behaviour<? super Alien> behaviour){
        this.behaviour = behaviour;
        this.health=new Health(health);
        this.animation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animation);
        this.stoppedMoving();
        this.smery = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST,Direction.EAST);
        this.speed = 2;
    }
    public void randMove(){
        new Loop<>(
            new ActionSequence<>(
            new Invoke<>(
                this::stoppedMoving
            ),
            new Invoke<>(
                ()-> this.randdir = smery.get((int)(Math.random()*4))
            ),
                new Wait<>(0.5f),
                new Invoke<>(
                    ()->new Move<>(randdir, 0.5f).scheduleFor(this)
                )
            )
        ).scheduleFor(this);
        //this.randdir = smery.get((int)(Math.random()*4));
        //new Move<>(randdir, 10).scheduleFor(this);
    }
    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        animation.stop();
    }

    @Override
    public Health getHealth() {
        return this.health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.health.onExhaustion(()->{
            //animation.setTint(Color.RED);
            //scene.getMessageBus().publish(RIPLEY_DIED, this);
            scene.cancelActions(this);
            scene.removeActor(this);
        });
        if(this.behaviour != null) this.behaviour.setUp(this);
        for (Actor actor:scene.getActors()) {
            if(actor instanceof Alive && !(actor instanceof Enemy))
                new Loop<>(
                    new When<>(
                        () -> actor.intersects(this),
                        new Invoke<>(() -> ((Alive)actor).getHealth().drain(1))
                    )
                ).scheduleFor(this);
        }
    }

}
