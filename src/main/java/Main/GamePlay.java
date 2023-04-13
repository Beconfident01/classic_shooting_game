package Main;

import javax.swing.*;
import java.awt.*;

public class GamePlay extends Thread{
    private int delay =20;
    private long pretime;
    //이벤트 발생 주기를 컨트롤 해줄 변수
    private int cnt;

    private Image player = new ImageIcon("src/main/java/Main/images/player.png").getImage();

    private int playerX, playerY;
    private int playerWidth =player.getWidth(null);
    private int playerHeight =player.getHeight(null);
    //키 입력 한 번에 이동 할 거리
    private int playerSpeed =10;
    private int playerHp =30;

    private boolean up, down, left, right;

    //GameLoop 구현
    @Override
    public void run(){
        cnt =0;
        playerX =10;
        playerY =(Main.SCREEN_HEIGHT - playerHeight) / 2;

        while(true){
            //현재 시간을 가져오기
            pretime = System.currentTimeMillis();
            //스레드를 일시적으로 멈추어 다음 루프의 반복이 일정한 시간 간격을 유지하도록 조절
            if(System.currentTimeMillis() -pretime < delay){
                try{
                    //스레드를 일시적으로 멈춘다.
                    Thread.sleep(delay - System.currentTimeMillis() + pretime);
                    KeyProcess();
                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //플레이어가 화면 밖으로 나가지 못하게 제한을 걸어준다.
    private void KeyProcess() {
        if(up && playerY - playerSpeed > 0) playerY -= playerSpeed;
        if(down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT) playerY += playerSpeed;
        if(left && playerX - playerSpeed > 0) playerX -= playerSpeed;
        if(right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) playerX += playerSpeed;
    }

    //게임 안의 요소들을 그려주는 메소드
    public void gameDraw(Graphics g) {
        playerDraw(g);
    }
    //플레이어를 그려주는 메소드
    public void playerDraw(Graphics g) {
        g.drawImage(player, playerX, playerY, null);
    }


    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
