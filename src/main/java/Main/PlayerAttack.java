package Main;

import javax.swing.*;
import java.awt.*;

public class PlayerAttack {
    Image image = new ImageIcon("src/main/java/Main/images/playerAttack.png").getImage();
    int x, y;
    int width =image.getWidth(null);
    int hegiht =image.getHeight(null);
    int attack =5;

    public PlayerAttack(int x, int y){
        this.x =x;
        this.y =y;
    }
    //위로 발사될꺼니까 y좌표를 증가시켜준다.
    public void fire() {
        this.y -=15;
    }
}
