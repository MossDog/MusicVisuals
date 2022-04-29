package C20441826;
import ie.tudublin.Visual;
import processing.core.PApplet;

public class Viz1 
{
    
    float x, y;
    VisualSetup viz;
    float[] lerpedBufferX;
    float[] lerpedBufferY;
    float width, height;

    //constructor for first visualizer
    public Viz1(float width, float height, float lerpedBuffer[], VisualSetup viz)
    {
        this.height = height;
        this.width = width;
        this.viz = viz;
        this.lerpedBufferX = lerpedBuffer;
        this.lerpedBufferY = lerpedBuffer;
    }

    // render class called from draw
    public void render()
    {
        // Calculate distance from sides of tv
        float borderx = width * 0.2f;
        float bordery = height * 0.25f;
        
        // Calculate half of height
        float halfH = height/2;

        // calculate lerped buffer for the visualizers going across the y axis of the screen
        for(int i = (int)bordery ; i < height-bordery ; i++)
        {
            lerpedBufferY[i] = PApplet.lerp(lerpedBufferY[i], viz.ab.get(i), 0.2f);
        }
        
        // vizualizers at bottom and top of the screen including sporadic circles
        for (int i = (int)borderx ; i < width-borderx ; i ++)
        {
            //colour vizualizers
            float c = PApplet.map(i, 0, width-borderx, 0, 255);
            
            //colour used for stroke of the line line vizualizers
            viz.stroke(c, 255, 255);

            // calculate lerped buffer for the visualizers going across the x axis of the screen
            lerpedBufferX[i] = PApplet.lerp(lerpedBufferX[i], viz.ab.get(i), 0.2f);
            
            //calculate frequency to be relative to the range from the top or bottom of the border to the screen
            float f = lerpedBufferX[i] * bordery;

            // bottom visualizer, if statement filters out bottom of the bottom line vizualizer
            if ((halfH/2) + f + (bordery*2) < (halfH/2) - f + (bordery*2))
            {
                viz.line(i, (halfH/2) + (f/2) + (bordery*2), i, (halfH/2) + (bordery*2));  
            }
            // top visualizer, if statement filters out top of the line top vizualizer
            if ((halfH/2) + f > (halfH/2) - f)
            {
                viz.line(i, (halfH/2) + (f/2), i, (halfH/2));
            }

            // calculate x and y to spread of ellipses
            x = PApplet.sin(i)*5;
            y = PApplet.sin(i)*width-(borderx*2);

            //filter out remaining circles outside of tv
            if ( i + x > borderx + 10 && i+x < width-borderx - 10 && i+y > bordery + 10 && i+y < height - bordery - 10)
            {
                viz.ellipse(i + x, i + y, f/3, f/3);
            }
            
        }
        
        // iterate through the width of the screen - 20 on both sides so visualizer doesnt merge with side visualizers
        for(int i = (int)borderx ; i < width-(borderx*2); i ++)
        {
            // colour for main vizualizer
            float c = PApplet.map(i, 0, width-borderx, 255, 0);
            viz.stroke(c, 255, 255);

            //calculate frequency to be relative to the range from the bottom or top of the border to the screen
            float f = lerpedBufferX[i] * bordery;

            // main vizualizer in the center
            viz.line(i+10, (halfH) + (f), width-10- (i), (halfH)-(f) );
        }
        
        // iterate through the length of the screen, vizualizers on left and right hand side of the screen
        for(int i = (int)bordery; i < height -bordery; i ++)
        {
            // colour for left and right vizualizers
            float c = PApplet.map(i, 0, height-bordery, 0, 255);
            viz.stroke(c, 255, 255);

            // calculate frequency to be relative to the range from the bottom or top of the border to the screen
            float f = lerpedBufferY[i] * bordery;
            
            // left visualizer, if statement filters out left side of the left line vizualizer
            if (f + (borderx*4) > borderx*4)
            {
                viz.line(borderx, i, f/2 + borderx, i);
            }
            // right visualizer, if statement filters out right side of the right line vizualizer
            if (f + (borderx*4) < borderx*4)
            {
                viz.line(borderx*4, i, f/2 + (borderx*4), i);
            }
            
        }

    }
    
}
