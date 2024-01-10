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
            if(command == "toggleTraps"){
                if(Trap.toggleFunny()==true){
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
        if(command == "toggleTraps"){
            if(Trap.funnyTraps==true){
                setFillColor(Color.GRAY);
            }
        }
    }
}
