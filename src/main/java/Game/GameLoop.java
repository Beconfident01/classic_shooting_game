package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class GameLoop extends Game {
    private int x = 0;
    private int y = 0;

    public GameLoop() {
        super();
    }

    @Override
    public void tick() {
        x++;
        y++;
    }

    @Override
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        super.render(); // 상위 클래스인 Game 클래스의 render() 메서드를 호출합니다.
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
        g.dispose();
        bs.show();
    }
    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.start();
    }
}