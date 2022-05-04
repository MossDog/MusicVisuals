# Music Visualiser Project

### Name & Student Number: 


Ryan Deguara - C20309873  
Conor Davis - C20441826  
Luke Hughes - C20487654  


## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment
The main focus for this assignment was to have a television visualisation taking inspiration from the Rap God music video, to get that similar feeling and environment when you use our work, along with making the visuals fully dynamic in relation to resolution. 

In the [video](https://www.youtube.com/watch?v=XbGs_qK2PQA), you can see him appear on the TV with some cool colour visualization in the background and we felt that this would be a good idea to base our project around. We developed a TV with six different visuals that appear on the screen, along with buttons on the TV that you can interact with to change the visual or to pause / play it. We each created two different visualizations to give a plethora of options to use to visualize the song as it reaches over six minutes in length. 

Alongside this, we have made almost everything completely responsive down to 500x500px resolution. This added a complex layer to everything we did, as not only did we have to make sure the TV was working, both the visuals inside the screen and the buttons, we had to make sure they adapted to whatever resolution was being used due to fullscreen. We felt that few resolutions would have lower than 500 pixels in either height or width and getting any lower would make our assignment unfeasible, so on the off chance it would happen, the TV visual would be disabled.

# Instructions
When you start the visualizer, the song will automatically start playing and the first of six visuals will respond to the music. We made the main functionality around the TV graphic, so:

Power Button (Big) --> Pause / Play the Music  
Previous Channel (Smaller Left) --> Previous Visualization  
Next Channel (Smaller Right) --> Next Visualization  

You can also change the visuals using the keys 1-6, alongside hitting the spacebar to pause and hitting it again to restart the song at the beginning if you really need to, to save you from restarting the visual all over again.

# How It Works
This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

# What Are We Most Proud Of?
We are most proud of how we came together at multiple times over the last few weeks to really work together to make sure that we were on the same page. In our future careers, we will most certainly be faced with working in teams with other programmers, and this experience has helped immensely with gaining experience in working together with others, coming to compromises and discussions over what should be done next.

# Video
[![YouTube](https://i.ytimg.com/vi/XbGs_qK2PQA/maxresdefault.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

