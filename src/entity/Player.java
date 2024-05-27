package entity;

import main.KeyHandler;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidAria = new Rectangle();
        solidAria.x = 24;
        solidAria.y = 30;
        solidAriaDefaultX = solidAria.x;
        solidAriaDefaultY = solidAria.y;
        solidAria.width = 15;
        solidAria.height = 25;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX=gp.tileSize*23;//starting position of player
        worldY=gp.tileSize*21;
        speed=4;
        direction ="jos";
    }
    public void getPlayerImage(){

        try{
            sus1 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_sus1.png"));
            sus2 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_sus2.png"));
            jos1 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_jos1.png"));
            jos2 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_jos2.png"));
            dreapta1 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_dreapta1.png"));
            dreapta2 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_dreapta2.png"));
            stanga1 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_stanga1.png"));
            stanga2 = ImageIO.read(getClass().getResourceAsStream("/player/Flavian_stanga2.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed) {
                direction = "sus";
            }
            else if(keyH.downPressed){
                direction = "jos";
            }
            else if(keyH.leftPressed){
                direction = "stanga";


            }
            else if(keyH.rightPressed){
                direction = "dreapta";

            }
            //Verifica coliziune dala
            collisionOn = false;
            gp.collisionCheck.checkTile(this);

            //Verifica coliziune obiect
            int objIndex = gp.collisionCheck.checkObject(this,true);
            pickUpObject(objIndex);

            //Daca coliziunea e falsa, jucatorul se poate misca
            if(collisionOn == false){
                switch(direction){
                    case "sus":
                        worldY-=speed;
                        break;
                    case "jos":
                        worldY+=speed;
                        break;
                    case "stanga":
                        worldX-=speed;
                        break;
                    case "dreapta":
                        worldX+=speed;
                        break;
                }
            }
            spriteCounter ++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum=2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                    gp.playSoundEfect(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Ai găsit o cheie!");
                    break;
                case "Door":
                    gp.playSoundEfect(2);
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Ai deschis ușa!");
                    }
                    else{
                        gp.ui.showMessage("Ai nevoie de cheie pentru a deschide ușa!");
                    }
                    break;
                case "Mushroom":
                    gp.playSoundEfect(3);
                    speed +=2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Ai prins viteză!");
                    break;
                case "Child":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    speed = 1;
                    gp.playSoundEfect(4);
                    break;
            }
        }

    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "sus":
                if(spriteNum == 1){
                    image = sus1;
                }
                if(spriteNum == 2){
                    image = sus2;
                }
                break;
            case "jos" :
                if(spriteNum == 1){
                    image = jos1;
                }
                if(spriteNum == 2){
                    image = jos2;
                }
                break;
            case "stanga" :
                if(spriteNum == 1){
                    image = stanga1;
                }
                if(spriteNum == 2){
                    image = stanga2;
                }
                break;
            case "dreapta" :
                if(spriteNum == 1){
                    image = dreapta1;
                }
                if(spriteNum == 2){
                    image = dreapta2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
