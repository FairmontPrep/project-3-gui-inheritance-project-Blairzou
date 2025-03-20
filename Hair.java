class Hair extends Mouth {
    private BufferedImage hairImage;
    private String hairDescription = "hair";
    private String hairColor;

    public Hair() {
        super();
        Random rand = new Random();
        if (rand.nextBoolean()) {
            hairColor = "blonde";
        } else {
            hairColor = "brunette";
        }
        description += hairDescription + " (" + hairColor + ").";
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            hairImage = ImageIO.read(new File(hairColor + "_hair.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hairImage != null) {
            g.drawImage(hairImage, 0, 0, this);
        }
    }

    @Override
    public String getDescription() {
        return description; // Includes the random hair color description
    }
}
