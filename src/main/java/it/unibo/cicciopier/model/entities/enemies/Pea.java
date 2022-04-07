package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.entities.base.EntityType;

public class Pea extends SimpleProjectile {

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public Pea(final World world) {
        super(EntityType.PEA,world,Projectiles.PEA);
    }

    @Override
    public void tick() {
        super.tick();
    }
}