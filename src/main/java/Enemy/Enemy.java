package Enemy;

public class Enemy {
    private int x; // 적군의 x좌표
    private int y; // 적군의 y좌표
    private int speed; // 이동 속도

    public Enemy(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
}