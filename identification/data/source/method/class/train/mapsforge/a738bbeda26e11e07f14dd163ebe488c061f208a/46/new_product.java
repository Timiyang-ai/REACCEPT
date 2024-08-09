public static int pixelYToTileYWithScaleFactor(double pixelY, double scaleFactor, int tileSize) {
        return (int) Math.min(Math.max(pixelY / tileSize, 0), scaleFactor - 1);
    }