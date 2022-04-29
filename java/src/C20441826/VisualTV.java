package C20441826;

public class VisualTV extends VisualSetup
{
    VisualSetup tv;
    float width;
    float height;
    float frame = 30f;
    float detail = 5f;
    float halfH = height / 2;
    float halfW = width/2;
    
    public VisualTV(float width, float height, VisualSetup tv)
    {
        this.height = height;
        this.width = width;
        this.tv = tv;
    }

    public void render()
    {
        float borderx = width * 0.2f;
        float bordery = height * 0.25f;
        float n4 = borderx/2;
        tv.background(100,0,100);
        tv.noStroke();
        //border calculation
        
        //details --> minimum size for tv details
        if(height >= 500 && width >= 500)
        {
            //desk
            tv.fill(16, 100, 50);
            tv.quad(width/24,height,width/8,height-height/3,width-width/8,height-height/3,width-width/24,height);
            //tv frame
            tv.fill(150); 
            tv.rect(borderx-frame, bordery-frame, width-(borderx*2)+(frame*2), height-(bordery*2)+(frame*4), detail*2);    
            //details
            tv.fill(100);
            tv.rect(borderx, (bordery + height-(bordery*2)) + detail, width-(borderx*2), detail, detail*2);//small thing indent
            tv.strokeWeight(2);
            tv.stroke(95);
            tv.rect(borderx, (bordery + height-(bordery*2)) + detail*3, width-(borderx*2), frame*2, detail*2);//panel
            tv.noStroke();
            //details --> input
            tv.fill(60);
            tv.rect(borderx + frame, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame*2, frame, detail);//power button
            tv.rect(borderx + (frame*(float)2.5) + frame, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            tv.rect(borderx + (frame*(float)2.5) + frame*2, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            tv.strokeWeight(2);//proper stroke weight
            tv.stroke(140, 140, 180);//light blue
            tv.circle(borderx + (frame*(float)7.5),(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line in
            tv.stroke(90, 140, 180);//light green
            tv.circle(borderx + (frame*(float)7.5) + frame,(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line out
        }//end if statement
        //tv screen
        tv.noStroke();
        tv.fill(20);
        tv.rect(borderx, bordery, width-(borderx*2), height-(bordery*2));
    }


    
}
