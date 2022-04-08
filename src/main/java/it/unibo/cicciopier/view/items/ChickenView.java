package it.unibo.cicciopier.view.items;

import it.unibo.cicciopier.model.items.Chicken;
import it.unibo.cicciopier.view.GameObjectView;
import it.unibo.cicciopier.view.Texture;

import java.awt.*;

/**
 * Class to render a specific chicken
 */
public class ChickenView implements GameObjectView {
    private final Chicken chicken;

    /**
     * Constructor for this class, crate an instance of a chicken view
     *
     * @param chicken what chicken to render
     */
    public ChickenView(final Chicken chicken) {
        this.chicken = chicken;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Texture.CHICKEN.getTexture(), this.chicken.getPos().getX(), this.chicken.getPos().getY(), null);
    }
}
