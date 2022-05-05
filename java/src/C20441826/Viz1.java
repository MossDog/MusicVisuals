package C20441826;

//import libraries
import processing.core.PVector;

public class Viz1 extends VisualSetup
{ //start Viz1 class
    
    //declare variables
    private float x, y;
    private VisualSetup viz;
    private float[] lerpedBufferX;
    private float[] lerpedBufferY;
    private float width;
    private PVector border, border2, center;


    //constructor for first visualizer
    public Viz1(float width, float lerpedBuffer[], PVector border, PVector border2, PVector center, VisualSetup viz)
    { //start Viz1
        this.width = width;
        this.viz = viz;
        this.lerpedBufferX = lerpedBuffer;
        this.lerpedBufferY = lerpedBuffer;
        this.border = border;
        this.border2 = border2;
        this.center = center;
    } //end Viz1

    // render method called from draw
    public void render()
    {
        
        //vizualizers at bottom and top of the screen including sporadic circles
        for (int i = (int)border.x ; i < border2.x ; i ++)
        { //start for loop

            //colour vizualizers
            float c = map(i, 0, border2.x, 0, 255);
            //colour used for stroke of the line line vizualizers
            viz.stroke(c, 255, 255);

            // calculate lerped buffer for the visualizers going across the x axis of the screen
            lerpedBufferX[i] = lerp(lerpedBufferX[i], viz.ab.get(i), 0.2f);
            //calculate frequency to be relative to the range from the top or bottom of the border to the screen
            float f = lerpedBufferX[i] * border.y;

            //bottom visualizer, if statement filters out bottom of the bottom line vizualizer
            if ((center.y/2) + f + (border.y*2) < (center.y/2) - f + (border.y*2))
            {//start if statement
                viz.line(i, (center.y/2) + (f/2) + (border.y*2), i, (center.y/2) + (border.y*2));  
            }//end if statement

            //top visualizer, if statement filters out top of the line top vizualizer
            if ((center.y/2) + f > (center.y/2) - f)
            {//start if statement
                viz.line(i, (center.y/2) + (f/2), i, (center.y/2));
            }//end if statement

            // calculate x and y to spread of ellipses
            x = sin(i)*5;
            y = sin(i)*width-(border.x*2);

            //filter out remaining circles outside of tv
            if ( i + x > border.x + 20 && i+x < border2.x - 20 && i+y > border.y + 20 && i+y < border2.y - 20)
            {//start if statement
                viz.ellipse(i + x, i + y, f/3, f/3);
            }//end if statement  

        }//end for loop

        // iterate through the width of the screen - 20 on both sides so visualizer doesnt merge with side visualizers
        for(int i = (int)border.x; i < width-(border.x*2); i ++)
        {//start for loop

            // colour for main vizualizer
            float c = map(i, 0, border2.x, 255, 0);
            viz.stroke(c, 255, 255);
            //calculate frequency to be relative to the range from the bottom or top of the border to the screen
            float f = lerpedBufferX[i] * border.y;
            // main vizualizer in the center
            viz.line(i+10, (center.y) + (f), width-10- (i), (center.y)-(f));

        }//end for loop

        // iterate through the length of the screen, vizualizers on left and right hand side of the screen
        for(int i = (int)border.y; i < border2.y; i++)
        { //start for loop

            // colour for left and right vizualizers
            float c = map(i, 0, border2.y, 0, 255);
            viz.stroke(c, 255, 255);

            //calculate lerped buffer for the visualizers going down the y axis of the screen
            lerpedBufferY[i] = lerp(lerpedBufferY[i], viz.ab.get(i), 0.2f);
            //calculate frequency to be relative to the range from the left or right of the border to the screen
            float f = lerpedBufferY[i] * border.x;

            //left visualizer, if statement filters out left side of the left line vizualizer
            if (f + (border.x*4) > border.x*4)
            {
                viz.line(border.x, i, f/2 + border.x, i);
            }//end if statement

            //right visualizer, if statement filters out right side of the right line vizualizer
            if (f + (border.x*4) < border.x*4)
            {
                viz.line(border.x*4, i, f/2 + (border.x*4), i);
            }//end if statement

        }//end for loop

    }//end render
    
}//end class
