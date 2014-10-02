
package wingman;

//after detsrying the enemy planes

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.lang.*;
import java.awt.*;
import javax.swing.*;

//the boss will appear and if its defeated then
//the user will win the game. The boss will diagnal shoots

public class Boss
{
        Image img[], fire;
        int x, y, speed, sizeX, sizeY, fx, fy, fw, fh;
        //fx and fy are the sizes of the fire
        int index, health;
        boolean exploded, toRight, isFire;
        public static gm1942 gm = new gm1942();
        int condition;
        Random rand;
        
       
        
        Boss(int speed)
        {

            this.img = new Image[5];
            this.img[0] = gm.getSprite("Resources/myBoss.gif");
            this.img[1] = gm.getSprite("Resources/explosion2_2.png");
            this.img[2] = gm.getSprite("Resources/explosion2_3.png");
            this.img[3] = gm.getSprite("Resources/explosion2_5.png");
            this.img[4] = gm.getSprite("Resources/explosion2_7.png");
            
            //The components of the boss Fire
            fire = gm.getSprite("Resources/fire.gif");

            fw = fire.getWidth(null);
            fh = fire.getHeight(null);
            fx = 25;
            fy = y + 40;
                  
            sizeX = img[0].getWidth(null);
            sizeY = img[0].getHeight(null);
            
            x = 50;
            y = -20;
            this.speed = speed;
            exploded = isFire= false;
            toRight = true;
            health = 100;
            index = 0;
            condition = 1;
            rand = new Random();
            
        }
        
        public void update() 
        {
            //This is the moving coordinates of the boss object
            //it will move back and forth hoizontally at the
            //top of the screen. The boss has 100 health point, which means
            //the plane must shot it several times to kill.
            if(condition == 1)
            {
                x += speed;
                if(x>480)
                    condition = 2;
            }    
            else if(condition == 2)
            {
                x -= speed;
                if(x< 25)
                     condition = 1;   
            } 
            
            if (!exploded)
                index = 0;
            
            if(fy>=500) isFire = false;
            
            if(!exploded&& !isFire)
            {
                //The boss fires at random times
                //It will have a 1/90 probability to fire
                //at a time
                int hit = Math.abs (rand.nextInt() % 90);
                if (hit==1)
                {
                    isFire = true;
                    //sets the initial position
                    //of the fire image
                    fx = x+26;
                    fy = y+80;
                }
            }
            else if(isFire)
            {
                fy += (speed+2);
                if(fy==600)
                    isFire = false;
                
            } 

            if(gm1942.f.collision(x, y+120, sizeX, sizeY-120)&&!exploded)
            {
                 if(health<= 0&&!exploded)
                 {    
                 exploded = true;
                 gm1942.gameEvents.setValue("Enemy destroyed");
                 System.out.println("Enemy Destroyed");
                 }
                 else if(!exploded)
                 {    
                     health--;
                     gm1942.gameEvents.setValue("Enemy destroyed");
                     System.out.println("Enemy Destroyed");
                     index++;

                 }
             }
             else if(gm1942.m.collision(fx, fy, fw, fh)&& isFire) 
            {
                
                isFire = false;
                gm1942.gameEvents.setValue("superExplosion");
                System.out.println("Super explosion");
            }
             
            else if(gm1942.m2.collision(fx, fy, fw, fh)&& isFire) 
            {
                
                isFire = false;
                gm1942.gameEvents.setValue("superExplosion2");
                System.out.println("Super explosion");
            } 
            
            
        }//end of update
 
        
        
        public void draw(Graphics g, ImageObserver obs) 
        {
            if(health > 0)
            {    
                if(index==1)
                    g.drawImage(img[index], x+10, y+45, obs);
                 else   
                   g.drawImage(img[index], x, y, obs);
                
                if (isFire)
                {
                   g.drawImage(fire, fx, fy, obs); 
                } 
            }    
            //after displaying all the explosion images in the exp[]
            //it stops drawing the boss
            else if(exploded && health <= 0)
            {   
                if(index <=4)
                {    
                   g.drawImage(img[index], x, y, obs);
                   index++;
                   gm1942.gameEvents.setValue("gameover");
                       
                }

            }    
         }
        
    
}
