package c123456;

import ie.tudublin.Visual;
//import ie.tudublin.VisualException;

public class BryansVisual extends Visual 
{

    public void settings()
    {
        size(500, 500, P3D);//min size for tv graphic is 500 x 500
        println("CWD: " + System.getProperty("user.dir"));//current working directory
        fullScreen(P3D,SPAN);// full screen
    }//end settings

    public void keyPressed()
    {
        if (key == ' ')//spacebar
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }//end if statement
    }//end keyPressed

    public void setup()
    {
        colorMode(RGB);
        noCursor();
        setFrameSize(256);
        startMinim();
        loadAudio("rapgod.mp3");
        getAudioPlayer().play();
        //startListening(); 
    }//end setup

    public void draw()
    {
        //background
        background(100,100,100);
        noStroke();
        //border calculation
        float borderx = width * 0.2f;
        float bordery = height * 0.25f;
        float frame = 30f;
        float detail = 5f;
        //details --> minimum size for tv details
        if(height >= 500 && width >= 500)
        {
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
            stroke(92, 145, 230);//light blue
            circle(borderx + (frame*(float)7.5),(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line in
            stroke(115, 199, 132);//light green
            circle(borderx + (frame*(float)7.5) + frame,(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line out
        }//end if statement
        //tv screen
        noStroke();
        fill(20);
        rect(borderx, bordery, width-(borderx*2), height-(bordery*2), detail*2);
    }//end draw

}//end NameVisual