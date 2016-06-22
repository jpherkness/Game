package gfx;

import java.awt.image.BufferedImage;

public class Animation {

    protected Bitmap[] frames;
    protected int currentFrame;
    protected int timesPlayed;
    protected int ticks;
    protected int delay;
    protected int numFrames;
    protected boolean shouldAnimate;

    public Animation(){
        delay = 6;
        shouldAnimate = true;
    }
    public void setFrames(Bitmap[] frames){
        this.frames = frames;
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

    public void finish(){
        shouldAnimate = false;
    }

}
