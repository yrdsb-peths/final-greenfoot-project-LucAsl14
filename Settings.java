import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This world is called when the "settings" button is pressed
 * It holds 2 settings for now: "Toggle funny traps" and "powerups"
 */
public class Settings extends World
{
    int cx, cy;
    Button funnyTraps, togglePowerups, funnyRays;
    World world;
    /**
     * constructor for Settings
     */
    public Settings(World world){
        super(1200, 600, 1);
        cx = getWidth()/2; cy = getHeight()/2;
        setBackground("tankBackground.jpg");
        this.world = world;
        showSettings();
    }
    /**
     * makes the buttons for the settings mentioned in the description
     */
    protected void showSettings(){
        funnyRays = new Button("toggle funny death ray", 80, "toggleFunnyRays");
        funnyTraps = new Button("toggle funny traps", 80, "toggleFunnyTraps");
        togglePowerups = new Button("powerups", 80, "powerups", this);
        
        funnyRays.setLineColor(Color.RED);
        funnyTraps.setLineColor(Color.RED);
        togglePowerups.setLineColor(Color.RED);
        
        addObject(funnyRays, cx, cy-100);
        addObject(funnyTraps, cx, cy);
        addObject(togglePowerups, cx, cy+100);
    }
    public void act(){
        // when background is clicked, return to previous world
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(world);
        }
    }
}
