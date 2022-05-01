package C20441826;

//Import libraries
import ie.tudublin.Visual;
import processing.core.PVector;

public class Viz6 extends Visual 
{//Start Class
    //Declare Variables
    VisualSetup viz;
    private float height;
    private float high;
    private float openHeight, openWidth;
    private float bWidth, bHeight;
    private float color;
    private float hoBlocks;
    private PVector border, border2, center;

    //Constructor for Sixth visualizer
    public Viz6(float width, float height, PVector border, PVector border2, PVector center, VisualSetup viz)
    {
        this.height = height;
        this.viz = viz;
        this.border = border;
        this.border2 = border2;
        this.center = center;
        this.openHeight = (height - (border.y * 2)) * 0.75f;
        this.openWidth = ((openHeight/9) * 16) * 0.75f;
        this.hoBlocks = 40;
        this.bHeight = (height - (border.y*2)) / (hoBlocks/2);
        this.bWidth = (width - (border.x*2)) / hoBlocks;
    }//End Constructor

    //Render method to be called from draw()
    public void render()
    {
        //Draw inside tv graphic
        viz.noStroke();
        for(int i = 0; i < hoBlocks; i++)
        {
            //Rect color is dependant on the amplitude of the corresponding audio band.
            //Color variable needed to use same color on bezier curves
            color = map(viz.ab.get((int)map(i, 0, hoBlocks/2, 0, viz.getFFT().specSize())), -1, 1, 0, 360);
            viz.fill(color, 255, 255);

            //Draw border using rectangles.
            viz.rect(border.x+ (bWidth * i), border.y, bWidth, bWidth);
            viz.rect(border.x + (bWidth * i), border2.y, bWidth, -1*bWidth);
        }
        //Left and right border
        viz.rect(border.x, border.y, bHeight, height-(border.y*2));
        viz.rect(border2.x, border.y, -1*bHeight, height-(border.y*2));


        //reset variable to 0 so a new highest band can be found in the loop
        high = 0;
        //Loop through every band in the audio spectrum
        for(int i = 0; i < viz.getFFT().specSize(); i++)
        {
            //Rect color is dependant on the amplitude of the corresponding audio band.
            viz.fill(map(viz.ab.get(i), -1, 1, 0, 360), 255, 255);
            //draw audio spectrum with a rectangle representing each band
            viz.rect((center.x-openWidth/2) + (openWidth/viz.getFFT().specSize()) * i, center.y, openWidth/viz.getFFT().specSize(), map(viz.ab.get(i), -1, 1, -1*(openHeight/4), openHeight/4));
            
            //compare high against each band to find the appropriate value
            if(viz.ab.get(i) > high)
            {
                high = viz.ab.get(i);
            }//End if
        }//End for

        //draw bezier curves from spectrum dependant on amplitude
        viz.noFill();
        viz.stroke(color, 255, 255);
        viz.bezier(center.x+openWidth/2, center.y, lerp(center.x+openWidth/2, border2.x, 0.5f), center.y, lerp(center.x+openWidth/2, border2.x, 0.5f), map(high, -1, 1, center.y, center.y-openHeight/2), border2.x-bHeight, map(high, -1, 1, center.y, center.y-openHeight/2));
        viz.bezier(center.x+openWidth/2, center.y, lerp(center.x+openWidth/2, border2.x, 0.5f), center.y, lerp(center.x+openWidth/2, border2.x, 0.5f), map(high, -1, 1, center.y, center.y+openHeight/2), border2.x-bHeight, map(high, -1, 1, center.y, center.y+openHeight/2));
        viz.bezier(center.x-openWidth/2, center.y, lerp(center.x-openWidth/2, border.x, 0.5f), center.y, lerp(center.x-openWidth/2, border.x, 0.5f), map(high, -1, 1, center.y, center.y-openHeight/2), border.x+bHeight, map(high, -1, 1, center.y, center.y-openHeight/2));
        viz.bezier(center.x-openWidth/2, center.y, lerp(center.x-openWidth/2, border.x, 0.5f), center.y, lerp(center.x-openWidth/2, border.x, 0.5f), map(high, -1, 1, center.y, center.y+openHeight/2), border.x+bHeight, map(high, -1, 1, center.y, center.y+openHeight/2));
    }//End render
}//End Class