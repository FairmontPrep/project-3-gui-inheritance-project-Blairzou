import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// 抽象类：角色基础层
abstract class CharacterLayer extends JPanel {
    private BufferedImage faceImage, eyesImage, clothesImage;

    public CharacterLayer() {
        loadImages();
    }

    protected abstract void loadImages();

    protected void setBaseImages(String facePath, String eyesPath, String clothesPath) {
        try {
            faceImage = ImageIO.read(new File(facePath));
            eyesImage = ImageIO.read(new File(eyesPath));
            clothesImage = ImageIO.read(new File(clothesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BufferedImage getFaceImage() { return faceImage; }
    protected BufferedImage getEyesImage() { return eyesImage; }
    protected BufferedImage getClothesImage() { return clothesImage; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScaledImage(g, getFaceImage());
        drawScaledImage(g, getEyesImage());
        drawScaledImage(g, getClothesImage());
    }

    // 计算缩放后的图片大小，并绘制
    protected void drawScaledImage(Graphics g, BufferedImage image) {
        if (image == null) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        // 计算缩放比例，确保图片不会超过窗口，并保持原比例
        double scale = Math.min((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);

        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);

        // 居中绘制图片
        int x = (panelWidth - newWidth) / 2;
        int y = (panelHeight - newHeight) / 2;

        g.drawImage(image, x, y, newWidth, newHeight, this);
    }
}

// 子类：添加头发（自动切换）
class CharacterWithHair extends CharacterLayer {
    private BufferedImage hairImage1, hairImage2;
    private boolean toggleHair = true;

    public CharacterWithHair() {
        super();
        startHairSwitching();
    }

    @Override
    protected void loadImages() {
        setBaseImages("faces.png", "eyes.png", "clothes.png");
        try {
            hairImage1 = ImageIO.read(new File("hairv1.png"));
            hairImage2 = ImageIO.read(new File("hairv2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startHairSwitching() {
        Timer timer = new Timer(1000, e -> { // 每秒切换一次头发
            toggleHair = !toggleHair;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScaledImage(g, toggleHair ? hairImage1 : hairImage2);
    }
}

//
public class CharacterGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("角色显示");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(2550,3300) ;  // 设置初始窗口大小
            frame.setMinimumSize(new Dimension(300, 300)); // 设置最小窗口大小

            CharacterLayer character = new CharacterWithHair();
            frame.add(character);
            frame.setVisible(true);
        });
    }
}

