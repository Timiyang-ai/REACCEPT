public static int pixelXToTileX(double pixelX, double scaleFactor, int tileSize) {
        return (int) Math.min(Math.max(pixelX / tileSize, 0), scaleFactor - 1);
    }