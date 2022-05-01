package C20441826;

//Import libraries
import ie.tudublin.Visual;
import processing.core.PImage;
import processing.core.PVector;

public class Viz5 extends Visual 
{//Start Class
    //Declare variables
    private VisualSetup viz;
    private float width;
    private float high;
    private float openHeight, openWidth;
    private float spotlightDist;
    private float offset1, offset2, offset3, offset4;
    private float spotlightSpeed1, spotlightSpeed2;
    private float imgPixelDim;
    private PImage img;
    private PVector border, border2, center, imgOrigin;

    //Constructor for fifth Visualizer
    public Viz5(float width, float height, PImage img, PVector border, PVector border2, PVector center, VisualSetup viz)
    {
        this.width = width;
        this.viz = viz;
        this.img = img;

        this.border = border;
        this.border2 = border2;
        this.center = center;

        this.openHeight = (height - (border.y * 2)) - 50;
        this.openWidth = (openHeight/9) * 16;
        this.imgPixelDim = openHeight / img.height;

        this.imgOrigin = new PVector(center.x - ((img.width * imgPixelDim)/2), center.y - ((img.height * imgPixelDim)/2));

        this.spotlightDist = (openWidth - openHeight)/6;
        this.spotlightSpeed1 = 2;
        this.spotlightSpeed2 = 1;

        this.offset1 = ((center.x) - (openWidth/2)) + (spotlightDist);
        this.offset2 = ((center.x) - (openWidth/2)) + (spotlightDist * 2);
        this.offset3 = ((center.x) + (openHeight/2)) + (spotlightDist);
        this.offset4 = ((center.x) + (openHeight/2)) + (spotlightDist * 2);
    }//End Constructor

    //Render method to be called from draw()
    public void render()
    {
        //set colormode to RGB for duration of render
        viz.colorMode(RGB, 255, 255, 255);

        //draw inside tv graphic
        //reset variable to 0 so a new highest band can be found in the loop
        high= 0;

        //loop through first half of the audio spectrum
        for (int i = 0; i < viz.getFFT().specSize()/2; i++)
        {
            //compare high against each band to find the appropriate value
            if(viz.ab.get(i) > high)
            {
                high = viz.ab.get(i);
            }  //End if          
            
            //draw two mirrored rectangles to represent each band in the first half of the spectrum.
            //The height of each being determined by the amplitude of it's corresponding band.
            //The amount of red color is determined the same way. each bar is given a white outline.
            viz.noStroke();
            viz.fill(map(viz.ab.get(i), -1, 1, 200, 255), 50, 50);
            viz.rect(map(i, 0, viz.getFFT().specSize()/2, border.x, center.x), border2.y, ((width - (2 * border.x))/2)/(viz.getFFT().specSize()/2), -1 * map(viz.ab.get(i), -0.78f, 0.73f, 0, 0.1f * dist(0, center.y, 0, border2.y)));
            viz.rect(map(i, 0, viz.getFFT().specSize()/2, border2.x, center.x), border2.y, -1 * ((width - (2 * border.x))/2)/(viz.getFFT().specSize()/2), -1 * map(viz.ab.get(i), -0.78f, 0.73f, 0, 0.1f * dist(0, center.y, 0, border2.y)));
        }//End for

        //Nested for loop to generate image co-ordinants.

        for(int x = 0; x < img.width; x++)
        {
            //LeftGW and TopGW are used to define an abstract grid. Gw = grid wall
            float leftGW = imgOrigin.x + (imgPixelDim * x);

            for(int y = 0; y < img.height; y++)
            {
                float topGW = imgOrigin.y + (imgPixelDim * y);

                //Find linear equivelent of nested loop position
                int loc = x + y * img.width;

                //The functions red(), green(), and blue() pull out the 3 color components from a pixel.
                float r = viz.red(img.pixels[loc]);
                float g = viz.green(img.pixels[loc]);
                float b = viz.blue(img.pixels[loc]);

                //Use values extracted from image to color circles with no outline
                viz.noStroke();
                viz.fill(r, g, b);

                //Each circle representing an img pixel is drawn in a unique segment of the grid.
                //The diameter of the circles is dependant on the highest amplitude currently displayed.
                viz.circle(leftGW + (imgPixelDim/2), topGW + (imgPixelDim/2), map(high, 0, 1, imgPixelDim/5, imgPixelDim));
            }//End for
        }//End for

        if(width >= 1000){
            //Draw spotlights to the screen using triangles and elipses
            viz.fill(0, 255, 0, 50);
            viz.stroke(0, 255, 0, 75);
            viz.triangle(((center.x) - (openWidth/2)) + (spotlightDist * 2), (center.y) - (openHeight/2), offset2 - spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2, offset2 + spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2);
            viz.fill(0, 255, 0);
            viz.ellipse(offset2, (center.y) + (openHeight/2) - spotlightDist/2, spotlightDist, spotlightDist/2);
                
            viz.fill(255, 0, 0, 50);
            viz.stroke(255, 0, 0, 75);
            viz.triangle(((center.x) - (openWidth/2)) + (spotlightDist * 1), (center.y) - (openHeight/2), offset1 - spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2, offset1 + spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2);
            viz.fill(255, 0, 0);
            viz.ellipse(offset1, (center.y) + (openHeight/2) - spotlightDist/2, spotlightDist, spotlightDist/2);

            viz.fill(0, 0, 255, 50);
            viz.stroke(0, 0, 255, 75);
            viz.triangle(((center.x) + (openHeight/2)) + (spotlightDist * 1), (center.y) - (openHeight/2), offset3 - spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2, offset3 + spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2);
            viz.fill(0, 0, 255);
            viz.ellipse(offset3, (center.y) + (openHeight/2) - spotlightDist/2, spotlightDist, spotlightDist/2);

            viz.fill(255, 125, 0, 50);
            viz.stroke(255, 125, 0, 75);
            viz.triangle(((center.x) + (openHeight/2)) + (spotlightDist * 2), (center.y) - (openHeight/2), offset4 - spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2, offset4 + spotlightDist/2, (center.y) + (openHeight/2) - spotlightDist/2);
            viz.fill(255, 125, 0);
            viz.ellipse(offset4, (center.y) + (openHeight/2) - spotlightDist/2, spotlightDist, spotlightDist/2);

            //Border detection for moving spotlights
            if(offset1 - spotlightDist/2 + spotlightSpeed1 < border.x || offset1 + spotlightDist/2 + spotlightSpeed1 > border2.x)
            {
                spotlightSpeed1 *= -1;
            }//End if

            else if(offset2 - spotlightDist/2 + spotlightSpeed2 < border.x || offset2 + spotlightDist/2 + spotlightSpeed2 > border2.x)
            {
                spotlightSpeed2 *= -1;
            }//End if
            
            else if(offset3 - spotlightDist/2 + spotlightSpeed2 < border.x || offset3 + spotlightDist/2 + spotlightSpeed2 > border2.x)
            {
                spotlightSpeed2 *= -1;
            }//End if
            
            else if(offset4 - spotlightDist/2 + spotlightSpeed1 < border.x || offset4 + spotlightDist/2 + spotlightSpeed1 > border2.x)
            {
                spotlightSpeed1 *= - 1;
            }//End if

            //Move spotlights over time by adjusting their offset relative to their their speed value.
            offset1 += spotlightSpeed1;
            offset2 += spotlightSpeed2;
            offset3 += spotlightSpeed2;
            offset4 += spotlightSpeed1;
        }
        
        //Change colormode back to HSB before returning to VisualSetup
        viz.colorMode(HSB, 255, 255, 255);

    }//End Render
}//End Class