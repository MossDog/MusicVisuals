package C20441826;

//import libraries
import processing.core.PVector;

public class Viz3 extends VisualSetup
{
    
    //declare variables
    VisualSetup viz;
    float[] lerpedBuffer;
    float[] lerpedBufferY;
    float width, height; 
    float smoothedAmplitude, smoothedAmplitudeY;
    private PVector border, border2, center;

    //constructor for third visualization
    public Viz3(float width, float height, float lerpedBuffer[], PVector border, PVector border2, PVector center, VisualSetup viz)
    {
        this.height = height;
        this.width = width;
        this.lerpedBuffer = lerpedBuffer;
        this.lerpedBufferY = lerpedBuffer;
        this.viz = viz;
        this.border = border;
        this.border2 = border2;
        this.center = center;
    }//end Viz3

    //render visualization
    public void render()
    {

        //declare local variables
        float average = 0;
        float averagey = 0;
        float sum = 0;
        float sumy = 0;

        //calculate sum and lerped buffer for tv x axis
        for(int i = (int)border.x; i < border2.x; i++)
        {
            sum += abs(viz.ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], viz.ab.get(i), 0.2f);
        }//end for loop
        //calculate average and smoothed amplitude for tv x axis
        average = sum / (float) viz.ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.2f);

        //calculate sum and lerped buffer for tv y axis
        for(int i = (int)border.y; i < border2.y; i++)
        {
            sumy += abs(viz.ab.get(i));
            lerpedBufferY[i] = lerp(lerpedBufferY[i], viz.ab.get(i), 0.2f);
        }//end for loop
        //calculate average and smoothed amplitude for tv y axis
        averagey = sumy / (float) viz.ab.size();
        smoothedAmplitudeY = lerp(smoothedAmplitudeY, averagey, 0.2f);

        //the great eye
        viz.stroke(255, 0, 255);
        viz.strokeWeight(2);
        viz.noFill();
        //sclera
        viz.fill(255, 0, 255);
        viz.ellipse(center.x, center.y, ((height + width) / 25) + (smoothedAmplitude * 400), ((height + width) / 50) + (smoothedAmplitude * 400));
        //iris
        viz.fill(smoothedAmplitude * (height + width),255,255);
        viz.ellipse(center.x, center.y, ((height + width) / 50) + (smoothedAmplitude * 400), ((height + width) / 50) + (smoothedAmplitude * 400));
        //pupil
        viz.fill(0, 0, 20);
        viz.strokeWeight(0);
        viz.ellipse(center.x, center.y, ((height + width) / 100) + (smoothedAmplitude * 400), ((height + width) / 50) + (smoothedAmplitude * 400));

        //outer square
        viz.strokeWeight(2);
        viz.noFill();
        viz.rectMode(CENTER);
        viz.square(center.x, center.y, (height + width) / 10);
        viz.rectMode(CORNER);

        //left and right square visualizer
        for(int i = (int)(height/2 - ((height + width) / 20)); i < (height) - (height/2) + ((height + width) / 20); i++)
        {
            //map colours
            float d = map(i, (int)(height/2 - ((height + width) / 20)), (height) - (height/2) + ((height + width) / 20), 0, 255);
            viz.stroke(d, 255, 255);
            //line visualizers --> slightly tamed
            float f = lerpedBufferY[i] * border.x * (float)0.9;
            viz.line(center.x - ((height + width) / 20), i, f/3 + center.x - ((height + width) / 20), i);
            viz.line(center.x + ((height + width) / 20), i, f/3 + center.x + ((height + width) / 20), i);
        }//end for loop

        //top and bottom square visualizer
        for(int i = (int)(width/2 - ((height + width) / 20)); i < width - width/2 + ((height + width) / 20); i++)
        {
            //map colours
            float d = map(i, (int)(width/2 - ((height + width) / 20)), width - width/2 + ((height + width) / 20), 0, 255);
            viz.stroke(d, 255, 255);
            //line visualizers --> slightly tamed
            float f = lerpedBuffer[i] * border.y * (float)0.9;
            viz.line(i, center.y - ((height + width) / 20), i, f/3 + center.y - ((height + width) / 20));
            viz.line(i, center.y + ((height + width) / 20), i, f/3 + center.y + ((height + width) / 20));
        }//end for loop

        //sliders
        //two white lines for slider
        viz.stroke(255, 0, 255);
        viz.line(border.x+border.x/3,border.y+border.y/5,border.x+border.x/3,border2.y-border.y/5);
        viz.line(border2.x-border.x/3,border.y+border.y/5,border2.x-border.x/3,border2.y-border.y/5);
        //four circle ends on sliders
        viz.fill(0,0,255);
        viz.circle(border.x+border.x/3,border.y+border.y/5,(height + width) / 320);
        viz.circle(border.x+border.x/3,border2.y-border.y/5,(height + width) / 320);
        viz.circle(border2.x-border.x/3,border.y+border.y/5,(height + width) / 320);
        viz.circle(border2.x-border.x/3,border2.y-border.y/5,(height + width) / 320);
        //squares running up line slider
        //checking to make sure the square is on the slide
        if(border.y+border.y/5 <= (border2.y-border.y/5) - ((smoothedAmplitudeY * (height + width))*2))
        {
            viz.rectMode(CENTER);
            viz.square(border.x+border.x/3, (border2.y-border.y/5) - (smoothedAmplitudeY * (height + width))*2, (height + width) / 80);
            viz.square(border2.x-border.x/3, (border2.y-border.y/5) - (smoothedAmplitudeY * (height + width))*2, (height + width) / 80);
        }//end if statement
        //prevent the squares flying off the slider --> max
        else
        {
            viz.rectMode(CENTER);
            viz.fill(0,0,255);
            //set square max height
            viz.square(border.x+border.x/3, border.y+border.y/5, (height + width) / 80);
            viz.square(border2.x-border.x/3, border.y+border.y/5, (height + width) / 80);
        }//end else statement

        //reset rectangle mode
        viz.rectMode(CORNER);

    }//end render
    
}//end class
