public static int latitudeToTileYWithScaleFactor(double latitude, double scaleFactor) {
        return pixelYToTileYWithScaleFactor(latitudeToPixelYWithScaleFactor(latitude, scaleFactor, DUMMY_TILE_SIZE), scaleFactor, DUMMY_TILE_SIZE);
    }