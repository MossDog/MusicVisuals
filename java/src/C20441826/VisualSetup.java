package C20441826;

import ie.tudublin.Visual;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class VisualSetup extends Visual 
{

    //declare variables
    //main
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    //audio response
    float lerpedBuffer[];
    //border calculation
    float borderx = width * 0.2f;
    float bordery = height * 0.25f;
    float frame = 30f;
    float detail = 5f;
    float halfH = height / 2;
    float halfW = width/2;
    //declare visuals
    Viz1 viz1;
    Viz2 viz2;
    Viz3 viz3;
    Viz4 viz4;
    VisualTV tv;
    //visual mode
    int mode = 1;
    
    //settings
    public void settings()
    {
        size(500,500, P3D);//min size for tv graphic is 500 x 500
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

        //color mode
        colorMode(HSB);
        //begin visualization
        minim = new Minim(this);
        ap = minim.loadFile("rapgod.mp3", height+width);
        ap.play();
        ab = ap.mix;
        lerpedBuffer = new float[width];
        //declare visualizations
        viz1 = new Viz1(width, height, lerpedBuffer, this);
        viz2 = new Viz2(width, height, this);
        viz3 = new Viz3(width, height, lerpedBuffer, this);
        viz4 = new Viz4(width, height, lerpedBuffer, this);
        tv = new VisualTV(width, height, this);

    }//end setup

    //draw
    public void draw()
    {

        //jeff
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
                break;
            }//end case 5
            //final visualization
            case 6:
            {
                viz6.render();
                break;
            }//end case 
            */

        }//end switch

    }//end draw

    //mouse clicked
    public void mouseClicked()
    {   

        //variables need to be in here
        float borderx = width * 0.2f;
        float bordery = height * 0.25f;
        float frame = 30f;
        float detail = 5f;

        //first button --> control play and pause
        if (mouseX >= borderx + frame && mouseX <= borderx + frame + (frame*2) && mouseY >= ((bordery + height-(bordery*2)) + (detail*3) + (frame / 2)) && mouseY <= ((bordery + height-(bordery*2)) + (detail*3) + (frame / 2)) + frame)
        {

            //if playing
            if (ap.isPlaying())
            {
                ap.pause();
            }//end if statement
            //if not playing
            else
            {
                ap.play();
            }//end else statement

        }//end if statement

        //second button --> previous visualization
        if(mouseX >= borderx + (frame*(float)2.5) + frame && mouseX <= borderx + (frame*(float)2.5) + frame + (frame - (frame/3))&& mouseY >= (bordery + height-(bordery*2)) + (detail*3) + (frame / 2) && mouseY <= (bordery + height-(bordery*2)) + (detail*3) + (frame / 2) + frame)
        {

            //first visualization
            if(mode == 1)
            {
                mode = 6;
            }//end if statement
            //second visualization
            else if(mode == 2)
            {
                mode = 1;
            }//end else if statement
            //third visualization
            else if(mode == 3)
            {
                mode = 2;
            }//end else if statement
            //fourth visualization
            else if(mode == 4)
            {
                mode = 3;
            }//end else if statement
            //fifth visualization
            else if(mode == 5)
            {
                mode = 4;
            }//end else if statement
            //sixth visualization
            else if(mode == 6)
            {
                mode = 5;
            }//end else if statement

        }//end if statement

        //third button --> next visualization
        if(mouseX >= borderx + (frame*(float)2.5) + (frame*2) && mouseX <= borderx + (frame*(float)2.5) + (frame*2) + (frame - (frame/3))&& mouseY >= (bordery + height-(bordery*2)) + (detail*3) + (frame / 2) && mouseY <= (bordery + height-(bordery*2)) + (detail*3) + (frame / 2) + frame)
        {

            //first visualization
            if(mode == 1)
            {
                mode = 2;
            }//end if statement
            //second visualization
            else if(mode == 2)
            {
                mode = 3;
            }//end else if statement
            //third visualization
            else if(mode == 3)
            {
                mode = 4;
            }//end else if statement
            //fourth visualization
            else if(mode == 4)
            {
                mode = 5;
            }//end else if statement
            //fifth visualization
            else if(mode == 5)
            {
                mode = 6;
            }//end else if statement
            //sixth visualization
            else if(mode == 6)
            {
                mode = 1;
            }//end  else if statement

        }//end if statement

    }//end mouseClicked

}//end NameVisual
