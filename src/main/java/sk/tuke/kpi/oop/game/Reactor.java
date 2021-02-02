package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable/*<BreakableTool<Actor>>*/{
    private Set<EnergyConsumer> device;
    private int temperature;
    private int damage;
    private boolean stav;
    private boolean broken;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;
    //ctrl+p=zobrazi parameter
    public Reactor() {
        this.stav=false;
        this.broken=false;
        this.temperature = 0;
        this.damage = 0;
        device = new HashSet<>();
        // create animation object
        hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80,
            80,
            0.05f,
            Animation.PlayMode.LOOP
        );

        normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );


        brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP
        );
        offAnimation = new Animation(
          "sprites/reactor.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP
        );
        // set actor's animation to just created Animation object

        setAnimation(offAnimation);
        turnOff();
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int set){ this.damage=set; }



    public void increaseTemperature(int incr){

        if (this.isOn() && (incr >= 0)) {
            if (this.damage >= 0 && this.damage <= 33) {
                this.temperature = this.temperature + incr;
            } else if (this.damage < 66) {
                this.temperature = (int) Math.ceil(this.temperature + 1.5 * incr);
            } else /*if (this.damage >= 66) */ {
                this.temperature = this.temperature + 2 * incr;
            }

            if (this.temperature >= 6000) {
                this.broken = true;
                this.damage = 100;
                turnOff();
            }

            else if (this.temperature > 2000 && this.damage < (int) ((this.temperature - 2000) / 40))
                this.damage = (int) ((this.temperature - 2000) / 40);

            this.updateAnimation();
        }

    }

    public void decreaseTemperature(int decr){
        if (decr<0) return;
        if (this.isOn()) {
            if (this.damage >= 50 && this.damage < 100) {
                this.temperature = (int) (this.temperature - 0.5 * decr);
            }
            if (this.damage < 50 && this.damage >= 0) {
                this.temperature = (int) (this.temperature - decr);
            }
            this.updateAnimation();
        }
    }

    private void updateAnimation(){
        if(this.broken){
            setAnimation(brokenAnimation);
        }
        else if(!this.isOn()) setAnimation(offAnimation);

        else if(this.temperature>=4000 && this.temperature<6000){
            //hotAnimation.setFrameDuration(0.03f);
            setAnimation(hotAnimation);
        }
        else if(this.temperature>=0 && this.temperature<4000){
            //normalAnimation.setFrameDuration(0.1f-(float)this.damage/1000);
            setAnimation(normalAnimation);
        }


    }
    @Override
    public boolean repair(){
        if(this.damage<100 && this.damage>0){

            if(this.damage<50) {
                this.damage=0;
            }
            else{
                this.damage=this.damage-50;
            }
            //this.temperature=this.temperature-2000;
            this.temperature=40*this.damage+2000;
            this.updateAnimation();
            return true;
        }
        return false;
    }

    public boolean extinguish(){
        if(this.broken) {
            //this.extingushed=true;
            setAnimation(new Animation("sprites/reactor_extinguished.png"));
            temperature=4000;
            return true;
        }
        return false;
    }

    public boolean isOn() {return this.stav;}

    public void turnOn(){
        if(!this.broken) {
            this.stav = true;
            updateAnimation();
            for (EnergyConsumer dev : device) {
                if (dev != null) {
                    dev.setPowered(true);
                }
            }
        }
    }

    public void turnOff(){
        this.stav = false;
        updateAnimation();
        for(EnergyConsumer dev:device) {
            if (dev != null) {
                dev.setPowered(false);
            }
        }
    }

    public void addDevice(EnergyConsumer d){
        device.add(d);
        for(EnergyConsumer dev:device) {
            if (dev != null) {
                dev.setPowered(isOn());
            }
        }
    }

    public void removeDevice(EnergyConsumer d){
        d.setPowered(false);
        device.remove(d);

    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }



}
