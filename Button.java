import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A special extension of the Label, this class allows label to be
 * clicked for an effect that is pre-coded into this class
 */
public class Button extends Label
{
    String command; 
    World world;
    // Default constructor
    public Button(String text, int size, String command)
    {
        super(text, size);
        this.command = command;
    }
    // Constructor for when the button is linked to a world
    public Button(String text, int size, String command, World world){
        this(text, size, command);
        this.world = world;
    }
    public void addedToWorld(World world){
        if(command=="toggleFunnyTraps" && Trap.isFunny
         ||command=="toggleGatling" && Powerup.isActive[0]
         ||command=="toggleRemote" && Powerup.isActive[1]
         ||command=="toggleBomb" && Powerup.isActive[2]
         ||command=="toggleTrap" && Powerup.isActive[3]
         ||command=="toggleRay" && Powerup.isActive[4]
         ||command=="toggleDrill" && Powerup.isActive[5])
         setFillColor(Color.GRAY);
    }
    public void act(){
        if(Greenfoot.mousePressed(this)){
            setFillColor(Color.GRAY);
        }
        if(Greenfoot.mouseClicked(this)){
            setFillColor(Color.WHITE);
            if(command == "singleplayer" || command == "multiplayer"){
                Game g = new Game(command);
                Game.resetScores();
                g.newGame();
            }
            if(command == "rules"){
                Greenfoot.setWorld(new Rules());
            }
            if(command == "settings"&& world!=null){
                Greenfoot.setWorld(new Settings(world));
            }
            if(command == "toggleFunnyTraps"){
                if(Trap.toggleFunny()){
                    setFillColor(Color.GRAY);
                }
            }
            if(command == "powerups"&& world!=null){
                Greenfoot.setWorld(new PowerupSettings(world));
            }
            if(command == "toggleGatling"){
                if(Powerup.togglePowerup(0)){
                    setFillColor(Color.GRAY);
                }
            }
            if(command == "toggleRemote"){
                if(Powerup.togglePowerup(1)){
                    setFillColor(Color.GRAY);
                }
            }
            if(command == "toggleBomb"){
                if(Powerup.togglePowerup(2)){
                    setFillColor(Color.GRAY);
                }
            }
            if(command == "toggleTrap"){
                if(Powerup.togglePowerup(3)){
                    setFillColor(Color.GRAY);
                }
            }
            if(command == "toggleRay"){
                if(Powerup.togglePowerup(4)){
                    setFillColor(Color.GRAY);
                }
            }
            if(command == "toggleDrill"){
                if(Powerup.togglePowerup(5)){
                    setFillColor(Color.GRAY);
                }
            }
            if(command == "continue" && world!=null){
                Greenfoot.setWorld(world);
            }
            if(command == "quit"){
                Greenfoot.setWorld(new TitleScreen());
            }
        }
    }
}
