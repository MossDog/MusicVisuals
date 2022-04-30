package C20441826;

import processing.core.PVector;

public class VisualTV extends VisualSetup
{

    //visual settings
    VisualSetup tv;
    float width;
    float height;
    PVector border;
    PVector border2;

    //visual tv constructor
    public VisualTV(float width, float height, VisualSetup tv)
    {
        this.height = height;
        this.width = width;
        this.tv = tv;
    }//end constructor

    //render
    public void render()
    {

        //border calculation
        border = new PVector(width * 0.2f, height * 0.25f);
        border2 = new PVector(width-(border.x*2), height-(border.y*2));
        //background
        tv.background(100,0,100);
        tv.noStroke();  
          
        //details --> minimum size for tv details
        if(height >= 500 && width >= 500)
        {

            //desk
            tv.fill(16, 100, 50);
            tv.quad(width/24,height,width/8,height-height/3,width-width/8,height-height/3,width-width/24,height);
            //tv frame
            tv.fill(150); 
            tv.rect(border.x-frame, border.y-frame, border2.x+(frame*2), border2.y+(frame*4), detail*2);    
            //details
            tv.fill(100);
            tv.rect(border.x, (border.y + border2.y) + detail, border2.x, detail, detail*2);//small thing indent
            tv.strokeWeight(2);
            tv.stroke(95);
            tv.rect(border.x, (border.y + border2.y) + detail*3, border2.x, frame*2, detail*2);//panel
            tv.noStroke();
            //details --> input
            tv.fill(60);
            tv.rect(border.x + frame, (border.y + border2.y) + (detail*3) + (frame / 2), frame*2, frame, detail);//power button
            tv.rect(border.x + (frame*(float)2.5) + frame, (border.y + border2.y) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            tv.rect(border.x + (frame*(float)2.5) + frame*2, (border.y + border2.y) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            tv.strokeWeight(2);//proper stroke weight
            tv.stroke(140, 140, 180);//light blue
            tv.circle(border.x + (frame*(float)7.5),(border.y + border2.y) + (detail*3) + (frame),frame/2);//line in
            tv.stroke(90, 140, 180);//light green
            tv.circle(border.x + (frame*(float)7.5) + frame,(border.y + border2.y) + (detail*3) + (frame),frame/2);//line out
        
        }//end if statement

        //tv screen
        tv.noStroke();
        tv.fill(20);
        tv.rect(border.x, border.y, border2.x, border2.y);

    }//end render

}//end class
