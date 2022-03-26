package c123456;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class BryansVisual extends Visual 
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void settings()
    {
        size(500, 500, P3D);//min size for tv graphic is 500 x 500
        println("CWD: " + System.getProperty("user.dir"));//current working directory
        //fullScreen(P3D,SPAN);// full screen
    }//end settings

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void setup()
    {
        minim = new Minim(this);
        colorMode(RGB);
        noCursor();
        setFrameSize(256);
        startMinim();
        //startListening(); 
        ap = minim.loadFile("rapgod.mp3", 500);
        ap.play();
        ab = ap.mix;
        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
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
        float halfH = height / 2;
        float halfW = width/2;
        float average = 0;
        float sum = 0;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.2f);
        }
        average= sum / (float) ab.size();
        //details --> minimum size for tv details
        if(height >= 500 && width >= 500)
        {
            //desk
            fill(59, 32, 18);
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
            stroke(92, 145, 230);//light blue
            circle(borderx + (frame*(float)7.5),(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line in
            stroke(115, 199, 132);//light green
            circle(borderx + (frame*(float)7.5) + frame,(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line out
        }//end if statement
        //tv screen
        noStroke();
        fill(20);
        rect(borderx, bordery, width-(borderx*2), height-(bordery*2), detail*2);
        switch (mode) {
            case 0:
            {
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * bordery;
                    line(i/2 + (width/4), (halfH/2) + f + (height/4), (halfW/2) - f + (width/4), i/2 + (height/4));  
                      
                       
     
                }
                break;
            }
    }//end draw
}
}//end NameVisual