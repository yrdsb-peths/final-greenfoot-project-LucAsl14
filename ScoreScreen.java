import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This world is called at the end of singleplayer, where it compares your
 * score with the highest score this session.
 */
public class ScoreScreen extends World
{
    static int bestScore = 0;
    final int cx = getWidth()/2, cy = getHeight()/2;
    /**
     * constructor for ScoreScreen
     */
    public ScoreScreen(int score)
    {    
        super(1200, 600, 1); 
        setBackground("tankBackground.jpg");
        showScore(score);
    }
    private void showScore(int score){
        Label yourScore = new Label("Your Score: "+score, 70);
        Label congrats = new Label ("Congratulations on achieving a highscore!", 70);
        Label instructions = new Label("Click anywhere to go back", 70);
        
        yourScore.setLineColor(Color.RED);
        congrats.setLineColor(Color.RED);
        instructions.setLineColor(Color.RED);
        
        if(score > bestScore){
            addObject(congrats, cx, cy+100);
            bestScore = score;
        }
        Label highScore = new Label("Best Score: "+bestScore, 70);
        highScore.setLineColor(Color.RED);
        
        addObject(yourScore, cx, cy-200);
        addObject(highScore, cx, cy-100);
        addObject(instructions, cx, cy+200);
    }
    public void act(){
        // returns to title screen when mouse is clicked
        if(Greenfoot.mouseClicked(null)){
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
