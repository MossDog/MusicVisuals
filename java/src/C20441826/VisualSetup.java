package C20441826;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class VisualSetup extends Visual 
{

    //declare variables
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    int x, j;
    int mode = 1;
    float lerpedBuffer[];
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float borderx;
    float bordery;
    float halfH;
    float halfW;
    Viz1 viz1;
    Viz2 viz2;
    Viz3 viz3;
    Viz4 viz4;
    float n4;
    
    //settings
    public void settings()
    {
        size(1000,500, P3D);//min size for tv graphic is 500 x 500
        println("CWD: " + System.getProperty("user.dir"));//current working directory
        fullScreen(P3D,SPAN);// full screen
    }//end settings

    //check for key press
    public void keyPressed() 
    {
        //for choosing visualizations
		if (key >= '1' && key <= '6') 
        {
			mode = key - '0';
		}//end if statement
        //spacebar for pausing
		if (keyCode == ' ') 
        {
            //if playing
            if (ap.isPlaying()) 
            {
                //if paused
                ap.pause();
            }//end if statement
            //if paused
            else 
            {
                //restart the song and play
                ap.rewind();
                ap.play();
            }//end else statement
        }//end if statement
	}//end key pressed

    //setup
    public void setup()
    {
        minim = new Minim(this);
        colorMode(HSB);
        setFrameSize(256);
        startMinim();
        //startListening(); 
        ap = minim.loadFile("rapgod.mp3", height+width);
        //ap.setGain(-10); // set volume
        ap.play();
        ab = ap.mix;
        y = height / 2;
        smoothedY = y;
        lerpedBuffer = new float[width];
        //declare visualizations
        viz1 = new Viz1(width, height, lerpedBuffer, this);
        viz2 = new Viz2(width, height, this);
        viz3 = new Viz3(width, height, smoothedAmplitude, lerpedBuffer, this);
        viz4 = new Viz4(width, height, smoothedAmplitude, lerpedBuffer, this);
    }//end setup

    //draw
    public void draw()
    {
        //background
        background(100,0,100);
        noStroke();
        //border calculation
        borderx = width * 0.2f;
        bordery = height * 0.25f;
        float frame = 30f;
        float detail = 5f;
        halfH = height / 2;
        halfW = width/2;
        n4 = borderx/2;
        //details --> minimum size for tv details
        if(height >= 500 && width >= 500)
        {
            //desk
            fill(16, 100, 50);
            quad(width/24,height,width/8,height-height/3,width-width/8,height-height/3,width-width/24,height);
            //tv frame
            fill(150); 
            rect(borderx-frame, bordery-frame, width-(borderx*2)+(frame*2), height-(bordery*2)+(frame*4), detail*2);    
            //details
            fill(100);
            rect(borderx, (bordery + height-(bordery*2)) + detail, width-(borderx*2), detail, detail*2);//small thing indent
            strokeWeight(2);
            stroke(95);
            rect(borderx, (bordery + height-(bordery*2)) + detail*3, width-(borderx*2), frame*2, detail*2);//panel
            noStroke();
            //details --> input
            fill(60);
            rect(borderx + frame, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame*2, frame, detail);//power button
            rect(borderx + (frame*(float)2.5) + frame, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            rect(borderx + (frame*(float)2.5) + frame*2, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            strokeWeight(2);//proper stroke weight
            stroke(140, 140, 180);//light blue
            circle(borderx + (frame*(float)7.5),(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line in
            stroke(90, 140, 180);//light green
            circle(borderx + (frame*(float)7.5) + frame,(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line out
        }//end if statement
        //tv screen
        noStroke();
        fill(20);
        rect(borderx, bordery, width-(borderx*2), height-(bordery*2));
        //switching visualizations
        switch (mode) 
        {
            //first visualization
            case 1:
            {
                viz1.render();
                break;
            }//end case 1
            //second visualization
            case 2:
            {
                viz2.render();
                break;
            }//end case 2
            //third visualization
            case 3:
            {
                viz3.render();
                break;
            }//end case 3
            //fourth visualization
            case 4:
            {
                viz4.render();
                break;
            }//end case 4
            /*
            //fifth visualization
            case 5:
            {
                viz5.render();
            }//end case 5
            //final visualization
            case 6:
            {
                viz6.render();
            }//end case 
            */
        }//end switch
    }//end draw

    //control tv
    public void mouseClicked()
    {   
        //declare local variable
        float detail = 5f;

        //first button --> control play and pause
        if (mouseX >= borderx && mouseX <= width-borderx && mouseY >= (bordery + height-(bordery*2)) + detail*3 && mouseY <= ((bordery + height-(bordery*2)) + detail*3) + 60f)
        {
            if (ap.isPlaying())
            {
                ap.pause();
            }
            else
            {
                ap.play();
            }
        }//end if statement

        /*
        //second button --> previous visualization
        if()
        {

        }
        //third button --> next visualization
        if()
        {
            
        }
        */

    }//end mouseClicked

}//end NameVisual
