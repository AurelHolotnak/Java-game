package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    private Animation animation;
    private int speed;
    private Backpack backpack;
    private Animation die;
    private Health health;
    private Firearm gun;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("RIPLEY DIED", Ripley.class);
    public Ripley(){
        super();
        health = new Health(100);
        this.backpack = new Backpack("inventory", 3);
        this.die = new Animation(
            "sprites/player_die.png",
            32,
            32,
            0.1f,
            Animation.PlayMode.ONCE
        );
        this.gun = new Gun(100,300);
        this.animation = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animation);
        this.stoppedMoving();
        speed = 2;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        animation.setRotation(direction.getAngle());
        animation.play();
    }

    @Override
    public void stoppedMoving() {
        animation.stop();
    }

    public void toxic(){
        /*new Loop<>(
            new Invoke<>(
                ()->this.energy=this.energy-1
            )
        ).scheduleFor(this);*/
        if(this.health.getValue() > 0) this.health.drain(1);
        else {
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
            setAnimation(die);
        }

    }
    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    public void showRipleyState(){
        int windowHeight = Objects.requireNonNull(this.getScene()).getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        this.getScene().getGame().getOverlay().drawText("| Energy: "+this.health.getValue()+" | Ammo: "+this.getFirearm().getAmmo(), 100 , yTextPos);
        this.getScene().getGame().pushActorContainer(this.getBackpack());
    }

    @Override
    public Health getHealth() {
        return this.health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.health.onExhaustion(()->{
                setAnimation(die);
                scene.getMessageBus().publish(RIPLEY_DIED, this);
                scene.cancelActions(this);
        });
    }

    @Override
    public Firearm getFirearm() {
        return this.gun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.gun = weapon;
    }
}
