import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(1200, 600, 1);
        int cx = getWidth()/2, cy = getHeight()/2;
        setBackground("tankBackground.jpg");
        Label title = new Label("Tank Trouble�", 80);
        Button singleplayer = new Button("1 player", 80, "singleplayer");
        Button multiplayer = new Button("2 players", 80, "multiplayer");
        Button rules = new Button("rules", 80, "rules");
        
        title.setLineColor(Color.RED);
        singleplayer.setLineColor(Color.RED);
        multiplayer.setLineColor(Color.RED);
        rules.setLineColor(Color.RED);
        
        addObject(title, cx, 100);
        addObject(singleplayer, cx-400, cy+200);
        addObject(multiplayer, cx+400, cy+200);
        addObject(rules, cx, cy+100);
    }
}
