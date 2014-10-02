
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

//Abstract class enemy its sub classes 
//will be the enemy planes which come from horizontal and vertical
//directions
public abstract class Enemy 
{

        Image[] img;
        int x, y, sizeX, sizeY, speed;
        Random gen;
        boolean show;
        public static gm1942 gm = new gm1942();
       
        
   
       Enemy(int speed, Random gen)
       {
            this.img = new Image[5];
            //img[0] will be implemented in subclass enemy depending
            //on what direction they are coming from
            this.img[1] = gm.getSprite("Resources/explosion1_1.png");
            this.img[2] = gm.getSprite("Resources/explosion1_2.png");
            this.img[3] = gm.getSprite("Resources/explosion1_3.png");
            this.img[4] = gm.getSprite("Resources/explosion1_4.png");
            
            
            
            this.speed = speed;
            this.gen = gen;
            this.show = true;
            
            //System.out.println("w:" + sizeX + " y:" + sizeY);
       }
       
       //Following methods will be implemented in subclasses of enemy

       public abstract void update(int w, int h);

       public abstract void draw(Graphics g, ImageObserver obs);

    }//end of Enemy class
        
        
       