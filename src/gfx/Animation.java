package gfx;

import java.awt.image.BufferedImage;

public class Animation {

    public Bitmap[] frames;
    public int currentFrame;
    public int timesPlayed;
    public int ticks;
    public int delay;
    public int numFrames;
    public boolean shouldAnimate;

    public Animation(){
        delay = 6;
    }
    public void setFrames(Bitmap[] frames){
        this.frames = frames;
        currentFrame = 0;
        timesPlayed = 0;
        ticks = 0;
        numFrames = frames.length;
    }

    public void update(){
        ticks++;

        if(ticks == delay){
            if(shouldAnimate){
                currentFrame++;
            }else{
                currentFrame = 0;
            }
            ticks = 0;
        }

        if(currentFrame > numFrames - 1){
            currentFrame = 0;
        }
    }

    public void setDelay(int delay){
        this.delay = delay;
    }

    public Bitmap getImage(){
        return frames[currentFrame];
    }

    public void start(){
        shouldAnimate = true;
    }

    public void stop(){
        currentFrame = 0;
        shouldAnimate = false;
    }

    public void finish(){
        shouldAnimate = false;
    }

}
