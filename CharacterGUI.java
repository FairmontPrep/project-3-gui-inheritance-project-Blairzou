import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Base abstract layer class
abstract class CharacterLayer {
    protected BufferedImage image;
    protected String description = "";

    protected abstract void loadImage();

    public BufferedImage getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    protected void drawScaledImage(Graphics g, BufferedImage img, Dimension panelSize) {
        if (img == null) return;

        int panelWidth = panelSize.width;
        int panelHeight = panelSize.height;
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        double scale = Math.min((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);

        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);
        int x = (panelWidth - newWidth) / 2;
        int y = (panelHeight - newHeight) / 2;

        g.drawImage(img, x, y, newWidth, newHeight, null);
    }
}

class FaceLayer extends CharacterLayer {
    public FaceLayer() {
        loadImage();
    }

    @Override
    protected void loadImage() {
        try {
            image = ImageIO.read(new File("faces.png"));
            description = "Face: Smiling";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class EyesLayer extends CharacterLayer {
    public EyesLayer() {
        loadImage();
    }

    @Override
    protected void loadImage() {
        try {
            image = ImageIO.read(new File("eyes.png"));
            description = "Eyes: Green";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClothesLayer extends CharacterLayer {
    public ClothesLayer() {
        loadImage();
    }

    @Override
    protected void loadImage() {
        try {
            image = ImageIO.read(new File("clothes.png"));
            description = "Clothes: Hoodie";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class HairLayer extends CharacterLayer {
    private BufferedImage hair1, hair2;

    public HairLayer() {
        loadImage();
    }

    @Override
    protected void loadImage() {
        try {
            hair1 = ImageIO.read(new File("hairv1.png"));
            hair2 = ImageIO.read(new File("hairv2.png"));
            boolean useHair1 = Math.random() < 0.5;
            image = useHair1 ? hair1 : hair2;
            description = useHair1 ? "Hair: Spiky" : "Hair: Wavy";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class FullCharacterPanel extends JPanel {
    private FaceLayer face;
    private EyesLayer eyes;
    private ClothesLayer clothes;
    private HairLayer hair;

    public FullCharacterPanel() {
        face = new FaceLayer();
        eyes = new EyesLayer();
        clothes = new ClothesLayer();
        hair = new HairLayer();

        Timer t = new Timer(100, e -> repaint());
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = getSize();

        face.drawScaledImage(g, face.getImage(), size);
        eyes.drawScaledImage(g, eyes.getImage(), size);
        clothes.drawScaledImage(g, clothes.getImage(), size);
        hair.drawScaledImage(g, hair.getImage(), size);

        String fullDescription = face.getDescription() + " | " +
                                 eyes.getDescription() + " | " +
                                 clothes.getDescription() + " | " +
                                 hair.getDescription();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(fullDescription, 50, 3200);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(2550, 3300);
    }
}

public class CharacterGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("角色显示");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(2550, 3300);
            frame.setMinimumSize(new Dimension(400, 400));

            FullCharacterPanel panel = new FullCharacterPanel();
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
