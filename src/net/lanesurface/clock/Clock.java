package net.lanesurface.clock;

import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.time.LocalDateTime;

public class Clock {
    /**
     * An array that hold the current time displayed on the clock, where the
     * the elements are in the order of hour, minute, and second.
     */
    private static int[] time = new int[3];
    
    private static final Hand[] hands = {
        new Hand(HandType.HOUR, java.awt.Color.BLACK),
        new Hand(HandType.MINUTE, java.awt.Color.BLUE),
        new Hand(HandType.SECOND, java.awt.Color.RED)
    };

    private static java.awt.Image face;
    
    /**
     * The radius of the clock (half the width of the circle encompassed by the
     * hands), in pixels.
     */
    private static final int CLK_RAD = 100;

    /**
     * The width and height of the content pane (not the total frame width).
     */
    private static final int CANVAS_WIDTH = 250,
                             CANVAS_HEIGHT = 250;

    public static void main(String[] args) throws java.io.IOException {
        face = ImageIO.read(ClassLoader.getSystemResource("watch-face.png"));
        
        JFrame frame = new JFrame("Analog Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setPreferredSize(new Dimension(CANVAS_WIDTH,
                                                              CANVAS_HEIGHT));
        frame.pack();

        @SuppressWarnings("serial")
        JPanel panel = new JPanel() {
            @Override
            public void paint(java.awt.Graphics graphics) {
                graphics.translate(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
                graphics.drawImage(face, -CLK_RAD, -CLK_RAD,
                                   2 * CLK_RAD, 2 * CLK_RAD, this);
                
                for (Hand hand : hands) {
                    graphics.setColor(hand.color);
                    graphics.drawLine(0, 0,
                                      hand.coordinates.x, -hand.coordinates.y);
                }
                
                graphics.setColor(java.awt.Color.BLACK);
                graphics.fillOval(-10, -10, 20, 20);
            }
        };
        frame.add(panel);

        frame.setVisible(true);
        
        new Timer(1000, (ae) -> {
            LocalDateTime now = LocalDateTime.now();
            
            time[0] = now.getHour() % 12;
            time[1] = now.getMinute();
            time[2] = now.getSecond();
            
            for (int i = 0; i < hands.length; i++) {
                Hand hand = hands[i];
                CoordinatePair coords = getHandCoordinates(hand.type, time[i]);
                hand.coordinates = coords;
            }
            panel.repaint();
        }).start();
    }

    private static CoordinatePair getHandCoordinates(HandType type,
                                                     int position) {
        double angle = getHandAngle(position, type.divisions);
        int x = (int)(Math.cos(angle) * (CLK_RAD - 10)),
            y = (int)(Math.sin(angle) * (CLK_RAD - 10));

        return new CoordinatePair(x, y);
    }

    private static double getHandAngle(int time, int frequency) {
        return -time * 2 * Math.PI / frequency  + Math.PI / 2;
    }
}