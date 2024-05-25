package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Child extends SuperObject{
        public Obj_Child(){

            name = "Child";
            try{
                image = ImageIO.read(getClass().getResourceAsStream("/objects/treasure.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            collision = true;
        }
}

