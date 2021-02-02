package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer{
    private boolean flow;
    private Animation animation;
    public Computer(){
        animation = new Animation(
            "sprites/computer.png",
            80,
            48,
            0.2f,
            Animation.PlayMode.LOOP
        );
        setAnimation(animation);
        flow = false;
    }

    @Override
    public void setPowered(boolean a) {
        this.flow=a;
        if(this.flow) animation.play();
        else animation.pause();
    }




    public int add(int a, int b){
        if(flow) return a+b;
        else return 0;
    }
    public float add(float a, float b){
        if(flow) return a+b;
        else return 0;
    }
    public int sub(int a, int b){
        if(flow) return a-b;
        else return 0;
    }
    public float sub(float a, float b){
        if(flow) return a-b;
        else return 0;
    }

}
