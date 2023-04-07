package Player;

import Game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Game implements KeyListener {

    private int x; //Player의 x좌표
    private int y; //Player의 y좌표
    private int speed; //Player의 이동 속도
    private boolean leftPressed; //왼쪽 키를 눌렀을 떄
    private boolean rightPressed; //오른쪽 키를 눌렀을 떄
    private boolean upPressed; //위쪽 키를 눌렀을 떄
    private boolean downPressed; //아래쪽 키를 눌렀을 떄

    Player(int x,int y,int speed){
        this.x=x;
        this.y=y;
        this.speed=speed;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if (key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        // not used
    }
    public void update() {
        if (leftPressed) {
            x -= speed;
        }
        if (rightPressed) {
            x += speed;
        }
        if (upPressed) {
            y -= speed;
        }
        if (downPressed) {
            y += speed;
        }
    }

}
