package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MovableController implements KeyboardListener {

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );
    private Set<Direction> naraz;
    private Movable actor;
    private Move<Movable> move;

    public MovableController(Movable actor){ this.naraz = new HashSet<>();
        this.actor = actor;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if(this.keyDirectionMap.containsKey(key)) {
            this.naraz.add(this.keyDirectionMap.get(key));
            pohyb();
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if(this.keyDirectionMap.containsKey(key)) {
            this.naraz.remove(this.keyDirectionMap.get(key));
            pohyb();
        }
    }

    private void pohyb(){
        if (this.move != null) this.move.stop();
        Direction smer=Direction.NONE;
        for(Direction d:naraz){
            smer=smer.combine(d);
        }
        if(this.naraz.size() > 0) {
            this.move= new Move<>(smer, 20);
        }
        else{
            Objects.requireNonNull(this.move).stop();
        }
        Objects.requireNonNull(actor.getScene()).scheduleAction(this.move, this.actor);
    }
}
