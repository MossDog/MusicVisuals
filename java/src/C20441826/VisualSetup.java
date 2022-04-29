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
    VisualTV tv;
    float n4;
    
    //settings
    public void settings()
    {
        size(500,500, P3D);//min size for tv graphic is 500 x 500
        println("CWD: " + System.getProperty("user.dir"));//current working directory
        //fullScreen(P3D,SPAN);// full screen
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
        tv = new VisualTV(width, height, this);
    }//end setup

    //draw
    public void draw()
    {
        tv.render();
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
