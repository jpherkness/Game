package tile;

public class BoundingBox {

    public int top;
    public int left;
    public int bottom;
    public int right;
    public boolean shouldCollide;
    public int width;
    public int height;

    public BoundingBox(boolean shouldCollide){
        this.shouldCollide = shouldCollide;
        top = -1;
        left = -1;
        bottom = -1;
        right = -1;
    }
    public BoundingBox(int x, int y, int width, int height, boolean shouldCollide){
        setBoundary(x, y, width, height);
        this.shouldCollide = shouldCollide;
    }

    public void setBoundary(int x, int y, int width, int height){
        top = y;
        left = x;
        right = x + width - 1;
        bottom = y + height - 1;
        this.width = width;
        this.height = height;
    }

    public boolean shouldCollide(){ return shouldCollide; }

    public void setShouldCollide(boolean shouldCollide){ this.shouldCollide = shouldCollide; }

    public boolean isColliding(BoundingBox box){
        BoundingBox box1 = this;
        BoundingBox box2 = box;
        
        if(!box1.shouldCollide()) return false;
        if(!box2.shouldCollide()) return false;
        
        if(box1.bottom < box2.top) return false;
        if(box1.top > box2.bottom) return false;
        if(box1.left > box2.right) return false;
        if(box1.right < box2.left) return false;

        return true;
    }

    public boolean isOverlapping(BoundingBox box){
        BoundingBox box1 = this;
        BoundingBox box2 = box;

        if(box1.bottom < box2.top) return false;
        if(box1.top > box2.bottom) return false;
        if(box1.left > box2.right) return false;
        if(box1.right < box2.left) return false;

        return true;
    }

    public boolean isContained(BoundingBox box){

        if(box.left <= this.left &&
                box.right >= this.right &&
                box.top <= this.top &&
                box.bottom >= this.bottom)
            return true;

        return false;
    }

    public boolean contains(BoundingBox box){

        if(box.left > this.left &&
                box.right < this.right &&
                box.top > this.top &&
                box.bottom < this.bottom)
            return true;

        return false;
    }
}
