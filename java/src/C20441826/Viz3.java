package C20441826;
import ie.tudublin.Visual;

public class Viz3 extends Visual 
{
    
    //declare variables
    VisualSetup viz;
    float[] lerpedBuffer;
    float[] lerpedBufferY;
    float width, height, smoothedAmplitude, smoothedAmplitudeY;

    //constructor
    public Viz3(float width, float height, float smoothedAmplitude, float lerpedBuffer[], VisualSetup viz)
    {
        this.height = height;
        this.width = width;
        this.smoothedAmplitude = smoothedAmplitude;
        this.smoothedAmplitudeY = smoothedAmplitude;
        this.lerpedBuffer = lerpedBuffer;
        this.lerpedBufferY = lerpedBuffer;
        this.viz = viz;
    }//end Viz3

    //declare off
    float off = 0;

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
        off += 1;

        //calculate sum and lerped buffer for x axis
        for(int i = (int)borderx; i < width-borderx ; i ++)
        {
            sum += abs(viz.ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], viz.ab.get(i), 0.2f);
        }//end for loop
        //calculate average and smoothed amplitude for x axis
        average = sum / (float) viz.ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.2f);

        //calculate sum and lerped buffer for y axis
        for(int i = (int)bordery; i < height-bordery ; i ++)
        {
            sumy += abs(viz.ab.get(i));
            lerpedBufferY[i] = lerp(lerpedBufferY[i], viz.ab.get(i), 0.2f);
        }//end for loop
        //calculate average and smoothed amplitude for y axis
        averagey = sumy / (float) viz.ab.size();
        smoothedAmplitudeY = lerp(smoothedAmplitudeY, averagey, 0.2f);

        //the great eye
        viz.stroke(255, 0, 255);
        viz.strokeWeight(2);
        viz.noFill();
        viz.fill(255, 0, 255);
        viz.ellipse(width / 2, height / 2, ((height + width) / 20) + (smoothedAmplitude * 400), ((height + width) / 40) + (smoothedAmplitude * 400));
        viz.fill(smoothedAmplitude * (height + width),255,255);
        viz.ellipse(width / 2, height / 2, ((height + width) / 40) + (smoothedAmplitude * 400), ((height + width) / 40) + (smoothedAmplitude * 400));
        viz.fill(0, 0, 20);
        viz.strokeWeight(0);
        viz.ellipse(width / 2, height / 2, ((height + width) / 80) + (smoothedAmplitude * 400), ((height + width) / 40) + (smoothedAmplitude * 400));

        //outer square
        viz.strokeWeight(2);
        viz.noFill();
        viz.rectMode(CENTER);
        viz.square(width / 2, height / 2, (height + width) / 8);
        viz.rectMode(CORNER);

        //left and right square visualizer
        for(int i = (int)(height/2 - ((height + width) / 16)); i < (height) - (height/2) + ((height + width) / 16); i++)
        {
            float d = map(i, 0, height - borderx, 0, 255);
            viz.stroke(d, 255, 255);
            float f = lerpedBufferY[i] * borderx;
            viz.line(width / 2 - ((height + width) / 16), i, f/3 + width / 2 - ((height + width) / 16), i);
            viz.line(width / 2 + ((height + width) / 16), i, f/3 + width / 2 + ((height + width) / 16), i);
        }//end for loop

        //top and bottom square visualizer
        for(int i = (int)(width/2 - ((height + width) / 16)); i < width - width/2 + ((height + width) / 16); i++)
        {
            float d = map(i, 0, width - bordery, 0, 255);
            viz.stroke(d, 255, 255);
            float f = lerpedBuffer[i] * bordery;
            viz.line(i, height / 2 - ((height + width) / 16), i, f/3 + height / 2 - ((height + width) / 16));
            viz.line(i, height / 2 + ((height + width) / 16), i, f/3 + height / 2 + ((height + width) / 16));
        }//end for loop

        //sliders
        viz.stroke(255, 0, 255);
        viz.line(borderx+borderx/3,bordery+bordery/5,borderx+borderx/3,height-bordery-bordery/5);
        viz.line(width-borderx-borderx/3,bordery+bordery/5,width-borderx-borderx/3,height-bordery-bordery/5);
        viz.fill(0,0,255);
        viz.circle(borderx+borderx/3,bordery+bordery/5,(height + width) / 320);
        viz.circle(borderx+borderx/3,height-bordery-bordery/5,(height + width) / 320);
        viz.circle(width-borderx-borderx/3,bordery+bordery/5,(height + width) / 320);
        viz.circle(width-borderx-borderx/3,height-bordery-bordery/5,(height + width) / 320);

        //checking to make sure the square is on the slide
        if(bordery+bordery/5 <= (height-bordery-bordery/5) - (smoothedAmplitude * (height + width)))
        {
            viz.rectMode(CENTER);
            viz.square(borderx+borderx/3, (height-bordery-bordery/5) - (smoothedAmplitude * (height + width)), (height + width) / 80);
            viz.square(width-borderx-borderx/3, (height-bordery-bordery/5) - (smoothedAmplitude * (height + width)), (height + width) / 80);
        }//end if statement

        //prevent the squares flying off the slider --> max
        else
        {
            viz.rectMode(CENTER);
            viz.fill(0,0,255);
            viz.square(borderx+borderx/3, bordery+bordery/5, (height + width) / 80);
            viz.square(width-borderx-borderx/3, bordery+bordery/5, (height + width) / 80);
        }//end else statement

        //reset rectangle mode
        viz.rectMode(CORNER);

    }//end render
    
}//end class
