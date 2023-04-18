package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Timer;
import java.util.TimerTask;


public class Game extends JFrame {
    private Image bufferImage;
    private Graphics screenGraphics;
    private Image startScreen = new ImageIcon("src/main/java/Main/images/Start_screen.png").getImage();
    private Image loadingScreen = new ImageIcon("src/main/java/Main/images/loading_screen.png").getImage();
    private Image gameScreen = new ImageIcon("src/main/java/Main/images/game_screen.png").getImage();

    private boolean isStartScreen, isLoadingScreen, isGameScreen;

    public static GamePlay gamePlay = new GamePlay();

    public Game() {
        //창 제목
        setTitle("Shooting Game");
        //테두리 없는 창 구현
        setUndecorated(true);
        //창 크키, 크기 조절여부
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        //프로그램이 종료되도록 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //해당 컴포넌트를 화면에 표시
        setVisible(true);
        //해당 컴포넌트의 레이아웃 매니저 설정=null = 개발자가 직접설정
        setLayout(null);

        addKeyListener(new KeyListener());

        init();
    }

    // 초기화를 해줄 init 메소드를 만들어줍니다.
    private void init() {
        isStartScreen = true;
        isLoadingScreen = false;
        isGameScreen = false;

        addKeyListener(new KeyListener());
    }

    //로딩화면에서 게임화면으로 넘어가기 위한 메서드
    private void gameStart() {
        isStartScreen = false;
        isLoadingScreen = true;

        Timer loadingTimer = new Timer();
        TimerTask loadingTask = new TimerTask() {
            @Override
            public void run() {
                isLoadingScreen =false;
                isGameScreen =true;
            }
        };
        //로딩화면에서 3초 후 게임화면으로 넘어가기
        loadingTimer.schedule(loadingTask,3000);

        gamePlay.start();
    }


    //버퍼이미지를 만들고 이를 화면에 뿌려줘서 깜빡임을 최소화
    public void paint(Graphics g){
        //이미지 객체 생성
        bufferImage =createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        //Graphics 객체를 얻어오기
        screenGraphics =bufferImage.getGraphics();
        //그래픽 그리기
        screenDraw(screenGraphics);
        //그래픽이 그려진 bufferImage를 화면의 (0,0)좌표에 그리기
        g.drawImage(bufferImage,0,0,null);
    }

    //이미지를 표시하는 로직
    public void screenDraw(Graphics g) {
        //각 화면 변수가 true일 때마다 화면을 그려주기 위해 if문 사용
        if(isStartScreen){
            g.drawImage(startScreen,0,0,null);
        }
        if(isLoadingScreen){
            g.drawImage(loadingScreen,0,0,null);
        }
        if(isGameScreen){
            g.drawImage(gameScreen,0,0,null);
            //게임화면일 때, gameDraw 메서드 실행
            gamePlay.gameDraw(g);
        }
        //화면에 이미지를 그린 후 화면을 갱신하여 이미지 표시
        this.repaint();
    }
    class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    gamePlay.setUp(true);
                    break;
                case KeyEvent.VK_DOWN:
                    gamePlay.setDown(true);
                    break;
                case KeyEvent.VK_LEFT:
                    gamePlay.setLeft(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    gamePlay.setRight(true);
                    break;
                case KeyEvent.VK_SPACE:
                    gamePlay.setShooting(true);
                    break;
                //ENTER를 눌렀을 때 게임 시작
                case KeyEvent.VK_ENTER:
                    if(isStartScreen) gameStart();
                    break;
                //ESC 키를 눌렀을 때 프로그램이 종료
                case KeyEvent.VK_ESCAPE:
                    //프로그램을 정상적으로 종료
                    System.exit(0);
                    break;
            }
        }
        //키를 뗐을 때, false로 바꿔준다.
        public void keyReleased(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    gamePlay.setUp(false);
                    break;
                case KeyEvent.VK_DOWN:
                    gamePlay.setDown(false);
                    break;
                case KeyEvent.VK_LEFT:
                    gamePlay.setLeft(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    gamePlay.setRight(false);
                    break;
                case KeyEvent.VK_SPACE:
                    gamePlay.setShooting(false);
                    break;

            }
        }
    }
}
