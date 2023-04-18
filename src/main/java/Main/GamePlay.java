package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private boolean up, down, left, right, shooting;

    //플레이어의 공격을 담아줄 ArrayList
    ArrayList<PlayerAttack> playerAttackList =new ArrayList<PlayerAttack>();
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<EnemyAttack> enemyAttackList = new ArrayList<EnemyAttack>();
    private PlayerAttack playerAttack;
    private Enemy enemy;
    private EnemyAttack enemyAttack;

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
                    playerAttackProcess();
                    enemyAppearProcess();
                    enemyMoveProcess();
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
        //cnt가 0.02초 마다 올라가니까 0.3초마다 미사일이 발사되도록 해준다.
        if(shooting && cnt % 15 ==0) {
            //플레이어와 적당히 떨어진 곳에 공격을 생성
            playerAttack =new PlayerAttack(playerX,playerY+100);
            playerAttackList.add(playerAttack);
        }
    }

    private void playerAttackProcess() {
        for(int i = 0;i<playerAttackList.size();i++){
            playerAttack = playerAttackList.get(i);
            playerAttack.fire();
        }
    }

    //주기적으로 적을 등장시키는 메소드
    private void enemyAppearProcess() {
        //"cnt" 변수를 80으로 나눈 나머지가 0인 경우, 매 80번째 프레임마다 적을 생성합니다.
        if(cnt % 80 ==0) {
            enemy =new Enemy((int)(Math.random()*1121),10);
            //생성된 적은 "enemyList"라는 리스트에 추가되어 관리됩니다
            enemyList.add(enemy);
        }
    }
    //적이 움직이는 메서드
    private  void enemyMoveProcess(){
        for(int i =0; i<enemyList.size();i++){
            enemy =enemyList.get(i);
            enemy.move();
        }
    }

    //게임 안의 요소들을 그려주는 메소드
    public void gameDraw(Graphics g) {
        playerDraw(g);
        enemyDraw(g);
    }
    //플레이어를 그려주는 메소드
    public void playerDraw(Graphics g) {
        g.drawImage(player, playerX, playerY, null);
        for (int i = 0; i < playerAttackList.size(); i++) {
            playerAttack = playerAttackList.get(i);
            g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
        }
    }
    //적과 적의 공격을 그려줄 메서드
    public void enemyDraw(Graphics g){
        for(int i =0; i<enemyList.size(); i++){
            enemy =enemyList.get(i);
            g.drawImage(enemy.image,enemy.x,enemy.y,null);
        }
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

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
