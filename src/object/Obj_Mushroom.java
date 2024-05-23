package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Mushroom extends SuperObject{
    public Obj_Mushroom(){

        name = "Mushroom";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/mushroom.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
