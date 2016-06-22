package helper;

import gfx.Screen;
import gfx.Bitmap;

public class BoundingBox {

    public int top;
    public int left;
    public int bottom;
    public int right;

    /**
     * Creates a bounding box with the default dimensions.
     */
    public BoundingBox(){
        setBoundary(-1, -1, 0, 0);
    }

    /**
     * Creates a bounding box with the specified dimensions.
     * @param x      the x position of the bounding box
     * @param y      the y position of the bounding box
     * @param width  the width of the bounding box
     * @param height the height of the bounding box
     */
    public BoundingBox(int x, int y, int width, int height){
        setBoundary(x, y, width, height);
    }

    /**
     * Sets the bounds of the bounding box to the specified dimensions.
     * @param x      the x position of the bounding box
     * @param y      the y position of the bounding box
     * @param width  the width of the bounding box
     * @param height the height of the bounding box
     */
    public void setBoundary(int x, int y, int width, int height){
        top = y;
        left = x;
        right = x + width - 1;
        bottom = y + height - 1;
    }

    /**
     * Returns a boolean indicating whether or not two bounding boxes
     * are overlapping.
     * @param box the bounding box being checked
     * @return    a boolean indicating whether an overlap exists
     */
    public boolean overlaps(BoundingBox box){
        BoundingBox box1 = this;
        BoundingBox box2 = box;

        if(box1.bottom < box2.top) return false;
        if(box1.top > box2.bottom) return false;
        if(box1.left > box2.right) return false;
        if(box1.right < box2.left) return false;

        return true;
    }

    /**
     * Returns a boolean indicating whether or not a bounding box is
     * contained inside this bounding box.
     * @param box the bounding box being checked
     * @return    a boolean indicating whether the box is inside this box
     */
    public boolean inside(BoundingBox box){

        if(box.left <= this.left &&
                box.right >= this.right &&
                box.top <= this.top &&
                box.bottom >= this.bottom)
            return true;

        return false;
    }

    /**
     * Changes the position of the bounding box.
     * @param xo the x offest
     * @param yo the y offset
     */
    public void move(int xo, int yo){
        right += xo;
        left += xo;
        top += yo;
        bottom += yo;
    }

    /**
     * Changes the position of the bounding box.
     * @param x the x position
     * @param y the y position
     */
    public void setPosition(int x, int y){
        int width = right - left;
        int height = bottom - top;
        setBoundary(x, y, width, height);
    }
}
