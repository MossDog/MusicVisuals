package C20441826;
import ie.tudublin.Visual;

public class Viz2 extends Visual 
{

    VisualSetup viz;
    float[] lerpedBuffer;
    float width, height;
    float borderx = width * 0.2f;
    float bordery = height * 0.25f;
    float n4;
    float n6;
    float angle2;

    public Viz2(float width, float height, VisualSetup viz)
    {
        this.height = height;
        this.width = width;
        this.viz = viz;
    }

    public void render()
    {
        viz.colorMode(HSB);

        viz.translate(width/2, height/2);
        
        for (int i = (int) borderx; i < width-borderx; i++) {
            float c = map(i, 0, width-borderx, 0, 255);

            
            float angle = sin(i+n4)* 10; 
            if (height >= width)
            {
                angle2 = sin(i+n6)* width; 
            }
            else
            {
                angle2 = sin(i+n6)* height; 
            }
            
        
            float x = (sin(radians(i))*(angle2))/4; // width of outspread
            float y = (cos(radians(i))*(angle2))/4;
        
            //float x3 = sin(radians(i))*((width/2)/angle); 
            float y3 = cos(radians(i))*((width/2)/angle);

            //stroke(c, 255, 255);
            viz.fill(c, 255, 255);

            if (y3 > -(height/5) && y3 < (height/5))
            {
                viz.rect(x, y3, viz.ap.left.get(i)*10, viz.ap.left.get(i)*10);
            }
            //if (x3 > -(width/5) && x3 < (width/5))
            //{
                //viz.rect(x3, y, viz.ap.left.get(i)*10, viz.ap.left.get(i)*10);
            //}
            //viz.ellipse(x, y, viz.ap.left.get(i)*20, viz.ap.left.get(i)*20);
                    

            star(x, y, viz.ap.left.get(i)*5, viz.ap.right.get(i)*5, 5);


            //viz.rect(x3, y3, viz.ap.left.get(i)*20, viz.ap.left.get(i)*10);
            //viz.rect(x, y, viz.ap.right.get(i)*10, viz.ap.left.get(i)*10);
            //viz.rect(x3, y3, viz.ap.right.get(i)*10, viz.ap.right.get(i)*20);
            
            
            
        }
        
        n4 += 0.0008;
        if (n4 > borderx && n4 < width-borderx)
        {
            n4 = 0;
        }
        n6 += 0.04;
    }

    private void star(float x, float y, float radius1, float radius2, int npoints) {
        float angle = TWO_PI / npoints;
        float halfAngle = angle/2;

        viz.beginShape();
        for (float a = 0; a < TWO_PI; a += angle) {
          float sx = x + cos(a) * radius2;
          float sy = y + sin(a) * radius2;
          viz.vertex(sx, sy);
          sx = x + cos(a+halfAngle) * radius1;
          sy = y + sin(a+halfAngle) * radius1;
          viz.vertex(sx, sy);
        }
        viz.endShape(CLOSE);
    }
}
