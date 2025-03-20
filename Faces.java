import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
abstract class Face extends JPanel {
    private BufferedImage faceImage;
    protected String description = "A face with ";

    public Face() {
        loadImage();
    }

    protected abstract void loadImage();

    protected void setFaceImage(String filePath) {
        try {
            faceImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BufferedImage getFaceImage() {
        return faceImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (faceImage != null) {
            g.drawImage(faceImage, 0, 0, this);
        }
    }

    public String getDescription() {
        return description;
    }
}    

