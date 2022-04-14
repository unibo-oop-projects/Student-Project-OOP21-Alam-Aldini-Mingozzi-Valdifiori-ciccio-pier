package it.unibo.cicciopier.model.entities.enemies;

import it.unibo.cicciopier.controller.GameLoop;
import it.unibo.cicciopier.model.World;
import it.unibo.cicciopier.model.blocks.base.Block;
import it.unibo.cicciopier.model.entities.EntityState;
import it.unibo.cicciopier.model.entities.base.EntityType;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.entities.enemies.ShootingPeaView;

/**
 * Represents the enemy ShootingPea, a walking pea whom attack consists into shooting
 * peas bursting from the pod's bottom.
 */
public class ShootingPea extends SimplePathEnemy {
    private static final int SCORE_VALUE = 50;
    private static final int HEALTH_VALUE = 50;
    private static final int STAMINA_VALUE = 50;
    private static final int ATTACK_RANGE = 7 * Block.SIZE;
    private static final int IDLE_DURATION = 2 * GameLoop.TPS;
    private static final int MAX_RIGHT_OFFSET = 3 * Block.SIZE;
    private static final double ATTACK_SPEED = 9d * Block.SIZE / GameLoop.TPS;
    private static final int ATTACK_COOLDOWN = 2 * GameLoop.TPS;
    public static final int ATTACK_DURATION_TICKS = 90;
    private static final double MOVEMENT_SPEED = (0.7 * Block.SIZE) / GameLoop.TPS;
    public static final int ATTACK_DURATION = 2 * GameLoop.TPS;

    private final ShootingPeaView view;
    private boolean shot;
    private int attackDurationTicks;

    /**
     * Constructor for this class
     *
     * @param world The game's world
     */
    public ShootingPea(final World world) {
        super(EntityType.SHOOTING_PEA, world);
        this.attackDurationTicks = 0;
        this.shot = false;
        this.view = new ShootingPeaView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectView getView() {
        return this.view;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealValue() {
        return HEALTH_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStaminaValue() {
        return STAMINA_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScoreValue() {
        return SCORE_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getIdleDuration() {
        return IDLE_DURATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackRange() {
        return ATTACK_RANGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxRightOffset() {
        return MAX_RIGHT_OFFSET;
    }




    /**
     * {@inheritDoc}
     */
    @Override
    protected void attacking() {
        this.getVel().setX(0);
        if (this.getShootingCooldownTicks() == 0) {
            this.shot = false;
            this.resetCurrentState(EntityState.ATTACKING);
        }
        if (this.getCurrentState() == EnemyState.ATTACKING) {
            this.attackDurationTicks++;
        }
        if (this.attackDurationTicks >= ATTACK_DURATION) {
            this.setShootingCooldownTicks(ATTACK_COOLDOWN);
            this.attackDurationTicks = 0;
            this.resetCurrentState(EntityState.IDLE);
        } else if (this.attackDurationTicks >= ATTACK_DURATION / 2 && !this.shot) {
            this.shoot(ATTACK_SPEED, EntityType.PEA);
            this.shot = true;
            this.setShootingCooldownTicks(ATTACK_COOLDOWN);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void notAttacking() {
        this.attackDurationTicks = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(final long ticks) {
        super.tick(ticks);
    }
}
