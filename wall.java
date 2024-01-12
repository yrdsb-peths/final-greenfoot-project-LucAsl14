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
        // prevents independant small walls from forming
        if(!isTouching(Wall.class)&&length<7){
            world.removeObject(this);
            return;
        } else if(length<7){
            if(((Wall) getOneIntersectingObject(Wall.class)).length<7)
                world.removeObject(this);
                return;
        }
        
        // these 2 prevent small walls from forming perpendicular to world border
        if(isTouching(VerticalWall.class)&&this instanceof VerticalWall&&length<7){
            world.removeObject(this);
            return;
        }
        if(isTouching(HorizontalWall.class)&&this instanceof HorizontalWall&&length<7){
            world.removeObject(this);
            return;
        }
    }
    public boolean removeSelf(){
        // skip small walls and borders
        if(length<7) return false;
        if(length>=80) return true;
        World world = getWorld();
        List<Wall> touchingWalls = getIntersectingObjects(Wall.class);
        int x = getX(), y = getY();
        world.removeObject(this);
        while(touchingWalls.size()!=0){
            // prevents this from removing borders
            if(touchingWalls.get(0).length>=80){
                touchingWalls.remove(0);
                continue;
            }
            
            // prevents this from removing parallel large walls other than the other side
            if(touchingWalls.get(0).length==75){
                if((this instanceof HorizontalWall && !(touchingWalls.get(0) instanceof HorizontalWall))
                 ||(this instanceof VerticalWall && !(touchingWalls.get(0) instanceof VerticalWall))
                 ){
                    touchingWalls.remove(0);
                    continue;
                 }
            }
            
            // prevents this from removing perpendicular small walls
            if(touchingWalls.get(0).length<=10){
                if((this instanceof HorizontalWall
                 && !(touchingWalls.get(0) instanceof VerticalWall))
                || (this instanceof VerticalWall
                 && !(touchingWalls.get(0) instanceof HorizontalWall))){
                    // confirm that the wall won't be independant
                    touchingWalls.get(0).addedToWorld(world);
                    touchingWalls.remove(0);
                    continue;    
                 }
            }
            
            world.removeObject(touchingWalls.get(0));
            touchingWalls.remove(0);
        }
        if(this instanceof TopHorizontalWall){
            world.addObject(new LeftVerticalWall(5), x+38, y+2);
            world.addObject(new RightVerticalWall(5), x-38, y+2);
        } else if(this instanceof BottomHorizontalWall){
            world.addObject(new LeftVerticalWall(5), x+38, y-2);
            world.addObject(new RightVerticalWall(5), x-38, y-2);
        } else if(this instanceof LeftVerticalWall){
            world.addObject(new TopHorizontalWall(5), x+2, y+38);
            world.addObject(new BottomHorizontalWall(5), x+2, y-38);
        } else if(this instanceof RightVerticalWall){
            world.addObject(new TopHorizontalWall(5), x-2, y+38);
            world.addObject(new BottomHorizontalWall(5), x-2, y-38);
        } 
        return true;
    }
}
