
package wingman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

//displays the life bar and the number of
//enemies destroyed
public class Panel 
{
    public static gm1942 gm = new gm1942();
    Image[] img, img2;
    int score, w, h, barX,barX2,barY2, barY, index, index2;
    public Panel()
    {
        img = new Image[6];
        img[0] = gm.getSprite("Resources/health.png");
        img[1] = gm.getSprite("Resources/health1.png");
        img[2] = gm.getSprite("Resources/health2.png");
        img[3] = gm.getSprite("Resources/health3.png");
        img[4] = gm.getSprite("Resources/health3.png");
        img[5] = null;
        
        img2 = new Image[6];
        img2[0] = gm.getSprite("Resources/health.png");
        img2[1] = gm.getSprite("Resources/health1.png");
        img2[2] = gm.getSprite("Resources/health2.png");
        img2[3] = gm.getSprite("Resources/health3.png");
        img2[4] = gm.getSprite("Resources/health3.png");
        img2[5] = null;
        
        
        barX = 480;
        barY = 430;
        
        barX2 = 50;
        barY2 = 430;
        
    }
    
    public void update(int w, int h) 
    {
        if(gm1942.m.boom==0)
            index=0;
        else if (gm1942.m.boom==1)
            index=1;
        else if (gm1942.m.boom==2)
            index=2;
        else if (gm1942.m.boom==3 ||gm1942.m.boom==4)
            index=3;
        else
            index = 5;
        
        if(gm1942.m2.boom==0)
            index2=0;
        else if (gm1942.m2.boom==1)
            index2=1;
        else if (gm1942.m2.boom==2)
            index2=2;
        else if (gm1942.m2.boom==3 ||gm1942.m2.boom==4)
            index2=3;
        else
            index2 = 5;
        
        
    }
    
    public void draw(Graphics g, ImageObserver obs) 
    {
       
        g.drawString("SCORE: "+ gm1942.score, 250, 440);
        g.drawImage(img[index], barX, barY, obs);
        g.drawImage(img2[index2], barX2, barY2, obs);
        
    }        
    
}
