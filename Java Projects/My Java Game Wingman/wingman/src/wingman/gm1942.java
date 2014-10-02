
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.*;
import java.applet.*;

/**
 *
 * @author Ilmi
 */
public class gm1942 extends JApplet implements Runnable {

    private Thread thread; 
   
    
   
    private int x = 0, move = 0;
    //The BufferedImage subclass describes an 
    //Image with an accessible buffer of image data.
    private BufferedImage bimg;
    int speed = 2, life = 4;
    static int score;
    Random generator = new Random(1234567);
    Island I1, I2, I3;
    static Fire f;
    static MyPlane m;
    static MyPlane2 m2;
    Enemy e1, e2, e3, e4, e5, e6, e7,e8, e9;
    Boss boss;
    static GameEvents gameEvents;
    
    public AudioClip sound;
    
    Panel panel;

     boolean power;
     public  boolean gameOver;
    ImageObserver observer;
    
    scoreBoard board;

    public void init() {
        setBackground(Color.white);
        Image myPlane, island1, island2, island3, enemy, fire;
        
        island1 = getSprite("Resources/island1.png");
        island2 = getSprite("Resources/island2.png");
        island3 = getSprite("Resources/island3.png");
        myPlane = getSprite("Resources/myplane_1.png");

        
 
        gameOver = power = false;
        observer = this;

        I1 = new Island(island1, 100, 100, speed, generator);
        I2 = new Island(island2, 200, 400, speed, generator);
        I3 = new Island(island3, 300, 200, speed, generator);
        //Enemy is moving a little faster than
        //the speed of the background flow
        //Vertical Enemies
        e1 = new enemyY(speed+1, generator);
        e2 = new enemyY(speed+1, generator);
        e3 = new enemyY(speed+1, generator);
        e4 = new enemyY(speed+1, generator);
        
        //Horizontal Enemies
        e5 = new enemyX(speed+1, generator);
        e6 = new enemyX(speed+1, generator);
        e7 = new enemyX(speed+1, generator);
        e8 = new enemyX(speed+1, generator);
        
        e9 = new enemyZ(speed+1, generator);
        
        boss = new Boss(speed+1);
        
        score = 0;

        panel = new Panel();
        
        f = new Fire(speed+13, generator);
        
        KeyControl key = new KeyControl();
        addKeyListener(key);
        gameEvents = new GameEvents();
        m = new MyPlane(myPlane, 300, 360, 10);
        m2 = new MyPlane2(myPlane, 50, 360, 10);
        gameEvents.addObserver(m);
        gameEvents.addObserver(m2);
        
        //BACKGROUND MUSIC
        playSound("Resources/background.MID");
        board = new scoreBoard();


    }

    public class Island {

        Image img;
        int x, y, speed;
        Random gen;

        Island(Image img, int x, int y, int speed, Random gen) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.gen = gen;
        }
        
        //the y axis of the island is increased by speed value
        //when it reaches the height then it will set up -100
        //on y axis and its x axis set up to a random value
        public void update(int w, int h) {
            y += speed;
            if (y >= h) {
                y = -100;
                x = Math.abs(gen.nextInt() % (w - 30));
                
            }
        }

