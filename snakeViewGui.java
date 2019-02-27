import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class snakeViewGui extends JFrame implements KeyListener, ActionListener {

    private ArrayList<snakeBody> snakeBody = new ArrayList<>();
    private int WinHW=440;
    private snakeElement snake;
    private epleElement eple;
    private Timer timer;
    private Timer timerEple;
    private Direction currDir = Direction.RIGHT;
    private boolean newEple = false;
    private boolean gameOver = false;
    private boolean spacetrue = false;
    private JLabel lab1 = new JLabel("Press 'Space' to begin game", SwingConstants.CENTER);
    private static JMenuItem NGitem = new JMenuItem("New Game");
    private static JMenuItem EMitem = new JMenuItem("Easy Mode");
    private boolean EMgame = false;
    public int hast = 200;
    int KorX = 20;
//----------------------------------------------------------------------------------------------------------------------
    public snakeViewGui(){
        this.setSize(WinHW,WinHW);
        this.setTitle("Assignment 04:Snake bby");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.DARK_GRAY);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        menu.add(NGitem);
        menu.add(EMitem);

        this.setJMenuBar(menuBar);
        NGitem.addActionListener(new LFB());
        EMitem.addActionListener(new LFB());

        snake = new snakeElement();
        eple = new epleElement();

        lab1.setFont(new Font("Serif", Font.BOLD, 25));
        lab1.setBackground(Color.DARK_GRAY);
        this.add(lab1);
        this.setTitle("Snake");

        timer = new Timer(hast,this);
        timer.start();
        timerEple = new Timer(7000,this);
        timerEple.start();

        this.addKeyListener(this);
        this.setVisible(true);
    }

