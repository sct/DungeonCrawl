package com.sctgaming.dungeoncrawl.core.ui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.sctgaming.dungeoncrawl.core.entity.Item;
import com.sctgaming.dungeoncrawl.core.entity.Player;
import com.sctgaming.dungeoncrawl.core.entity.Properties;
import com.sctgaming.dungeoncrawl.core.entity.type.PlayerType;
import com.sctgaming.dungeoncrawl.core.ui.Component;
import com.sctgaming.dungeoncrawl.core.ui.GameOverlay;

public class InventoryHolder extends Component {
    private boolean visible = false;
    private Player player;
    private Table table;
    private NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("np_background.png")), 10, 10, 10, 10);
    private Skin skin = new Skin();

    private final float CONTAINER_WIDTH = 600;
    private final float CONTAINER_HEIGHT = 400;
    private float CONTAINER_X;
    private float CONTAINER_Y;

    public InventoryHolder(Player player) {
        this.player = player;

        CONTAINER_X = (GameOverlay.camera.viewportWidth / 2) - (CONTAINER_WIDTH / 2);
        CONTAINER_Y = (GameOverlay.camera.viewportHeight / 2) - (CONTAINER_HEIGHT / 2);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setVisible() {
        if (visible) {
            visible = false;
        } else {
            visible = true;
        }
    }

    @Override
    public void render(float dt) {
        if (isVisible()) {
            getBatch().begin();

            patch.draw(getBatch(), CONTAINER_X, CONTAINER_Y, CONTAINER_WIDTH, CONTAINER_HEIGHT);

            int x = 0;
            for (Item item : player.getInventory()) {
                getFont().draw(getBatch(), "Item: " + item.getType().getName() + " [STR: " + item.getProperty(Properties.STR) + " AGI: " + item.getProperty(Properties.AGI) + " CON: " + item.getProperty(Properties.CON) + "]", CONTAINER_X + 10, CONTAINER_Y + 10 + x * 20);
                x++;
            }

            getBatch().end();
        }
    }
}
