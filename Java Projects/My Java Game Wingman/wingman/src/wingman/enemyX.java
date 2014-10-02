
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.lang.*;
import java.awt.*;
import javax.swing.*;

public class enemyX extends Enemy
{
    public String c;
    //the enemy X will come from 2 directions
     //char condition;//shows the direction of enemx
    int i = 0;
    Image enemyFire;
    boolean isFire;
    Random rand;
    
    int fx, fy, fw, fh, condition;//coordinates of the enemy fire
    
    enemyX(int speed, Random gen)
     {
          super(speed, gen);//super always MUST be first ARGUMENT!!
          
          this.img[0] = gm.getSprite("Resources/enemy2L.png");
          this.enemyFire = gm.getSprite("Resources/enemybullet2.png");
          isFire = false;
          this.show = true;
          sizeX = img[0].getWidth(null);
          sizeY = img[0].getHeight(null);
          this.x=-400;
          this.y = Math.abs(gen.nextInt() % (450 - 20));
          
          fx = 50;
          fy = 50;
          fw = enemyFire.getWidth(null);
          fh = enemyFire.getHeight(null);
          
          rand = new Random();
          
          
          
     }
    
    
    public void update(int w, int h) 
        {
            //if the enemy reaches the bottom of the screen
            //its x value will be regenerated
            if(x>=500) isFire = false;
            
            if(x>=600)
            {    
               show = true;
               i = 0;
               x=-300;
               y = Math.abs(gen.nextInt() % (450 - 20));
            }
            x += speed;
            
            if(x>0 && x<250 && !isFire)
            {
                //each enemy plane on X axis has a specific probability of
                //firing
                condition = Math.abs (rand.nextInt() % 100);
                if (condition==1)
                {
                    isFire = true;
                    fx = x;
                    fy = y;
                }    
                        
            } 
            else if(isFire)
            {
                fx += (speed+2);
                if(fx==500)
                    isFire = false;
                
            } 
            
              
                
            
            
	    //detects collision. If it does,
	    //then show will be false.
            //In order to make the collision event happen,
            //show must be true which means the enemy must present
            
            //Case of collision of enemy to the Plane
            if(gm1942.m.collision(x, y, sizeX, sizeY)&& show) 
            {
                
                show = false;
                gm1942.gameEvents.setValue("Explosion");
                System.out.println("explosion");
            }
            else if(gm1942.m2.collision(x, y, sizeX, sizeY)&& show) 
            {
                
                show = false;
                gm1942.gameEvents.setValue("Explosion2");
                System.out.println("explosion2");
            }
            //Case of collision of plane to enemyFire
            else if(gm1942.m.collision(fx, fy, fw, fh)&& isFire) 
            {
                
                isFire = false;
                gm1942.gameEvents.setValue("Explosion");
                System.out.println("explosion");
            }
            else if(gm1942.m2.collision(fx, fy, fw, fh)&& isFire) 
            {
                
                isFire = false;
                gm1942.gameEvents.setValue("Explosion2");
                System.out.println("explosion2");
            }
            //Case of collison of Plane fire to enemy
            else if(gm1942.f.collision(x, y, sizeX, sizeY)&&show)
            {
                
                 show = false;
                 gm1942.gameEvents.setValue("Enemy destroyed");
                 System.out.println("Enemy Destroyed");
                
            }    
        }//end of update
    
            public void draw(Graphics g, ImageObserver obs) {
            if (show)
            {                           
                g.drawImage(img[i], x, y, obs);
                if (isFire)
                {
                   g.drawImage(enemyFire, fx, fy, obs); 
                }    
            }
            //draws the explosion images if the enemy plane
            else if(!show)
            {
                if(i<4)
                   g.drawImage(img[i++], x, y, obs);
            }     
        }

	  

   
    

}