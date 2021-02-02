package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import java.util.Random;

public class DefectiveLight extends Light implements Repairable/*<BreakableTool<Actor>>*/{
    private Random random;
    private Disposable dis;
    private boolean repaired;

    public DefectiveLight(){
        super();
        random = new Random();
        repaired=false;
    }

    private void defect(){
        //repaired=false;
        if(random.nextInt(20)==0){
            this.toggle();
        }
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        dis = new Loop<>(new Invoke<>(this::defect)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        if(!repaired) {
            this.repaired=true;
            //dis.dispose();
            dis = new ActionSequence<>(
                new Invoke<>(
                    dis::dispose
                ),
                new Wait<>(10),
                new Invoke<>(
                    () -> this.repaired = false
                ),
                new Loop<>(
                    new Invoke<>(
                        this::defect
                    )
                )

            ).scheduleFor(this);
            return true;
        }
        return false;
    }


}
