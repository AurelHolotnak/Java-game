package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.List;

public class RandomlyMoving implements Behaviour<Movable>{
    private Direction randdir;
    private List<Direction>smery = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST,Direction.EAST);

    public void randomMove(Movable actor){
        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(
                    actor::stoppedMoving
                ),
                new Invoke<>(
                    ()-> randdir = smery.get((int)(Math.random()*4))
                ),
                new Wait<>(0.5f),
                new Invoke<>(
                    ()->new Move<>(randdir, 0.5f).scheduleFor(actor)
                )
            )
        ).scheduleFor(actor);
    }

    @Override
    public void setUp(Movable actor) {
        if (actor==null) return;
        randomMove(actor);
    }

}
