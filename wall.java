import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Wall extends Actor
{
    int length, width = 5;
    public void addedToWorld(World world){
        if(isTouching(Tank.class)){
            world.removeObject(this);
            return;
        }
            
        if(!isTouching(Wall.class)&&length<7){
            world.removeObject(this);
            return;
        }
        if(isTouching(RightVerticalWall.class)&&this instanceof LeftVerticalWall&&length<7){
            world.removeObject(this);
            return;
        }
        if(isTouching(LeftVerticalWall.class)&&this instanceof RightVerticalWall&&length<7)
            world.removeObject(this);
    }
    public boolean removeSelf(){
        if(length<=4) return false;
        if(length>=80) return true;
        World world = getWorld();
        List<Wall> touchingWalls = getIntersectingObjects(Wall.class);
        while(touchingWalls.size()!=0){
            if(touchingWalls.get(0).length>=80){
                touchingWalls.remove(0);
                continue;
            }
            
            if(touchingWalls.get(0).length==75){
                if((this instanceof TopHorizontalWall && !(touchingWalls.get(0) instanceof BottomHorizontalWall))
                 ||(this instanceof BottomHorizontalWall && !(touchingWalls.get(0) instanceof TopHorizontalWall))
                 ||(this instanceof LeftVerticalWall && !(touchingWalls.get(0) instanceof RightVerticalWall))
                 ||(this instanceof RightVerticalWall && !(touchingWalls.get(0) instanceof LeftVerticalWall))
                 ){
                    touchingWalls.remove(0);
                    continue;
                 }
            }
            
            world.removeObject(touchingWalls.get(0));
            touchingWalls.remove(0);
        }
        int x = getX(), y = getY();
        world.removeObject(this);
        if(this instanceof TopHorizontalWall){
            world.addObject(new LeftVerticalWall(4), x+38, y+2);
            world.addObject(new RightVerticalWall(4), x-38, y+2);
        } else if(this instanceof BottomHorizontalWall){
            world.addObject(new LeftVerticalWall(4), x+38, y-2);
            world.addObject(new RightVerticalWall(4), x-38, y-2);
        } else if(this instanceof LeftVerticalWall){
            world.addObject(new TopHorizontalWall(4), x+2, y+38);
            world.addObject(new BottomHorizontalWall(4), x+2, y-38);
        } else if(this instanceof RightVerticalWall){
            world.addObject(new TopHorizontalWall(4), x-2, y+38);
            world.addObject(new BottomHorizontalWall(4), x-2, y-38);
        } 
        return true;
    }
}
