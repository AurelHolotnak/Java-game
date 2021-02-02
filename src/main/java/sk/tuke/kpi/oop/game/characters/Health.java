package sk.tuke.kpi.oop.game.characters;

import java.util.HashSet;
import java.util.Set;

public class Health {
    private int max;
    private int actual;
    private boolean first;
    private Set<ExhaustionEffect> effects = new HashSet<>();
    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public Health(int max){
        this.actual = max;
        this.max = max;
        this.first = true;
    }
    public Health(int init, int max){
        this.actual = init;
        this.max = max;
        this.first = true;
    }
    public int getValue(){return this.actual;}

    public void setMax(int newmax){this.max = newmax;}

    public void refill(int amount){
        this.actual = this.actual + amount;
        if (this.actual>max) this.actual = this.max;
    }
    public void restore(){
        this.actual = this.max;
    }
    public void drain(int amount){
        this.actual = this.actual - amount;
        if (this.actual<=0) {
            this.actual = 0;
            if(first) {
                this.first = false;
                for (ExhaustionEffect efect : effects) {
                    efect.apply();
                }
            }
        }

    }
    public void exhaust(){
        drain(this.actual);
    }
    public void onExhaustion(ExhaustionEffect effect){
        this.effects.add(effect);
    }
}
