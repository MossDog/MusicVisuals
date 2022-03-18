package ie.tudublin;

/*
import example.AudioBandsVisual;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
*/
import c123456.BryansVisual;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new BryansVisual());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();		
	}
}