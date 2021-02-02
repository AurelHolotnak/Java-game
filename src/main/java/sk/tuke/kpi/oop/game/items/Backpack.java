package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private List<Collectible> items;



    public Backpack(String name, int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.name = name;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(this.items);
    }

    @Override
    public int getSize() {
        return this.items.size();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }



    @Override
    public void add(@NotNull Collectible actor) throws IllegalStateException{
        if (this.items.size() >= capacity) throw new IllegalStateException(getName() + " is full");
        this.items.add(actor);
        Objects.requireNonNull(actor.getScene()).removeActor(actor);
    }

    @Override
    public void remove(@NotNull Collectible actor) throws IllegalStateException{
        if (this.items.size() > 0/* ==0 */) /*throw new IllegalStateException(getName() + " is empty");*/
        this.items.remove(actor);
    }

    @Nullable
    @Override
    public Collectible peek() {
        if (this.items.isEmpty()) return null;
        return this.items.get(this.items.size() - 1);

    }

    @Override
    public void shift() {
        if (this.items.size() == 1 || this.items.isEmpty()) return;
        this.items.add(0, this.items.remove(this.items.size() - 1));
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return this.items.iterator();
    }
}