        //draw the Island with x and y coordinates
        // The observer parameter notifies the application
        //of updates to an image that is loaded asynchronously
        public void draw(Graphics g, ImageObserver obs) {
            g.drawImage(img, x, y, obs);
        }
    }//end of Island
    
   public class GameEvents extends Observable {
       //Observable means this class will be observed, it updates and its 
       //observers will be notified by calling its  notifyObservers() methods
       //notifyObservers() will call observers' update() methods
       int type;
       Object event;
       
       
   public void setValue(KeyEvent e) {
          type = 1; // let's assume this mean key input. Should use CONSTANT value for this
          event = e;
          setChanged();
         // trigger notification
 
         notifyObservers(this);  
   }

       public void setValue(String msg) {
          type = 2; // let's assume this mean key input. Should use CONSTANT value for this
          event = msg;
          setChanged();
         // trigger notification
         notifyObservers(this);  
        }
    }//end of GameEvents

    public class KeyControl extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            gameEvents.setValue(e);
           
        }
    }

    public class MyPlane implements Observer {
        Image img, exp[];
        int x, y, speed;
        int boom;
        int index, spare;
        boolean exploded, lastExploded;

        //boss class is very similar to myplane
        //similar actions and events
        //so boss class should be created as subclass of
        //myPlane
        //enemy class asks myplane if there is an collision
        //enemy updates obseravble
        
        //For Boss class
        //collision method simlar to MyPlane
        
        public MyPlane(Image img, int x, int y, int speed)
        {
            
            spare = 0;
            this.img = img;
            this.exp = new Image[5];
            this.exp[0] = getSprite("Resources/explosion2_1.png");
            this.exp[1] = getSprite("Resources/explosion2_2.png");
            this.exp[2] = getSprite("Resources/explosion2_3.png");
            this.exp[3] = getSprite("Resources/explosion2_5.png");
            this.exp[4] = getSprite("Resources/explosion2_7.png");
            
            this.x = x;
            this.y = y;
            this.speed = speed;
            boom = 0;
            exploded = false;
        }

        public void draw(Graphics g, ImageObserver obs) 
        {
            if(boom <= 4)
                g.drawImage(img, x, y, obs);
            //after displaying all the explosion images in the exp[]
            //it stops drawing the plane
            else if(exploded && index <4)
            {
                g.drawImage(exp[index], x, y, obs);
                index++;
                if(index>3&&!gameOver)
                {
                    boom = 0;
                    exploded = false;
                }    
            }    
                
                
                
        }
           
        
        //parameters are the coordinate specification of the object
        //our plane crashs to. this.y and this.x means the y and x
        //coordinates of our plane
        //the value 60 is the width and length of the plane
        public boolean collision(int x, int y, int w, int h) 
        {
            if(!exploded)
            {    
            if((this.x >= x)&&(this.x <= x+w)&&(this.y >= y)&&(this.y <= y+h))
                return true;
            else if((this.x+60 > x)&&(this.x < x)&&(this.y+60 > y)&&(this.y < y))
                return true;
            }
            return false;
           
        }
        
        //changes in the Game events(Observable) will invoke
        //this update method. Since this class is observer
        public void update(Observable obj, Object arg) {
            GameEvents ge = (GameEvents) arg;
 
          if(!gameOver)
          {    
            if(ge.type == 1)
            {
                KeyEvent e = (KeyEvent) ge.event;
                switch (e.getKeyCode()) {    
                    case KeyEvent.VK_LEFT:
                        System.out.println("Left");
                        x -= (speed+2);
	        	break; 
                    case KeyEvent.VK_RIGHT:
                        System.out.println("Right");
                        x += (speed+2);
			break;				
								
		    case KeyEvent.VK_UP:
		      System.out.println("Up");
			y -= (speed+2);
			break;
				
		   case KeyEvent.VK_DOWN:
			 System.out.println("Down");
	                y += (speed+2);
		        break;
                       
                   case KeyEvent.VK_SPACE:
                         System.out.println("Fire");
                         if(!exploded)
                             f.isFired = true;
                        
                         break;
                       
     
                }
            }
            else if(ge.type == 2)
            {
                String msg = (String)ge.event;
                if(msg.equals("Explosion"))
                    boom++;
                //Boss gives multi-damage!!!
                else if(msg.equals("superExplosion"))
                    boom+=2;
                  playSound("Resources/snd_explosion2.wav");
                  score++;
                  if(boom>4)
                   {    
                     exploded = true;
                     spare++;
                     playSound("Resources/snd_explosion2.wav");
                     if(spare>1)
                         gameOver = true;
                    } 
     
                    
            }
          }//if not game pover   
        }
    }//end of MyPlane
    
        public class MyPlane2 extends MyPlane implements Observer
        {
            int boom, spare;
           public MyPlane2(Image img, int x, int y, int speed)
           {
               super(img, x, y, speed);
               spare = 0;
               this.img = img;
               this.exp = new Image[5];
               this.exp[0] = getSprite("Resources/explosion2_1.png");
               this.exp[1] = getSprite("Resources/explosion2_2.png");
               this.exp[2] = getSprite("Resources/explosion2_3.png");
               this.exp[3] = getSprite("Resources/explosion2_5.png");
               this.exp[4] = getSprite("Resources/explosion2_7.png");
               exploded = false;
               this.boom = boom;
           }
           public void draw(Graphics g, ImageObserver obs) 
           {
            if(boom <= 4)
                g.drawImage(img, x, y, obs);
            else if(exploded && index <4)
            {
                g.drawImage(exp[index], x, y, obs);
                index++;
                if(index>3&&!gameOver)
                {
                    boom = 0;
                    exploded = false;
                }    
            }            
                
           }
           //changes in the Game events(Observable) will invoke
        //this update method. Since this class is observer
        public void update(Observable obj, Object arg) {
            GameEvents ge = (GameEvents) arg;
            if(!gameOver)
            {
                
             
              if(ge.type == 1)
              {
                KeyEvent e = (KeyEvent) ge.event;
                switch (e.getKeyCode()) {    
                    case KeyEvent.VK_A:
                        System.out.println("Left");
                        x -= (speed+2);
	        	break; 
                    case KeyEvent.VK_D:
                        System.out.println("Right");
                        x += (speed+2);
			break;				
								
		    case KeyEvent.VK_W:
		      System.out.println("Up");
			y -= (speed+2);
			break;
				
		   case KeyEvent.VK_S:
			 System.out.println("Down");
	                y += (speed+2);
		        break;
                       
                   case KeyEvent.VK_Q:
                         System.out.println("Fire");
                         if(!exploded)
                             f.isFired2 = true;
                        
                         break;
                       
     
                }
             }
             else if(ge.type == 2)
             {
                String msg = (String)ge.event;
                if(msg.equals("Explosion2"))
                    boom++;
                //Boss gives multi-damage!!!
                else if(msg.equals("superExplosion2"))
                    boom+=2;
                    playSound("Resources/snd_explosion2.wav");
                    score++;
                    if(boom>4)
                    {    
                     exploded = true;
                     spare++;
                     playSound("Resources/snd_explosion2.wav");
                     if(spare>1)
                     {    
                         gameOver = true;
                         m.exploded = true;
                     }    
                    }
     
                    
            }
           }    
        }
                        
        }//end of MyPlane2 

    
    

    
    
