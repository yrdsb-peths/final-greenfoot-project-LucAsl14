import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This world is called whenever the player presses ESCAPE in-game.
 * As its name suggests, this pauses the game, and shows the continue,
 * settings, and quit button.
 */
public class PauseScreen extends World
{
    /**
     * constructor for PauseScreen
     */
    public PauseScreen(Game world)
    {    
        super(1200, 600, 1);
        setBackground("tankBackground.jpg");
        final int cx = getWidth()/2, cy = getHeight()/2;
        Label title = new Label("Game Paused", 90);
        Button toContinue = new Button("Continue", 70, "continue", world);
        Button settings = new Button("Settings", 70, "settings", this);
        Button quit = new Button("Quit", 70, "quit");
        
        title.setLineColor(Color.RED);
        toContinue.setLineColor(Color.RED);
        settings.setLineColor(Color.RED);
        quit.setLineColor(Color.RED);
        
        addObject(title, cx, 100);
        addObject(toContinue, cx, cy+50);
        addObject(settings, cx, cy+150);
        addObject(quit, cx, cy+250);
    }
}
