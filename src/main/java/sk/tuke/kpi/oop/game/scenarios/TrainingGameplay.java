/*package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.*;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;
import sk.tuke.kpi.oop.game.tools.Wrench;

public class TrainingGameplay extends Scenario {


    public TrainingGameplay(){
        super();
    }

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();  // vytvorenie instancie reaktora
        scene.addActor(reactor, 50, 40);  // pridanie reaktora do sceny na poziciu [x=64, y=64]
        reactor.turnOn();
        PowerSwitch sw_reactor = new PowerSwitch(reactor);
        scene.addActor(sw_reactor, 150, 40);

        Cooler cooler = new Cooler(reactor);
        scene.addActor(cooler, 50, 150);
        PowerSwitch sw_cool = new PowerSwitch(cooler);
        scene.addActor(sw_cool, 150, 150);

        Hammer hammer = new Hammer(1);
        scene.addActor(hammer, 50, 250);
        Wrench wrench = new Wrench();
        scene.addActor(wrench, 150, 250);

        //FireExtinguisher hasicak = new FireExtinguisher();
        //scene.addActor(hasicak, 100, 250);

        Light light = new Light();
        scene.addActor(light, 50, 200);
        PowerSwitch sw_light = new PowerSwitch(light);
        scene.addActor(sw_light, 150, 200);
        DefectiveLight def_light = new DefectiveLight();
        scene.addActor(def_light, 200, 200);
        reactor.addDevice(def_light);
        reactor.addDevice(light);

        ChainBomb c = new ChainBomb(5);
        scene.addActor(c, 0, 0);

        ChainBomb c1 = new ChainBomb(5);
        scene.addActor(c1, 250, 300);
        ChainBomb c11 = new ChainBomb(5);
        scene.addActor(c11, 242, 308);
        ChainBomb c12 = new ChainBomb(5);
        scene.addActor(c12, 258, 308);
        ChainBomb c13 = new ChainBomb(5);
        scene.addActor(c13, 240, 290);
        ChainBomb c14 = new ChainBomb(5);
        scene.addActor(c14, 258, 292);

        ChainBomb c2 = new ChainBomb(5);
        scene.addActor(c2, 250, 350);
        ChainBomb c3 = new ChainBomb(5);
        scene.addActor(c3, 250, 250);
        ChainBomb c4 = new ChainBomb(5);
        scene.addActor(c4, 200, 300);
        ChainBomb c6 = new ChainBomb(5);
        scene.addActor(c6, 300, 300);
        /*ChainBomb c4 = new ChainBomb(5);
        scene.addActor(c4, 270, 300);
        ChainBomb c5 = new ChainBomb(5);
        scene.addActor(c5, 270, 320);
        ChainBomb c6 = new ChainBomb(5);
        scene.addActor(c6, 270, 280);
        ChainBomb c7 = new ChainBomb(5);
        scene.addActor(c7, 230, 300);
        ChainBomb c8 = new ChainBomb(5);
        scene.addActor(c8, 230, 320);
        ChainBomb c9 = new ChainBomb(5);
        scene.addActor(c9, 230, 280);



    }


}*/
