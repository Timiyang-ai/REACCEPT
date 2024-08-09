public static double tileYToLatitude(long tileY, double scaleFactor) {
        return pixelYToLatitudeWithScaleFactor(tileY * DUMMY_TILE_SIZE, scaleFactor, DUMMY_TILE_SIZE);
    }