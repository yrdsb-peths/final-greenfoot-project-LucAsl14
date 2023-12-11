import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


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
                Game game = new Game(command);
                Greenfoot.setWorld(game);
            }
            if(command == "rules"){
                Rules rules = new Rules();
                Greenfoot.setWorld(rules);
            }
        }
    }
}
