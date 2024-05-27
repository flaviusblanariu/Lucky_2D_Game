package main;

import object.Obj_Child;
import object.Obj_Door;
import object.Obj_Key;
import object.Obj_Mushroom;

public class AssetSet {
    GamePanel gp;
    public AssetSet(GamePanel gp){
        this.gp = gp;
    }

    public void setObject (){
        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 24 * gp.tileSize; //nr liniei
        gp.obj[0].worldY = 7 * gp.tileSize; //nr coloanei

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 21 * gp.tileSize;
        gp.obj[1].worldY = 39 * gp.tileSize;

        gp.obj[2] = new Obj_Key();
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4] = new Obj_Door();
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;


        gp.obj[6] = new Obj_Child();
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;

        gp.obj[7] = new Obj_Child();
        gp.obj[7].worldX = 12 * gp.tileSize;
        gp.obj[7].worldY = 7 * gp.tileSize;

        gp.obj[7] = new Obj_Mushroom();
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
    }
}
