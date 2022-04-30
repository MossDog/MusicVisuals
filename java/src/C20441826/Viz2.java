package C20441826;

import processing.core.PVector;

public class Viz2 extends VisualSetup
{

    //declare variables
    VisualSetup viz;
    float[] lerpedBuffer;
    float width, height;
    float n1;
    float n2;
    float angle2;
    PVector border;
    PVector border2;
    PVector position;
    PVector outspread;

    //constructor for second visualizer
    public Viz2(float width, float height, VisualSetup viz)
    {//start constructor

        this.height = height;
        this.width = width;
        this.viz = viz;

    }//end Viz2

    //render called from draw
    public void render()
    { //start render method

        position = new PVector(width/2, height/2);
        border = new PVector(width * 0.2f, height * 0.25f);
        border2 = new PVector(width-border.x, height -border.y);
        viz.translate(position.x, position.y);
        
        for (int i = (int) border.x; i < border2.x; i++) 
        { //start for loop

            float c = map(i, 0, border2.x, 0, 255);
            float angle = sin(i+n1)* 10; 
            if (height >= width)
            { //start if statement
                angle2 = sin(i+n2)* width; 
            } //end if statement
            else
            { //start else statement
                angle2 = sin(i+n2)* height; 
            } //end else statement
            
            // width and height of outspread
            outspread = new PVector((sin(radians(i))*(angle2))/4, ((cos(radians(i))*(angle2)))/4.2f);

            //for center circle of rectangles
            float y3 = cos(radians(i))*((width/2)/angle);

            //stroke(c, 255, 255);
            viz.fill(c, 255, 255);

            //filter out rectangle pixels outside of tv
            if (y3 > -(height/5) && y3 < (height/5))
            { //start if statement
                viz.rect(outspread.x, y3, viz.ap.left.get(i)*10, viz.ap.left.get(i)*10);
            } //end if statement

            // call star function to create star out of values, x, y, radius1, radius2, amount of points star will have
            star(outspread.x, outspread.y, viz.ap.left.get(i)*5, viz.ap.right.get(i)*5, 5);
        
        } //end for loop
        
        // increment values to move for next use
        n1 += 0.0008;
        n2 += 0.04;

    }//end render

    // star function called from render
    private void star(float x, float y, float radius1, float radius2, int npoints) 
    { //start star function
        float angle = TWO_PI / npoints;
        float halfAngle = angle/2;

        viz.beginShape();

        for (float a = 0; a < TWO_PI; a += angle) 
        { //start for

          float sx = x + cos(a) * radius2;
          float sy = y + sin(a) * radius2;

          viz.vertex(sx, sy);
          sx = x + cos(a+halfAngle) * radius1;
          sy = y + sin(a+halfAngle) * radius1;
          viz.vertex(sx, sy);

        } //end for

        viz.endShape(CLOSE);

    }//end star

}//end class 
