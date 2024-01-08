import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Settings extends World
{
    int cx, cy;
    Button funnyTraps;
    World world;
    public Settings(World world)
    {    
        super(1200, 600, 1);
        cx = getWidth()/2; cy = getHeight()/2;
        setBackground("tankBackground.jpg");
        showSettings();
        this.world = world;
    }
    private void showSettings(){
        funnyTraps = new Button("toggle funny traps", 80, "toggleTraps");
        
        funnyTraps.setLineColor(Color.RED);
        
        addObject(funnyTraps, cx, cy);
    }
    public void act(){
        if(Greenfoot.mouseClicked(null)&&!Greenfoot.mouseClicked(funnyTraps)){
            Greenfoot.setWorld(world);
        }
    }
}