public class Fire
{
   Image img, img2, bulletLeft,bulletRight, powerup;
   int x, y,x2, y2, speed, sizeX, sizeY, powerH, powerW, powerX, powerY;
   
   int Lx, Ly, Rx, Ry;//coordinates of diognal bullets
   
   int Lx2, Ly2, Rx2, Ry2;
   Image bulletLeft2, bulletRight2;
   boolean isFired,isFired2,isExist2, isExist, isPassed;
   Random gen;

public Fire(int speed, Random gen)
{
   this.img = getSprite("Resources/bullet.png");
   this.img2 = getSprite("Resources/bullet.png");
   this.bulletLeft = getSprite("Resources/bulletLeft.png");
   this.bulletRight = getSprite("Resources/bulletRight.png");
   bulletLeft2 =bulletLeft;
   bulletRight2 = bulletRight;
   this.powerup = getSprite("Resources/powerup.png");
   powerW = powerup.getWidth(null);
   powerH = powerup.getHeight(null);
   this.x = this.y = 50;
   this.gen = gen;
   this.isFired = this.isExist = this.isFired2= false;
   this.speed = speed;
   sizeX = img.getWidth(null);
   sizeY = img.getHeight(null);
   isPassed = false;
}

public void update(int w, int h)
{
   
   if(!isFired)
   {
   this.x = m.x+18;//gets the coordinates of the plane
   this.y = m.y;
   //Initial coordinates of the diognal bullets
   this.Lx = m.x+10;
   this.Ly = m.y;
   this.Rx = m.x+26;
   this.Ry = m.y;
   }
   if(!isFired2)
   {
   this.x2 = m2.x+18;//gets the coordinates of the plane
   this.y2 = m2.y;
   //Initial coordinates of the diognal bullets
   this.Lx2 = m2.x+10;
   this.Ly2 = m2.y;
   this.Rx2 = m2.x+26;
   this.Ry2 = m2.y;

   }
   if(y<0)
   {
     isFired = false;

   }
   if(y2<0)
   {
     isFired2 = false;

   }
   //Managing update of bullet coordinates
   if(isFired)
   {    
   y -= speed;
   Lx -= speed;
   Ly -= speed;
   Rx += speed;
   Ry -= speed;   
   }
   if(isFired2)
   { 
   Lx2 -= speed;
   Ly2 -= speed;
   Rx2 += speed;
   Ry2 -= speed;    
   y2 -= speed; 
   }
   //If the planes scores 10
   //the power up ican will be displayed at the top
   //of the screen and move down. It will be displayed
   //only once.
   if(score==2)
   {
       isExist = true;
       score++;
       powerX = 200;
       powerY = 0;
   }
   else if(isExist)
   {
       powerY += (speed-13);
       // if planes touches the power up icon
       if((m.collision(powerX, powerY, powerW, powerH))||(m2.collision(powerX, powerY, powerW, powerH)))
       {
           power = true;
           isExist = false;
       }
       if(powerY==550)
           isExist = false;
   }    
 } 
 

