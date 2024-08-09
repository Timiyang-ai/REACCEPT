public static int latitudeToTileY(double latitude, double scaleFactor) {
        return pixelYToTileY(latitudeToPixelYWithScaleFactor(latitude, scaleFactor, DUMMY_TILE_SIZE), scaleFactor, DUMMY_TILE_SIZE);
    }