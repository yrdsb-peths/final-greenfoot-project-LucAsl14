import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Although separate from the Settings world, this world is also essentially
 * a settings world, using the constructor from the Settings class.
 * Called when clicking on the "powerups" button in "Settings", this shows a 
 * toggle button for each powerup in the game.
 */
public class PowerupSettings extends Settings
{
    Button bomb, drill, ray, gatling, remote, trap;
    public PowerupSettings(World world)
    {
        super(world);
    }
    /**
     * override for Settings's showSettings to show different options.
     */ 
    protected void showSettings(){
        bomb = new Button("toggle frag bombs", 60, "toggleBomb");
        drill = new Button("toggle drills", 60, "toggleDrill");
        ray = new Button("toggle death rays", 60, "toggleRay");
        gatling = new Button("toggle gatling guns", 60, "toggleGatling");
        remote = new Button("toggle remote bullets", 60, "toggleRemote");
        trap = new Button("toggle traps", 60, "toggleTrap");
        
        bomb.setLineColor(Color.RED);
        drill.setLineColor(Color.RED);
        ray.setLineColor(Color.RED);
        gatling.setLineColor(Color.RED);
        remote.setLineColor(Color.RED);
        trap.setLineColor(Color.RED);
        
        addObject(bomb, cx-300, cy-200);
        addObject(new Powerup(2), cx-300, cy-150);
        addObject(drill, cx+300, cy-200);
        addObject(new Powerup(5), cx+300, cy-150);
        addObject(ray, cx-300, cy);
        addObject(new Powerup(4), cx-300, cy+50);
        addObject(gatling, cx+300, cy);
        addObject(new Powerup(0), cx+300, cy+50);
        addObject(remote, cx-300, cy+200);
        addObject(new Powerup(1), cx-300, cy+250);
        addObject(trap, cx+300, cy+200);
        addObject(new Powerup(3), cx+300, cy+250);
        
    }
}