   public boolean collision(int x, int y, int w, int h) 
   {
       //collision of the diognal fires
       //In order to satisfy this cpondition,
       //the planes must get the Powerup icon first
       if((isFired||isFired2)&&power)
       {
           if((this.Lx >= x-10)&&(this.Lx <= x+w+10)&&(this.Ly >= y-10)&&(this.Ly <= y+h))
                return true;
           if((this.Rx >= x-10)&&(this.Rx <= x+w+10)&&(this.Ry >= y-10)&&(this.Ry <= y+h))
                return true;
           if((this.Lx2 >= x-10)&&(this.Lx2 <= x+w+10)&&(this.Ly2 >= y-10)&&(this.Ly2 <= y+h))
                return true;
           if((this.Rx2 >= x-10)&&(this.Rx2 <= x+w+10)&&(this.Ry2 >= y-10)&&(this.Ry2 <= y+h))
                return true;
            else if((this.sizeX > x)&&(this.x < x)&&(this.sizeY > y)&&(this.y < y))
                return true;
            else if((this.sizeX > x)&&(this.x2 < x)&&(this.sizeY > y)&&(this.y2 < y))
                return true;
       }    
      if(isFired||isFired2)
       {        
            if((this.x >= x-10)&&(this.x <= x+w+10)&&(this.y >= y-10)&&(this.y <= y+h))
                return true;
            if((this.x2 >= x-10)&&(this.x2 <= x+w+10)&&(this.y2 >= y-10)&&(this.y2 <= y+h))
                return true;
            else if((this.sizeX > x)&&(this.x < x)&&(this.sizeY > y)&&(this.y < y))
                return true;
            if((this.sizeX > x)&&(this.x2 < x)&&(this.sizeY > y)&&(this.y2 < y))
                return true;
            else
            return false;
       }
       return false;
       
    }
   public void draw(Graphics g, ImageObserver obs) 
   {
            if(isExist)
                g.drawImage(powerup, powerX, powerY, obs);
            if (isFired) 
            {
                g.drawImage(img, x, y, obs);
                if(power)
                {
                    g.drawImage(bulletLeft, Lx, Ly, obs);
                    g.drawImage(bulletRight, Rx, Ry, obs);
                    
                    
                }    
            }
            
            if(isFired2)
            {
               g.drawImage(img2, x2, y2, obs); 
               if(power)
               {   
                   g.drawImage(bulletLeft2, Lx2, Ly2, obs);
                    g.drawImage(bulletRight2, Rx2, Ry2, obs);
                }   
            }   
   } 
}//end of Fire
    
    public  Image getSprite(String name) {
         URL url = gm1942.class.getResource(name);
        Image img = getToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
        }
        return img;
    }

    // generates a new color with the specified hue
    public void drawBackGroundWithTileImage(int w, int h, Graphics2D g2) {
        Image sea;
        sea = getSprite("Resources/water.png");
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        Image Buffer = createImage(NumberX * TileWidth, NumberY * TileHeight);
        //Graphics BufferG = Buffer.getGraphics();


        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(sea, j * TileWidth, i * TileHeight + (move % TileHeight), TileWidth, TileHeight, this);
            }
        }
        move += speed;
    }
    


    public void drawDemo(int w, int h, Graphics2D g2) {

        drawBackGroundWithTileImage(w, h, g2);
            I1.update(w, h);
            I1.draw(g2, this);

            I2.update(w, h);
            I2.draw(g2, this);

            I3.update(w, h);
            I3.draw(g2, this);
        if (!gameOver )
        {
            
            m.draw(g2, this);
            m2.draw(g2, this);
            if(score<=35)
            {    
                
             e1.update(w, h);
             e1.draw(g2, this);
            
             e2.update(w, h);
             e2.draw(g2, this);
            
             e3.update(w, h);
             e3.draw(g2, this);
            
             e4.update(w, h);
             e4.draw(g2, this);
            
             e5.update(w, h);
             e5.draw(g2, this);
            
             e6.update(w, h);
             e6.draw(g2, this);
            
             e7.update(w, h);
             e7.draw(g2, this);
            
             e8.update(w, h);
             e8.draw(g2, this);
            
             e9.update(w, h);
             e9.draw(g2, this);
            }
            
            else if(score>15)
            {    
                boss.update();
                boss.draw(g2, this);
            } 
            
            
             f.update(w, h);
             f.draw(g2, this);
             panel.draw(g2, this);
             panel.update(w, h);
        }     

        else
            board.draw(g2, this);
            
            
        //}
  
    }//end of drawDemo

    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);

        //Following setFocusable enables to control the plane
        //with the keys. Otherwise, keys dont work!!!
        setFocusable(true);
        return g2;
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        Graphics2D g2 = createGraphics2D(d.width, d.height);
        drawDemo(d.width, d.height, g2);
        g2.dispose();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void run() {
    	
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
          
          try {
                //FRAME PER SECOND: 25
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
            
        }
    	    	
       // thread = null;
    }

    //plays the sound of a specific sound file
    //filename shows the directory of the method
    //and takes it as a parameter
    private void playSound(String filename)
    {
        
        URL directory = gm1942.class.getResource(filename);
        sound = Applet.newAudioClip(directory);
        sound.play();
      
    }

    public static void main(String argv[]) {
        final gm1942 demo = new gm1942();
        demo.init();
        JFrame f = new JFrame("Scrolling Shooter");
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}