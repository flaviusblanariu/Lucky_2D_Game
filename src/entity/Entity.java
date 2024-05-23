package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage sus1, sus2, jos1, jos2, dreapta1, dreapta2, stanga1, stanga2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidAria;
    public int solidAriaDefaultX, solidAriaDefaultY;
    public boolean collisionOn = false;
}
