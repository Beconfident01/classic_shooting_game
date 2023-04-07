package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas {
    private static final long serialVersionUID = 1L;
    private boolean running = false;
    private Thread thread;
    private JFrame frame;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public Game() {
        frame = new JFrame("Game");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        start();
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(() -> {
            long lastTime = System.nanoTime();
            double amountOfTicks = 60.0;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;
            while (running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    tick();
                    delta--;
                }
                if (running)
                    render();
                frames++;

                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
            stop();
        });
        thread.start();
    }


//stop 메서드에서는 게임 루프를 중지하고 스레드를 종료합니다.
    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK); // 예시로 색상을 검정색으로 설정해 보았습니다.
        g.fillRect(0, 0, WIDTH, HEIGHT); // 예시로 전체 화면에 검정색으로 채워진 사각형을 그려 보았습니다.
        g.dispose();
        bs.show();
    }
//Game 클래스는 Java AWT와 Swing을 사용하여 게임 루프를 구현합니다.
//
//Game 클래스는 Canvas 클래스를 상속받아 화면을 구현합니다. Canvas는 AWT 컴포넌트 중 하나로, 그래픽을 출력하기 위한 영역을 제공합니다.
//
//Game 클래스의 멤버 변수로는 게임 루프를 제어하기 위한 running, 게임 루프를 실행할 스레드를 저장할 thread, 게임 창을 나타내는 JFrame 클래스의 인스턴스인 frame, 게임 창의 크기를 저장하는 상수 WIDTH와 HEIGHT가 있습니다.
//
//Game 클래스의 생성자에서는 frame 변수를 초기화하고 게임 창을 설정합니다. start 메서드에서는 게임 루프를 실행하기 위한 스레드를 생성하고 시작합니다. 게임 루프에서는 게임 업데이트를 담당하는 tick 메서드와 그래픽을 출력하는 render 메서드가 반복적으로 호출됩니다.
//
//tick 메서드와 render 메서드는 상속을 통해 하위 클래스에서 구현할 수 있도록 비어있는 상태로 남겨둡니다.
//
//게임 루프에서는 또한 초당 프레임 수(FPS)를 계산하여 출력합니다.
//
//stop 메서드에서는 게임 루프를 중지하고 스레드를 종료합니다.
//
//main 메서드에서는 Game 클래스의 객체를 생성하여 게임을 실행합니다.
    public static void main(String[] args) {
        new Game();
    }
}