//----------------------------------------------------------------------------------------------------------------------
    private class LFB implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == NGitem){
            NGame();
        }
        if (e.getSource() == EMitem){
            EMgame = true;
        }
    }
}
//----------------------------------------------------------------------------------------------------------------------
    //funksjon som runder til nærmeste 20.
    private static int Round2(int b){
        return b-(b%20);
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void keyTyped(KeyEvent e) { }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void keyPressed(KeyEvent e) {
        //trykk space for å begynne
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("space");
            spacetrue = true;
            lab1.setText("");
        }
        if (e.getKeyCode() == KeyEvent.VK_Q){
            System.out.println("Easy Mode OFF");
            EMgame = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_E){
            System.out.println("Easy Mode ON");
            EMgame = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_N){
            gameOver = true;
            System.out.println("New Game, N, Pressed");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && currDir != Direction.UP) {
            System.out.println("ned");
            currDir = Direction.DOWN;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && currDir != Direction.DOWN) {
            System.out.println("opp");
            currDir = Direction.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && currDir != Direction.RIGHT) {
            System.out.println("venstre");
            currDir = Direction.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currDir != Direction.LEFT) {
            System.out.println("høyre");
            currDir = Direction.RIGHT;
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void keyReleased(KeyEvent e) { }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        //hvert tidsintervall så oppdateres viduet etter reglene for snake. Dette tidsintervallet styrer også hastigheten til slangen.
        // timer er tidsintervalet. / spacetrue er om space er blitt presset slik at spille skal begynne.
        if (e.getSource().equals(timer) && spacetrue){
            if (gameOver){
                NGame();
            }
            //Sjekker for kontakt mellom eple og slangehode og legger til 3 kroppsdeler
            if(eple.kolisjonS(snake)){
                System.out.println("we have contact!");
                newEple = true;
                snakeBody.add(new snakeBody());
                snakeBody.add(new snakeBody());
                hast = hast -5;
                timerrestart();
            }
            //etter at første eple er blitt fortært legges kordinatene til kroppsdelene.
            if (!snakeBody.isEmpty()) {
                for (int i = snakeBody.size()-1; i >= 0; i--) {
                    //samtidig sjekkes det for kolisjon mellom hode og kropp
                    if (snake.kolisjonS(snakeBody.get(i)) && !EMgame){
                        System.out.println("freaky shit man");
                        gameOver = true;
                        NGame();
                        break;
                    }//føste kroppsdel etter hode tilegner seg hode sitt siste kordinater
                    if (i == 0) {
                        snakeBody.get(0).setPosX(snake.getPosX());
                        snakeBody.get(0).setPosY(snake.getPosY());
                    } else {//alle andre kroppsdeler tilegner den neste sin kordinater, altså 1 for 0 sitt, 2 for 1 sin osv.
                        snakeBody.get(i).setPosX(snakeBody.get(i - 1).getPosX());
                        snakeBody.get(i).setPosY(snakeBody.get(i - 1).getPosY());
                    }
                }
            }
            //hode sitt posisjon blir oppdatert med hensyn på retning.
            snake.posX = setDirX(snake.posX, currDir);
            snake.posY = setDirY(snake.posY, currDir);

            //checky check for om slangen er utenfor vinduet. Vinduet sitt hjørner er litt underlige og tallene kan være noe av....
            //om slangen er utenfor grensen sin dør den og spille er slutt.
            if (snake.posX < 0 || snake.posX >= 430 || snake.posY < 20 || snake.posY >= 435){
                if (EMgame) {
                    if (snake.posX == -20 && currDir == Direction.LEFT) {
                        snake.posX = snake.posX + 440;
                    }
                    if (snake.posX == 440 && currDir == Direction.RIGHT) {
                        snake.posX = snake.posX - 440;
                    }
                    if (snake.posY == 0 && currDir == Direction.UP) {
                        snake.posY = snake.posY + 420;
                    }
                    if (snake.posY == 440 && currDir == Direction.DOWN) {
                        snake.posY = snake.posY - 440;
                    }
                }else {
                    System.out.println(snake.posX + " " + snake.posY);
                    gameOver = true;
                    System.out.println("game over");
                    NGame();
                }
            }
            //funsjonen for å oppdatere alle elementer på skjerm
            DrawBoard();
        }
        //--------------------------------------------------------------------------------------------------------------
            //ved inntak av eple av slange vil et nytt eple bli satt(newEple) eller om tidsintervallet for eple går ut
        if (e.getSource().equals(timerEple) || newEple){
            //tilfeldig plasssering av nye eple
            Random random = new Random();
            int randX = Round2(((random.nextInt(380)+20)));
            int randY = Round2(((random.nextInt(380)+40)));
            eple.posX = randX +1;
            eple.posY = randY +1;
            System.out.println("eple pos"+ eple.posX + " " + eple.posY);
            System.out.println("score: " + snakeBody.size());
            newEple = false;
            //om slange inntar eple blir tidsintervalet tilbakestillt.
            timerEple.restart();
            DrawBoard();
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    public void NGame(){

        String[] newGame1 = {"New Game"};
        int valgBruker = JOptionPane.showOptionDialog(null,
                "Spillet er ferdig, klikk 'New Game' for å spill igjen",
                "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,newGame1, newGame1[0]);

        hast = 200;
        timerrestart();
        lab1.setText("Press 'Space' to begin");
        gameOver = false;
        snakeBody.clear();
        snake.posX = 200;
        snake.posY = 200;
        currDir = Direction.RIGHT;
        spacetrue = false;
        newEple = true;
        DrawBoard();
    }
    //------------------------------------------------------------------------------------------------------------------
    private void timerrestart(){

        timer = new Timer(hast, this);
        timer.restart();
    }
    //------------------------------------------------------------------------------------------------------------------
    private void DrawBoard(){
        this.getGraphics().clearRect(0, 0,  WinHW, WinHW);

        for (int n = 0; n < snakeBody.size();n++){
            snakeBody.get(n).DrawElement(this.getGraphics());
        }
        snake.DrawElement(this.getGraphics());
        eple.DrawEple(this.getGraphics());
    }
    //------------------------------------------------------------------------------------------------------------------
    private int setDirX(int x, Direction ndd){
        if (ndd == Direction.RIGHT) {
            return x + KorX;
        }
        else if (ndd == Direction.LEFT) {
            return x - KorX;
        }
        else if (ndd == Direction.UP) {
            return x;
        }
        else if (ndd == Direction.DOWN) {
            return x;
        }
        return x;
    }
    //------------------------------------------------------------------------------------------------------------------
    private int setDirY(int y, Direction ndd){
        if (ndd == Direction.RIGHT) {
            return y;
        }
        else if (ndd == Direction.LEFT) {
            return y;
        }
        else if (ndd == Direction.UP) {
            return y -KorX;
        }
        else if (ndd == Direction.DOWN){
            return y+KorX;
        }
        return y;
    }
}