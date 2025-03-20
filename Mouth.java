class Mouth extends Eyes {
    private BufferedImage mouthImage;
    private String mouthDescription = "mouth";

    public Mouth() {
        super();
        description += mouthDescription + ", ";
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            mouthImage = ImageIO.read(new File("mouth.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mouthImage != null) {
            g.drawImage(mouthImage, 0, 0, this);
        }
    }
}
