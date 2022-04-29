package C20441826;

public class Viz4 extends VisualSetup
{

    //declare variables
    VisualSetup viz;
    float[] lerpedBuffer;
    float[] lerpedBufferY;
    float width, height;
    float smoothedAmplitude, smoothedAmplitudeY;

    //constructor for fourth visualization
    public Viz4(float width, float height, float lerpedBuffer[], VisualSetup viz)
    {
        this.height = height;
        this.width = width;
        this.lerpedBuffer = lerpedBuffer;
        this.lerpedBufferY = lerpedBuffer;
        this.viz = viz;
    }//end Viz4

    //render visualization
    public void render()
    {

        //declare local variables
        float average = 0;
        float averagey = 0;
        float sum = 0;
        float sumy = 0;
        float borderx = width * 0.2f;
        float bordery = height * 0.25f;

        //calculate sum and lerped buffer for x axis
        for(int i = (int)borderx; i < width-borderx ; i ++)
        {
            sum += abs(viz.ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], viz.ab.get(i), 0.2f);
        }//end for loop
        //calculate average and smoothed amplitude for x axis
        average = sum / (float) viz.ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.2f);

        //generate sum and lerped buffer for y axis
        for(int i = (int)bordery; i < height-bordery ; i ++)
        {
            sumy += abs(viz.ab.get(i));
            lerpedBufferY[i] = lerp(lerpedBufferY[i], viz.ab.get(i), 0.2f);
        }//end for loop
        //calculate average and smoothed amplitude for y axis
        averagey = sumy / (float) viz.ab.size();
        smoothedAmplitudeY = lerp(smoothedAmplitudeY, averagey, 0.2f);

        //calculate center point
        float centerx = width / 2;
        float centery = height / 2;

        //wave visualizer
        for(int i = (int)borderx; i < width-borderx; i++)
        {
            //visual colour
            float d = map(i, (int)borderx, width-borderx, 0, 255);
            viz.stroke(d,255,255);
            float f = lerpedBufferY[i] * bordery * 0.2f;
            //three different visualizer lines
            viz.line(i,centery+f,i,centery-f);
            viz.line(i,(centery - centery/3)+f,i,(centery - centery/3)-f);
            viz.line(i,(centery + centery/3)+f,i,(centery + centery/3)-f);
            viz.fill(0,0,20);
        }//end for loop

        //protect triangle visualisation
        for(int i = 16;i < 64;i+=5)
        {
            //cover back of triangles
            viz.strokeWeight((height + width) / 100);
            viz.stroke(0,0,20);
            viz.triangle((centerx-(height + width) / i)-(smoothedAmplitude * i * 4),(centery+(height + width) / i)+(smoothedAmplitude * i * 4),(centerx+(height + width) / i)+(smoothedAmplitude * i * 4),(centery+(height + width) / i)+(smoothedAmplitude * i * 4),centerx,(centery-(height + width) / i)-(smoothedAmplitude * i * 4));
        }//end for loop

        //triangle visualisation
        for(int i = 16;i < 64;i+=5)
        {
            viz.strokeWeight(1);
            viz.noFill();
            //stroke respond to the music to change colour
            viz.stroke((smoothedAmplitude * i * 32),255,255);
            //triangles expand to music
            viz.triangle((centerx-(height + width) / i)-(smoothedAmplitude * i * 4),(centery+(height + width) / i)+(smoothedAmplitude * i * 4),(centerx+(height + width) / i)+(smoothedAmplitude * i * 4),(centery+(height + width) / i)+(smoothedAmplitude * i * 4),centerx,(centery-(height + width) / i)-(smoothedAmplitude * i * 4));
        }//end for loop

    }//end render

}//end class
