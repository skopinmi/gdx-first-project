package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// класс цель, движение по периметру, при попадании меняет направление движения и некоторое время (50 кадров) крутится


public class Target {

    private Texture texture;
    private float x;
    private float y;
    private float speed;
    private float scale;
    private int size;
    private boolean isGoodShoot;
    private byte howLongRotate = 0;
    private float angle;
    private boolean toRightTurn;

    public Target() {
        this.texture = new Texture("target.png");
        this.size = 610;
        this.scale = 0.2f;
        this.speed = 1.0f;
        this.x = 1280 - size/2  * scale;
        this.y = 720 - size/2 * scale;
        this.toRightTurn = true;
        this.isGoodShoot = false;
        this.angle = 0;
    }

    public void setGoodShoot(boolean goodShoot) {
        isGoodShoot = goodShoot;
    }

    public float getScale() {
        return scale;
    }

    public int getSize() {
        return size;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update(float dt) {
        if (toRightTurn) {
            if (x >= 0 + size / 2 * scale && x < 1280 - size / 2 * scale && y == 720 - size / 2 * scale) {
                x += speed;
            }
            if (x > 0 + size / 2 * scale && x <= 1280 - size / 2 * scale && y == 0 + size / 2 * scale) {
                x -= speed;
            }
            if (y >= 0 + size / 2 * scale && y <= 720 - size / 2 * scale && x == 0 + size / 2 * scale) {
                y += speed;
            }
            if (y >= 0 + size / 2 * scale && y <= 720 - size / 2 * scale && x == 1280 - size / 2 * scale) {
                y -= speed;
            }
        } else {
            if (x > 0 + size / 2 * scale && x <= 1280 - size / 2 * scale && y == 720 - size / 2 * scale) {
                x -= speed;
            }
            if (x >= 0 + size / 2 * scale && x < 1280 - size / 2 * scale && y == 0 + size / 2 * scale) {
                x += speed;
            }
            if (y >= 0 + size / 2 * scale && y <= 720 - size / 2 * scale && x == 0 + size / 2 * scale) {
                y -= speed;
            }
            if (y >= 0 + size / 2 * scale && y < 720 - size / 2 * scale && x == 1280 - size / 2 * scale) {
                y += speed;
            }
        }
        if (isGoodShoot) {
            howLongRotate = 1;
            toRightTurn = !toRightTurn;
            isGoodShoot = false;
        }
        if (howLongRotate < 50 && howLongRotate > 0) {
            howLongRotate += 1;
            angle += 90 * dt;
        } else {
            howLongRotate = 0;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - size/2, y - size/2, size/2, size/2, size, size, scale, scale, angle , 0, 0, size, size, false, false);
    }

    public void dispose() {
        texture.dispose();
    }
}
