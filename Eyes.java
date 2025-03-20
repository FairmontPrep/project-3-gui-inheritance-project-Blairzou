class Eyes extends Face {
    private BufferedImage eyesImage;
    private String eyeDescription = "eyes";

    public Eyes() {
        super();
        description += eyeDescription + ", ";
    }

    @Override
    protected void loadImage() {
        setFaceImage("face_outline.png"); // base face
        try {
            eyesImage = ImageIO.read(new File("eyes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (eyesImage != null) {
            g.drawImage(eyesImage, 0, 0, this);
        }
    }
}
