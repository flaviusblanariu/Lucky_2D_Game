package main;

import entity.Entity;

public class CheckCollision {
    GamePanel gp;

    public CheckCollision(GamePanel gp) {

        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidAria.x;
        int entityRightWorldX = entity.worldX + entity.solidAria.x + entity.solidAria.width;
        int entityTopWorldY = entity.worldY + entity.solidAria.y;
        int entityBottomWorldY = entity.worldY + entity.solidAria.y + entity.solidAria.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "sus":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "jos":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "stanga":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "dreapta":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                //Preia pozitia ariei solide a entitatii
                entity.solidAria.x = entity.worldX + entity.solidAria.x;
                entity.solidAria.y = entity.worldY + entity.solidAria.y;
                //Preia pozitia ariei solide a obiectului
                gp.obj[i].solidAria.x = gp.obj[i].worldX + gp.obj[i].solidAria.x;
                gp.obj[i].solidAria.y = gp.obj[i].worldY + gp.obj[i].solidAria.y;

                switch (entity.direction) {
                    case "sus":
                        entity.solidAria.y -= entity.speed;
                        if (entity.solidAria.intersects(gp.obj[i].solidAria)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "jos":
                        entity.solidAria.y += entity.speed;
                        if (entity.solidAria.intersects(gp.obj[i].solidAria)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "stanga":
                        entity.solidAria.x -= entity.speed;
                        if (entity.solidAria.intersects(gp.obj[i].solidAria)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "dreapta":
                        entity.solidAria.x += entity.speed;
                        if (entity.solidAria.intersects(gp.obj[i].solidAria)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                            break;
                        }
                }
                entity.solidAria.x = entity.solidAriaDefaultX;
                entity.solidAria.y = entity.solidAriaDefaultY;
                gp.obj[i].solidAria.x = gp.obj[i].solidAriaDefaultX;
                gp.obj[i].solidAria.y = gp.obj[i].solidAriaDefaultY;
            }
        }
        return index;
    }

}
