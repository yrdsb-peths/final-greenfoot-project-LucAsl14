import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A special extension of the Label, this class allows label to be
 * clicked for an effect that is pre-coded into this class
 */
public class Button extends Label
{
    String command;
    public Button(String text, int size, String command)
    {
        super(text, size);
        this.command = command;
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
                //important; supposed to be game
            }
            if(command == "rules"){
                Greenfoot.setWorld(new Rules());
            }
        }
    }
}
