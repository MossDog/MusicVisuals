package C20441826;
import ie.tudublin.Visual;

public class Viz4 extends Visual 
{

    VisualSetup viz;
    float[] lerpedBuffer;
    float[] lerpedBufferY;
    float width, height, smoothedAmplitude;

    public Viz4(float width, float height, float smoothedAmplitude, float lerpedBuffer[], VisualSetup viz)
    {
        this.height = height;
        this.width = width;
        this.smoothedAmplitude = smoothedAmplitude;
        this.lerpedBuffer = lerpedBuffer;
        this.lerpedBufferY = lerpedBuffer;
        this.viz = viz;
    }

    public void render()
    {

        viz.colorMode(HSB);

        float average = 0;
        float sum = 0;
        float borderx = width * 0.2f;
        float bordery = height * 0.25f;

        for(int i = (int)borderx; i < width-borderx ; i ++)
        {
            sum += abs(viz.ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], viz.ab.get(i), 0.2f);
        }

        average = sum / (float) viz.ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        viz.noFill();
        viz.stroke(255,0,255);
        viz.beginShape();
        viz.vertex(borderx, bordery);
        viz.bezierVertex(borderx, bordery, borderx+smoothedAmplitude*1000, 0, borderx, height-bordery);
        viz.endShape();

    }

}
