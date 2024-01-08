import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class handles the title screen, where the user will get to
 * choose the gamemode and read the rules.
 */
public class TitleScreen extends World
{
    public TitleScreen()
    {    
        super(1200, 600, 1);
        int cx = getWidth()/2, cy = getHeight()/2;
        setBackground("tankBackground.jpg");
        Label title = new Label("Troubled Tanks", 80);
        Button singleplayer = new Button("1 player", 80, "singleplayer");
        Button multiplayer = new Button("2 players", 80, "multiplayer");
        Button rules = new Button("rules", 80, "rules");
        Button settings = new Button("settings", 80, "settings", this);
        
        title.setLineColor(Color.RED);
        singleplayer.setLineColor(Color.RED);
        multiplayer.setLineColor(Color.RED);
        rules.setLineColor(Color.RED);
        settings.setLineColor(Color.RED);
        
        addObject(title, cx, 100);
        addObject(singleplayer, cx-400, cy+200);
        addObject(multiplayer, cx+400, cy+200);
        addObject(rules, cx, cy+100);
        addObject(settings, cx, cy+200);
        
        addObject(new Explosion(100), 1005, 151);
    }
}
